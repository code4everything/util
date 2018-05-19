package com.zhazhapan.util;

import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author pantao
 * @since 2018/1/20
 */
public class ReflectUtilsTest {

    public void setFontSize(Integer size) {
        System.out.println(size);
    }

    @Test
    public void testGetClass() throws IOException, ClassNotFoundException {
        List<Class<?>> list = ReflectUtils.getClasses("com.zhazhapan.util");
        for (Class<?> type : list) {
            System.out.println(type.getName());
        }
    }

    @Test
    public void invokeMethod() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        System.out.println(ReflectUtils.invokeMethod(new ReflectUtilsTest(), "setFontSize", new Object[]{32}));
    }

    @Test
    public void executeExpression() {
        Map<String, Object> map = new HashMap<>();
        map.put("alive", "coding every day");
        map.put("out", System.out);
        String expression = "out.print(alive)";
        ReflectUtils.executeExpression(expression, map);
    }

    @Test
    public void invokeMethodUseBasicType() {
    }

    @Test
    public void getBasicTypes() {
    }

    @Test
    public void getTypes() {
    }

    @Test
    public void scanPackage() {
    }

    @Test
    public void getClasses() {
    }

    @Test
    public void addClassesInPackageByFile() {
    }
}
