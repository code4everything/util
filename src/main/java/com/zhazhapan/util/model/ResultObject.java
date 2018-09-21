package com.zhazhapan.util.model;

import com.alibaba.fastjson.JSONObject;
import com.zhazhapan.modules.constant.ValueConsts;

import java.sql.Timestamp;

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

    public Timestamp timestamp = new Timestamp(System.currentTimeMillis());

    /**
     * 无参构造
     */
    public ResultObject() {}

    /**
     * 构造方法
     *
     * @param code 返回码
     */
    public ResultObject(int code) {
        this.code = code;
    }

    /**
     * 构造方法
     *
     * @param message 消息
     */
    public ResultObject(String message) {
        this.message = message;
    }

    /**
     * 构造方法
     *
     * @param data 数据对象
     */
    public ResultObject(T data) {
        this.data = data;
    }

    /**
     * 构造方法
     *
     * @param code 返回码
     * @param message 消息
     */
    public ResultObject(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 构造方法
     *
     * @param code 返回码
     * @param data 数据对象
     */
    public ResultObject(int code, T data) {
        this.code = code;
        this.data = data;
    }

    /**
     * 构造方法
     *
     * @param message 消息
     * @param data 数据对象
     */
    public ResultObject(String message, T data) {
        this.message = message;
        this.data = data;
    }

    /**
     * /** 构造方法
     *
     * @param code 返回码
     * @param message 消息
     * @param status 状态
     */
    public ResultObject(int code, String message, String status) {
        this.message = message;
        this.code = code;
        this.status = status;
    }

    /**
     * 构造方法
     *
     * @param code 返回码
     * @param message 消息
     * @param data 数据对象
     */
    public ResultObject(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 构造方法
     *
     * @param code 返回码
     * @param message 消息
     * @param status 状态
     * @param data 数据对象
     */
    public ResultObject(int code, String message, String status, T data) {
        this.data = data;
        this.code = code;
        this.message = message;
        this.status = status;
    }

    /**
     * 设置返回码
     *
     * @param code 返回码
     *
     * @return {@link ResultObject}
     *
     * @since 1.1.0
     */
    public ResultObject<T> setCode(int code) {
        this.code = code;
        return this;
    }

    /**
     * 设置消息
     *
     * @param message 消息
     *
     * @return {@link ResultObject}
     *
     * @since 1.1.0
     */
    public ResultObject<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    /**
     * 设置状态
     *
     * @param status 状态
     *
     * @return {@link ResultObject}
     *
     * @since 1.1.0
     */
    public ResultObject<T> setStatus(String status) {
        this.status = status;
        return this;
    }

    /**
     * 设置数据
     *
     * @param data {@link T}
     *
     * @return {@link ResultObject}
     *
     * @since 1.1.0
     */
    public ResultObject<T> setData(T data) {
        this.data = data;
        return this;
    }

    /**
     * 从没有使用泛型 {@link ResultObject}中复制数据到泛型类中
     *
     * @param resultObject 没有使用泛型的 {@link ResultObject}
     *
     * @return 使用了泛型的 {@link ResultObject}
     *
     * @since 1.1.0
     */
    public ResultObject<T> copyFrom(ResultObject resultObject) {
        this.message = resultObject.message;
        this.status = resultObject.status;
        this.code = resultObject.code;
        return this;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
