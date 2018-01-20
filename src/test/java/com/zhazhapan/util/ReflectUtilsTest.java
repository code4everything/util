package com.zhazhapan.util;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * @author pantao
 * @date 2018/1/20
 */
public class ReflectUtilsTest {

    @Test
    public void testGetClass() throws IOException, ClassNotFoundException {
        List<Class<?>> list = ReflectUtils.getClasses("com.zhazhapan.util");
        for (Class<?> type : list) {
            System.out.println(type.getName());
        }
    }
}
