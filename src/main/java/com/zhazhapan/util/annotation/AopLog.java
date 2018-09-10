package com.zhazhapan.util.annotation;

import java.lang.annotation.*;

/**
 * @author pantao
 * @since 2018/9/10
 **/
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AopLog {

    /**
     * the description of method
     *
     * @return {@link String}
     */
    String value() default "";
}
