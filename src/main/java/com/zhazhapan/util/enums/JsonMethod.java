package com.zhazhapan.util.enums;

/**
 * Bean类字段转换成JSON的方式
 *
 * @author pantao
 * @since 2018/1/20
 */
public enum JsonMethod {

    /**
     * 使用阿里巴巴的JSONObject
     */
    AUTO,

    /**
     * 使用手动的方式
     */
    HANDLE
}
