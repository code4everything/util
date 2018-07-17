package com.zhazhapan.util.model;

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
}
