package com.zhazhapan.util;

import com.zhazhapan.modules.constant.ValueConsts;
import com.zhazhapan.util.annotation.ToJsonString;
import com.zhazhapan.util.common.interceptor.ToStringMethodInterceptor;
import com.zhazhapan.util.enums.FieldModifier;
import com.zhazhapan.util.enums.JsonMethod;
import com.zhazhapan.util.enums.JsonType;
import com.zhazhapan.util.model.A;
import com.zhazhapan.util.model.B;
import net.sf.cglib.proxy.Enhancer;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

/**
 * @author pantao
 * @since 2018/1/18
 */
public class BeanUtilsTest {

    private String file = ValueConsts.USER_DESKTOP + File.separator + "user.obj";

    @Test
    public void testToJsonString() throws IllegalAccessException {
        User user = new User(1, "test", new Date());
        System.out.println(user.toString());
        System.out.println(new User().toString());
        System.out.println(BeanUtils.toJsonStringByAnnotation(user));

        //测试cglib代理
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(User.class);
        enhancer.setCallback(new ToStringMethodInterceptor());
        User hello = (User) enhancer.create();
        System.out.println(hello.hashCode());
    }

    @Test
    public void deserialize() throws IOException, ClassNotFoundException {
        User user = BeanUtils.deserialize(file, User.class);
        System.out.println(user);
    }

    @Test
    public void serialize() throws Exception {
        User user = new User(2, "serializable", new Date());
        BeanUtils.serialize(user, file);
    }

    @Test
    public void beanToJson() {
    }

    @Test
    public void jsonPutIn() {
    }

    @Test
    public void toPrettyJson() {
    }

    @Test
    public void toPrettyJson1() {
    }

    @Test
    public void toJsonString() {
    }

    @Test
    public void toJsonString1() {
    }

    @Test
    public void toJsonString2() {
    }

    @Test
    public void toJsonStringByAnnotation() {
    }

    @Test
    public void bean2Another() throws InvocationTargetException, NoSuchMethodException, InstantiationException,
            IllegalAccessException {
        B b = new B();
        b.setAge(102);
        b.setBio("test");
        A a = BeanUtils.bean2Another(b, A.class);
        Assert.assertNotNull(a);
        System.out.println(a);
    }

    @Test
    public void toJsonObject() {
    }
}

@ToJsonString(type = JsonType.PRETTY, modifier = FieldModifier.PRIVATE, method = JsonMethod.MANUAL)
class User implements Serializable {

    public int id;

    private String name;

    private Date birth;

    public User() {}

    public User(int id, String name, Date birth) {
        this.id = id;
        this.name = name;
        this.birth = birth;
    }

    @Override
    public String toString() {
        try {
            return BeanUtils.toJsonString(this, FieldModifier.ALL);
        } catch (IllegalAccessException e) {
            return e.getMessage();
        }
    }
}