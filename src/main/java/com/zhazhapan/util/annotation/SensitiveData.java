package com.zhazhapan.util.annotation;

import java.lang.annotation.*;

/**
 * 此注解仅作用于 {@link String} 类型
 *
 * @author pantao
 * @since 2018/9/23
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SensitiveData {}
