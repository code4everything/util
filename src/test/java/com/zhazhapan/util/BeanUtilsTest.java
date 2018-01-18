package com.zhazhapan.util;

import org.junit.Test;

import java.util.Date;

/**
 * @author pantao
 * @date 2018/1/18
 */
public class BeanUtilsTest {

    @Test
    public void testToJsonString() throws IllegalAccessException {
        User user = new User(1, "test", new Date());
        System.out.println(BeanUtils.toJsonString(user));
        System.out.println(BeanUtils.toJsonString(new User()));
    }
}

class User {
    public int id;
    private String name;
    private Date birth;

    public User() {}

    public User(int id, String name, Date birth) {
        this.id = id;
        this.name = name;
        this.birth = birth;
    }
}