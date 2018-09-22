package com.zhazhapan.util.model;

/**
 * @author pantao
 * @since 2018/9/22
 */
public class A {

    private String name;

    private String gender;

    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "A{" + "name='" + name + '\'' + ", gender='" + gender + '\'' + ", age=" + age + '}';
    }
}
