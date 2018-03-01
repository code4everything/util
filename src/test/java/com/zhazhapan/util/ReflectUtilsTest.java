package com.zhazhapan.util;

import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

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
}
