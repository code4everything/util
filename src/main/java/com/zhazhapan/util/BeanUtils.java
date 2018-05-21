package com.zhazhapan.util;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.util.TypeUtils;
import com.zhazhapan.modules.constant.ValueConsts;
import com.zhazhapan.util.annotation.ToJsonString;
import com.zhazhapan.util.enums.FieldModifier;
import com.zhazhapan.util.enums.JsonMethod;
import com.zhazhapan.util.enums.JsonType;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author pantao
 * @since 2018/1/18
 */
public class BeanUtils {

    private static final JsonMethod[] METHODS = new JsonMethod[]{JsonMethod.MANUAL, JsonMethod.HANDLE};

    private BeanUtils() {}

    /**
     * 将类属性装换成JSON（只能转换有get和is方法的）
     *
     * @param object 转换的对象
     *
     * @return {@link JSONObject}
     *
     * @throws IllegalAccessException 异常
     * @throws InvocationTargetException 异常
     */
    public static JSONObject beanToJson(Object object) throws IllegalAccessException, InvocationTargetException {
        Method[] methods = object.getClass().getMethods();
        JSONObject jsonObject = new JSONObject();
        for (Method method : methods) {
            String name = method.getName();
            if (name.startsWith("get") && !"getClass".equals(name)) {
                name = name.substring(3);
                jsonObject.put(name.substring(0, 1).toLowerCase() + name.substring(1), method.invoke(object));
            } else if (name.startsWith("is")) {
                jsonObject.put(name, method.invoke(object));
            }
        }
        return jsonObject;
    }

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

    /**
     * 将Bean类指定修饰符的属性转换成JSON字符串
     *
     * @param object Bean对象
     * @param modifier 属性的权限修饰符
     * @param method {@link JsonMethod}
     *
     * @return 没有格式化的JSON字符串
     *
     * @throws IllegalAccessException 异常
     */
    public static String toJsonString(Object object, FieldModifier modifier, JsonMethod method) throws
            IllegalAccessException {
        JSONObject jsonObject = new JSONObject();
        StringBuilder builder = new StringBuilder("{");
        boolean isManual = false;
        if (Checker.isNotNull(object)) {
            Class<?> bean = object.getClass();
            Field[] fields = bean.getDeclaredFields();
            for (Field field : fields) {
                int mod = field.getModifiers();
                boolean addable = modifier == FieldModifier.ALL || (modifier == FieldModifier.PRIVATE && Modifier
                        .isPrivate(mod)) || (modifier == FieldModifier.PUBLIC && Modifier.isPublic(mod));
                if (addable) {
                    field.setAccessible(true);
                    isManual = Checker.isIn(method, METHODS);
                    if (isManual) {
                        Object f = field.get(object);
                        if (Checker.isNotNull(f)) {
                            builder.append(converter(field.getName(), f));
                        }
                    } else {
                        jsonObject.put(field.getName(), field.get(object));
                    }
                }
            }
        }
        return isManual ? builder.substring(0, builder.length() - 1) + "}" : jsonObject.toString();
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

    /**
     * 手动转换 {@link List} 和 {@link Map}
     *
     * @param fieldName 字段名
     * @param object 对象
     *
     * @return json对象
     */
    private static String converter(String fieldName, Object object) {
        StringBuilder builder = new StringBuilder();
        if (Checker.isNotEmpty(fieldName)) {
            builder.append("\"").append(fieldName).append("\":");
        }
        if (object instanceof Collection) {
            List list = (List) object;
            builder.append("[");
            list.forEach(obj -> builder.append(converter(ValueConsts.EMPTY_STRING, obj)));
            return builder.substring(0, builder.length() - 1) + "],";
        } else if (object instanceof Map) {
            Map map = (Map) object;
            builder.append("{");
            map.forEach((k, v) -> builder.append(converter(k.toString(), v)));
            return builder.substring(0, builder.length() - 1) + "},";
        } else if (Checker.isEmpty(fieldName)) {
            builder.append("\"").append(object).append("\",");
        } else {
            builder.append("\"").append(object).append("\",");
        }
        return builder.toString();
    }
}
