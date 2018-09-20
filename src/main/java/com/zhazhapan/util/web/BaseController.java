package com.zhazhapan.util.web;

import com.zhazhapan.modules.constant.ValueConsts;
import com.zhazhapan.util.Checker;
import com.zhazhapan.util.model.CheckResult;
import com.zhazhapan.util.model.ResultObject;

import javax.servlet.http.HttpServletRequest;

/**
 * @author pantao
 * @since 2018/9/18
 */
public class BaseController {

    private HttpServletRequest request;

    private String token;

    public BaseController() {}

    public BaseController(HttpServletRequest request) {
        this.request = request;
    }

    protected String getToken() {
        if (Checker.isEmpty(token)) {
            token = request.getHeader("token");
            if (Checker.isEmpty(token)) {
                token = request.getParameter("token");
            }
        }
        return token;
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

    protected <T> ResultObject<T> parseResult(String okMsg, String errMsg, T t) {
        if (Checker.isNull(t)) {
            return new ResultObject<>(400, errMsg, ValueConsts.ERROR_EN);
        }
        return new ResultObject<>(okMsg, t);
    }

    protected <T> ResultObject<T> parseResult(String errMsg, T t) {
        return parseResult("操作成功", errMsg, t);
    }

    protected <T> ResultObject<T> parseResult(T t) {
        return parseResult("操作失败", t);
    }
}
