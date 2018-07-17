package com.zhazhapan.util.annotation;

import com.zhazhapan.modules.constant.ValueConsts;

import java.lang.annotation.*;

/**
 * @author pantao
 * @since 2018/7/17
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE})
public @interface FieldChecking {

    /**
     * 校验码，默认验证失败
     *
     * @return {@link Integer}
     */
    int code() default 104;

    /**
     * 提示消息
     *
     * @return {@link String}
     */
    String message() default "{}字段不能为空";

    /**
     * 验证状态
     *
     * @return {@link String}
     */
    String status() default ValueConsts.ERROR_EN;

    /**
     * 自定义验证表达式（用val代替字段值），支持正则匹配（使用英文冒号:开头），验证返回true时isPassed返回true，验证返回false时isPassed返回false
     *
     * @return {@link String}
     */
    String expression() default "";
}
