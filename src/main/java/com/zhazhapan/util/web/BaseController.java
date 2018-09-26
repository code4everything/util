package com.zhazhapan.util.web;

import com.zhazhapan.util.Checker;
import com.zhazhapan.util.LoggerUtils;
import com.zhazhapan.util.annotation.SensitiveData;
import com.zhazhapan.util.model.CheckResult;
import com.zhazhapan.util.model.ResultObject;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.List;

/**
 * @author pantao
 * @since 2018/9/18
 */
public class BaseController {

    private ThreadLocal<HttpServletRequest> request = new ThreadLocal<>();

    private ThreadLocal<String> token = new ThreadLocal<>();

    private boolean checkSensitiveData = false;

    public BaseController() {}

    public BaseController(boolean checkSensitiveData) {
        this.checkSensitiveData = checkSensitiveData;
    }

    public BaseController(HttpServletRequest request) {
        this.request.set(request);
    }

    public BaseController(HttpServletRequest request, boolean checkSensitiveData) {
        this.request.set(request);
        this.checkSensitiveData = checkSensitiveData;
    }

    protected String getToken() {
        if (Checker.isEmpty(token.get())) {
            token.set(request.get().getHeader("token"));
            if (Checker.isEmpty(token.get())) {
                token.set(request.get().getParameter("token"));
            }
            token.remove();
            request.remove();
        }
        return token.get();
    }

    protected ResultObject<Boolean> parseBooleanResult(String okMsg, String errMsg, boolean isOk) {
        ResultObject<Boolean> resultObject = new ResultObject<>(isOk);
        return isOk ? resultObject.setMessage(okMsg) : resultObject.setMessage(errMsg);
    }

    protected ResultObject parseResult(boolean isOk) {
        return parseResult("", isOk);
    }

    protected ResultObject parseResult(String prefix, boolean isOk) {
        if (Checker.isEmpty(prefix)) {
            prefix = "操作";
        }
        String msg = prefix + (isOk ? "成功" : "失败");
        return parseResult(msg, msg, isOk);
    }

    protected ResultObject parseResult(String okMsg, String errMsg, boolean isOk) {
        return isOk ? new ResultObject(okMsg) : CheckResult.getErrorResult(errMsg);
    }

    protected <T> ResultObject<List<T>> parseResult(String errMsg, List<T> list) {
        return parseResult(errMsg, list, checkSensitiveData);
    }

    protected <T> ResultObject<List<T>> parseResult(String errMsg, List<T> list, boolean checkSensitiveData) {
        if (Checker.isEmpty(list)) {
            return CheckResult.getErrorResult(errMsg);
        }
        if (checkSensitiveData) {
            list.forEach(this::setSensitiveData);
        }
        return new ResultObject<>(list);
    }

    protected <T> ResultObject<T> parseResult(String okMsg, String errMsg, T t) {
        return parseResult(okMsg, errMsg, t, checkSensitiveData);
    }

    protected <T> ResultObject<T> parseResult(String okMsg, String errMsg, T t, boolean checkSensitiveData) {
        if (Checker.isNull(t)) {
            return CheckResult.getErrorResult(errMsg);
        }
        if (checkSensitiveData) {
            setSensitiveData(t);
        }
        return new ResultObject<>(okMsg, t);
    }

    protected <T> void setSensitiveData(T t) {
        if (Checker.isNotNull(t)) {
            Field[] fields = t.getClass().getDeclaredFields();
            final String sensitiveDataTip = "******";
            try {
                for (Field field : fields) {
                    SensitiveData sensitiveData = field.getAnnotation(SensitiveData.class);
                    if (Checker.isNotNull(sensitiveData) && field.getType() == String.class) {
                        field.setAccessible(true);
                        field.set(t, sensitiveDataTip);
                    }
                }
            } catch (IllegalAccessException e) {
                LoggerUtils.error("set sensitive data error: {}", e.getMessage());
            }
        }
    }

    protected <T> ResultObject<T> parseResult(String errMsg, T t) {
        return parseResult(errMsg, t, checkSensitiveData);
    }

    protected <T> ResultObject<T> parseResult(String errMsg, T t, boolean checkSensitiveData) {
        return parseResult("操作成功", errMsg, t, checkSensitiveData);
    }

    protected <T> ResultObject<T> parseResult(T t) {
        return parseResult("操作失败", t);
    }
}
