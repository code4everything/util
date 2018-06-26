package com.zhazhapan.util;

import cn.hutool.core.util.StrUtil;
import com.zhazhapan.modules.constant.ValueConsts;
import org.apache.log4j.Logger;

/**
 * @author pantao
 * @since 2018/6/8
 */
public class LoggerUtils {

    private static final Class<?> LOCAL_CLASS = LoggerUtils.class;

    private LoggerUtils() {}

    /**
     * 获取 {@link Logger}
     *
     * @return {@link Logger}
     *
     * @since 1.0.8
     */
    public static Logger getLogger() {
        return getLogger(LOCAL_CLASS);
    }

    /**
     * 获取 {@link Logger}
     *
     * @param object 指定对象
     *
     * @return {@link Logger}
     *
     * @since 1.0.8
     */
    public static Logger getLogger(Object object) {
        return Checker.isNull(object) ? getLogger() : getLogger(object.getClass());
    }

    /**
     * 获取 {@link Logger}
     *
     * @param clazz 类
     *
     * @return {@link Logger}
     *
     * @since 1.0.8
     */
    public static Logger getLogger(Class<?> clazz) {
        return Logger.getLogger(Checker.isNull(clazz) ? LOCAL_CLASS : clazz);
    }

    /**
     * 信息
     *
     * @param message 消息
     * @param values 格式化参数
     *
     * @since 1.0.8
     */
    public static void info(String message, Object... values) {
        info(LOCAL_CLASS, message, values);
    }

    /**
     * 信息
     *
     * @param object 指定对象
     * @param message 消息
     * @param values 格式化参数
     *
     * @since 1.0.8
     */
    public static void info(Object object, String message, Object... values) {
        if (Checker.isNull(object)) {
            info(message, values);
        } else {
            info(object.getClass(), message, values);
        }
    }

    /**
     * 信息
     *
     * @param clazz 类
     * @param message 消息
     * @param values 格式化参数
     *
     * @since 1.0.8
     */
    public static void info(Class<?> clazz, String message, Object... values) {
        getLogger(clazz).info(formatString(message, values));
    }

    /**
     * 警告
     *
     * @param message 消息
     * @param values 格式化参数
     *
     * @since 1.0.8
     */
    public static void warn(String message, Object... values) {
        warn(LOCAL_CLASS, message, values);
    }

    /**
     * 警告
     *
     * @param object 指定对象
     * @param message 消息
     * @param values 格式化参数
     *
     * @since 1.0.8
     */
    public static void warn(Object object, String message, Object... values) {
        if (Checker.isNull(object)) {
            warn(message, values);
        } else {
            warn(object.getClass(), message, values);
        }
    }

    /**
     * 警告
     *
     * @param clazz 类
     * @param message 消息
     * @param values 格式化参数
     *
     * @since 1.0.8
     */
    public static void warn(Class<?> clazz, String message, Object... values) {
        getLogger(clazz).warn(formatString(message, values));
    }

    /**
     * 错误
     *
     * @param message 消息
     * @param values 格式化参数
     *
     * @since 1.0.8
     */
    public static void error(String message, Object... values) {
        error(LOCAL_CLASS, message, values);
    }

    /**
     * 错误
     *
     * @param object 指定对象
     * @param message 消息
     * @param values 格式化参数
     *
     * @since 1.0.8
     */
    public static void error(Object object, String message, Object... values) {
        if (Checker.isNull(object)) {
            error(message, values);
        } else {
            error(object.getClass(), message, values);
        }
    }

    /**
     * 错误
     *
     * @param clazz 类
     * @param message 消息
     * @param values 格式化参数
     *
     * @since 1.0.8
     */
    public static void error(Class<?> clazz, String message, Object... values) {
        getLogger(clazz).error(formatString(message, values));
    }

    /**
     * 调试
     *
     * @param message 消息
     * @param values 格式化参数
     *
     * @since 1.0.8
     */
    public static void debug(String message, Object... values) {
        debug(LOCAL_CLASS, message, values);
    }

    /**
     * 调试
     *
     * @param object 指定对象
     * @param message 消息
     * @param values 格式化参数
     *
     * @since 1.0.8
     */
    public static void debug(Object object, String message, Object... values) {
        if (Checker.isNull(object)) {
            debug(message, values);
        } else {
            debug(object.getClass(), message, values);
        }
    }

    /**
     * 调试
     *
     * @param clazz 类
     * @param message 消息
     * @param values 格式化参数
     *
     * @since 1.0.8
     */
    public static void debug(Class<?> clazz, String message, Object... values) {
        getLogger(clazz).debug(formatString(message, values));
    }

    /**
     * 严重错误
     *
     * @param message 消息
     * @param values 格式化参数
     *
     * @since 1.0.8
     */
    public static void fatal(String message, Object... values) {
        fatal(LOCAL_CLASS, message, values);
    }

    /**
     * 严重错误
     *
     * @param object 指定对象
     * @param message 消息
     * @param values 格式化参数
     *
     * @since 1.0.8
     */
    public static void fatal(Object object, String message, Object... values) {
        if (Checker.isNull(object)) {
            fatal(message, values);
        } else {
            fatal(object.getClass(), message, values);
        }
    }

    /**
     * 严重错误
     *
     * @param clazz 类
     * @param message 消息
     * @param values 格式化参数
     *
     * @since 1.0.8
     */
    public static void fatal(Class<?> clazz, String message, Object... values) {
        getLogger(clazz).fatal(formatString(message, values));
    }

    /**
     * 格式化字符串
     *
     * @param message 消息
     * @param values 格式化参数
     *
     * @return 格式化的字符串
     *
     * @since 1.0.8
     */
    private static String formatString(String message, Object[] values) {
        if (Checker.isNotEmpty(message)) {
            if (Checker.isNotEmpty(values)) {
                return StrUtil.format(String.format(message, values), values);
            }
            return message;
        }
        return ValueConsts.EMPTY_STRING;
    }
}
