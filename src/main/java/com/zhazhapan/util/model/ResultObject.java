package com.zhazhapan.util.model;

import com.alibaba.fastjson.JSONObject;
import com.zhazhapan.modules.constant.ValueConsts;

/**
 * @author pantao
 * @since 2018/7/17
 */
public class ResultObject<T> {

    /**
     * 校验码
     */
    public int code = 200;

    /**
     * 提示消息
     */
    public String message = "验证通过";

    /**
     * 状态
     */
    public String status = ValueConsts.SUCCESS;

    /**
     * 自定义Data
     */
    public T data = null;

    public ResultObject() {}

    public ResultObject(int code) {
        this.code = code;
    }

    public ResultObject(String message) {
        this.message = message;
    }

    public ResultObject(T data) {
        this.data = data;
    }

    public ResultObject(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResultObject(int code, T data) {
        this.code = code;
        this.data = data;
    }

    public ResultObject(String message, T data) {
        this.message = message;
        this.data = data;
    }

    public ResultObject(int code, String message, String status) {
        this.message = message;
        this.code = code;
        this.status = status;
    }

    public ResultObject(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResultObject(int code, String message, String status, T data) {
        this.data = data;
        this.code = code;
        this.message = message;
        this.status = status;
    }

    public ResultObject<T> setCode(int code) {
        this.code = code;
        return this;
    }

    public ResultObject<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public ResultObject<T> setStatus(String status) {
        this.status = status;
        return this;
    }

    public ResultObject<T> setData(T data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
