package com.zhazhapan.util;

import com.zhazhapan.util.annotation.AopLog;

import java.lang.reflect.Method;

/**
 * @author pantao
 * @since 2018/9/10
 **/
public class AopLogUtils {

    public static String getDescription(String className, String methodName, Object[] args) throws ClassNotFoundException {
        Class targetClass = Class.forName(className);
        Method[] methods = targetClass.getMethods();
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazz = method.getParameterTypes();
                if (clazz.length == args.length) {
                    return method.getAnnotation(AopLog.class).value();
                }
            }
        }
        return "";
    }

    public static String getDescriptionNoThrow(String className, String methodName, Object[] args) {
        try {
            return getDescription(className, methodName, args);
        } catch (ClassNotFoundException e) {
            LoggerUtils.error("get description from {}#{} error: {}", className, methodName, e.getMessage());
            return "";
        }
    }
}
