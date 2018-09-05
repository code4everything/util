package com.zhazhapan.util.model;

import com.alibaba.fastjson.JSONObject;
import com.zhazhapan.modules.constant.ValueConsts;

/**
 * @author pantao
 * @since 2018/7/17
 */
public class ResultObject {

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
    public Object data = null;

    public ResultObject() {}

    public ResultObject(int code) {
        this.code = code;
    }

    public ResultObject(String message) {
        this.message = message;
    }

    public ResultObject(Object data) {
        this.data = data;
    }

    public ResultObject(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResultObject(int code, Object data) {
        this.code = code;
        this.data = data;
    }

    public ResultObject(String message, Object data) {
        this.message = message;
        this.data = data;
    }

    public ResultObject(int code, String message, String status) {
        this.message = message;
        this.code = code;
        this.status = status;
    }

    public ResultObject(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResultObject(int code, String message, String status, Object data) {
        this.data = data;
        this.code = code;
        this.message = message;
        this.status = status;
    }

    public ResultObject setCode(int code) {
        this.code = code;
        return this;
    }

    public ResultObject setMessage(String message) {
        this.message = message;
        return this;
    }

    public ResultObject setStatus(String status) {
        this.status = status;
        return this;
    }

    public ResultObject setData(Object data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
