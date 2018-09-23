package com.zhazhapan.util.model;

import com.zhazhapan.modules.constant.ValueConsts;

/**
 * @author pantao
 * @since 2018/7/17
 */
public class CheckResult<T> {

    private static final int DEFAULT_ERROR_CODE = 400;

    private static final String DEFAULT_ERROR_MESSAGE = "参数输入错误";

    public boolean passed = true;

    public ResultObject<T> resultObject = null;

    /**
     * 获取失败的结果
     *
     * @param code 错误码
     * @param message 消息
     *
     * @return {@link ResultObject}
     *
     * @since 1.1.0
     */
    public static <T> ResultObject<T> getErrorResult(int code, String message) {
        return new ResultObject<>(code, message, ValueConsts.ERROR_EN);
    }

    /**
     * 获取失败的结果
     *
     * @return {@link ResultObject}
     *
     * @since 1.1.0
     */
    public static <T> ResultObject<T> getErrorResult() {
        return getErrorResult(DEFAULT_ERROR_CODE, DEFAULT_ERROR_MESSAGE);
    }

    /**
     * 获取失败的结果
     *
     * @param code 错误码
     *
     * @return {@link ResultObject}
     *
     * @since 1.1.0
     */
    public static <T> ResultObject<T> getErrorResult(int code) {
        return getErrorResult(code, DEFAULT_ERROR_MESSAGE);
    }

    /**
     * 获取失败的结果
     *
     * @param message 消息
     *
     * @return {@link ResultObject}
     *
     * @since 1.1.0
     */
    public static <T> ResultObject<T> getErrorResult(String message) {
        return getErrorResult(DEFAULT_ERROR_CODE, message);
    }
}
