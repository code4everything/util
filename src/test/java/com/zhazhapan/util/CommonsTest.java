package com.zhazhapan.util;

import com.alibaba.fastjson.JSONObject;
import com.zhazhapan.util.annotation.ToJsonString;
import com.zhazhapan.util.enums.JsonType;
import com.zhazhapan.util.model.ResultObject;
import com.zhazhapan.util.model.TestBean;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author pantao
 * @since 2018/5/22
 */
public class CommonsTest {

    @Test
    public void testRefelect() throws NoSuchFieldException {
        assert TestBean.class.getField("username").getType() == String.class;
        assert TestBean.class.getField("age").getType() != String.class;
    }

    @Test
    public void testResultObject() {
        ResultObject<String> resultObject = new ResultObject<>();
        System.out.println(resultObject.setCode(200).setMessage("ok").setData("everything is ok"));
    }

    @Test
    public void testCastToBean() throws InvocationTargetException, IllegalAccessException {
        Person person = new Person("pan", 120);
        List<Map<String, Finger>> fingers = new ArrayList<>();
        String[] f = {"one", "three", "four", "five", "six", "seven", "eight", "nine", "ten"};
        Map<String, Finger> fingerMap = new HashMap<>();
        Finger finger;
        for (String s : f) {
            finger = new Finger(RandomUtils.getRandomInteger(0, 100), RandomUtils.getRandomInteger(0, 100));
            fingerMap.put(s, finger);
        }
        fingers.add(fingerMap);
        person.setFingers(fingers);
        JSONObject object = BeanUtils.beanToJson(person);
        System.out.println(object);
        System.out.println(JSONObject.toJavaObject(object, Person.class));
    }
}

@ToJsonString(type = JsonType.PRETTY)
class Person {

    private List<Map<String, Finger>> fingers;

    private String name;

    private int age;

    Person() {}

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public List<Map<String, Finger>> getFingers() {
        return fingers;
    }

    public void setFingers(List<Map<String, Finger>> fingers) {
        this.fingers = fingers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        try {
            return BeanUtils.toJsonStringByAnnotation(this);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return "";
    }
}

class Finger {

    private int height;

    private int width;

    public Finger() {}

    public Finger(int height, int width) {
        this.height = height;
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
