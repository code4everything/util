package com.zhazhapan.util;

import com.zhazhapan.util.json.PrettyJsonObject;
import org.junit.Test;

/**
 * @author pantao
 * @since 2018/1/22
 */
public class PrettyJsonObjectTest {

    @Test
    public void test() {
        PrettyJsonObject object = new PrettyJsonObject();
        object.put("i", "love you!");
        object.put("you", "love me?");
        System.out.println(object.toString());
    }
}
