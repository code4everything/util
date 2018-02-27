package com.zhazhapan.util;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.util.TypeUtils;
import com.zhazhapan.util.annotation.ToJsonString;
import com.zhazhapan.util.enums.FieldModifier;
import com.zhazhapan.util.enums.JsonMethod;
import com.zhazhapan.util.enums.JsonType;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * @author pantao
 * @since 2018/1/18
 */
public class BeanUtils {

    private BeanUtils() {}

    /**
     * 将JOSNObject转换为Bean
     *
     * @param jsonObject {@link JSONObject}
     * @param object {@link Object}
     *
     * @throws IllegalAccessException 异常
     */
    public static void jsonPutIn(JSONObject jsonObject, Object object) throws IllegalAccessException {
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            field.set(object, TypeUtils.castToJavaBean(jsonObject.get(field.getName()), field.getType()));
        }
    }

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
        return toJsonString(object, modifier, JsonMethod.AUTO);
    }

    public static String toJsonString(Object object, FieldModifier modifier, JsonMethod method) throws
            IllegalAccessException {
        JSONObject jsonObject = new JSONObject();
        StringBuilder builder = new StringBuilder("{");
        if (Checker.isNotNull(object)) {
            Class<?> bean = object.getClass();
            Field[] fields = bean.getDeclaredFields();
            for (Field field : fields) {
                int mod = field.getModifiers();
                boolean addable = modifier == FieldModifier.ALL || (modifier == FieldModifier.PRIVATE && Modifier
                        .isPrivate(mod)) || (modifier == FieldModifier.PUBLIC && Modifier.isPublic(mod));
                if (addable) {
                    field.setAccessible(true);
                    if (method == JsonMethod.HANDLE) {
                        builder.append("\"").append(field.getName()).append("\":\"").append(field.get(object)).append
                                ("\",");
                    } else {
                        jsonObject.put(field.getName(), field.get(object));
                    }
                }
            }
        }
        return JsonMethod.HANDLE == method ? builder.substring(0, builder.length() - 1) + "}" : jsonObject.toString();
    }

    /**
     * 通过注解将Bean转换为JSON
     *
     * @param object Bean对象
     *
     * @return {@link String}
     *
     * @throws IllegalAccessException 异常
     */
    public static String toJsonStringByAnnotation(Object object) throws IllegalAccessException {
        JsonType jsonType = JsonType.CONDENSED;
        FieldModifier modifier = FieldModifier.ALL;
        JsonMethod method = JsonMethod.AUTO;
        if (Checker.isNotNull(object)) {
            Class<?> bean = object.getClass();
            if (bean.isAnnotationPresent(ToJsonString.class)) {
                ToJsonString annotation = bean.getAnnotation(ToJsonString.class);
                jsonType = annotation.type();
                modifier = annotation.modifier();
                method = annotation.method();
            }
        }
        String json = toJsonString(object, modifier, method);
        return jsonType == JsonType.PRETTY ? Formatter.formatJson(json) : json;
    }
}
