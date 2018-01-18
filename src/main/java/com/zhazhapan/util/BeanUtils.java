package com.zhazhapan.util;

import com.alibaba.fastjson.JSONObject;

import java.lang.reflect.Field;

/**
 * @author pantao
 * @date 2018/1/18
 */
public class BeanUtils {

    private BeanUtils() {}

    /**
     * 将Bean类的全部属性转换成JSON字符串
     *
     * @param object Bean对象
     *
     * @return 格式化的JSON字符串
     *
     * @throws IllegalAccessException 异常
     */
    public static String toPrettyJson(Object object) throws IllegalAccessException {
        return toPrettyJson(object, FieldModifier.ALL);
    }

    /**
     * 将Bean类指定修饰符的属性转换成JSON字符串
     *
     * @param object Bean对象
     * @param modifier 属性的权限修饰符
     *
     * @return 格式化的JSON字符串
     *
     * @throws IllegalAccessException 异常
     */
    public static String toPrettyJson(Object object, FieldModifier modifier) throws IllegalAccessException {
        return Formatter.formatJson(toJsonString(object, modifier));
    }

    /**
     * 将Bean类的全部属性转换成JSON字符串
     *
     * @param object Bean对象
     *
     * @return 没有格式化的JSON字符串
     *
     * @throws IllegalAccessException 异常
     */
    public static String toJsonString(Object object) throws IllegalAccessException {
        return toJsonString(object, FieldModifier.ALL);
    }

    /**
     * 将Bean类指定修饰符的属性转换成JSON字符串
     *
     * @param object Bean对象
     * @param modifier 属性的权限修饰符
     *
     * @return 没有格式化的JSON字符串
     *
     * @throws IllegalAccessException 异常
     */
    public static String toJsonString(Object object, FieldModifier modifier) throws IllegalAccessException {
        JSONObject jsonObject = new JSONObject();
        if (Checker.isNotNull(object)) {
            Class<?> bean = object.getClass();
            if (modifier == FieldModifier.ALL || modifier == FieldModifier.PUBLIC) {
                Field[] fields = bean.getFields();
                for (Field field : fields) {
                    jsonObject.put(field.getName(), field.get(object));
                }
            }
            if (modifier == FieldModifier.ALL || modifier == FieldModifier.PRIVATE) {
                Field[] declaredFields = bean.getDeclaredFields();
                for (Field field : declaredFields) {
                    field.setAccessible(true);
                    jsonObject.put(field.getName(), field.get(object));
                }
            }
        }
        return jsonObject.toString();
    }

    /**
     * 字段的权限修饰符
     */
    public enum FieldModifier {
        /**
         * public类型
         */
        PUBLIC,

        /**
         * private类型
         */
        PRIVATE,

        /**
         * 全部类型
         */
        ALL
    }
}
