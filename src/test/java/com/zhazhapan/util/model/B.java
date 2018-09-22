package com.zhazhapan.util.model;

/**
 * @author pantao
 * @since 2018/9/22
 */
public class B {

    private Integer age;

    private String bio;

    @Override
    public String toString() {
        return "B{" + "age=" + age + ", bio='" + bio + '\'' + '}';
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
