package com.zhazhapan.util;

import com.zhazhapan.util.enums.FieldModifier;
import com.zhazhapan.util.enums.JsonMethod;
import com.zhazhapan.util.enums.JsonType;
import com.zhazhapan.util.annotation.ToJsonString;
import com.zhazhapan.util.common.interceptor.ToStringMethodInterceptor;
import net.sf.cglib.proxy.Enhancer;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;

/**
 * @author pantao
 * @since 2018/1/18
 */
public class BeanUtilsTest {

    @Test
    public void testToJsonString() throws IllegalAccessException, IOException, ClassNotFoundException {
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
}

@ToJsonString(type = JsonType.PRETTY, modifier = FieldModifier.PRIVATE, method = JsonMethod.HANDLE)
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

    @Override
    public String toString() {
        try {
            return BeanUtils.toJsonString(this, FieldModifier.ALL);
        } catch (IllegalAccessException e) {
            return e.getMessage();
        }
    }
}