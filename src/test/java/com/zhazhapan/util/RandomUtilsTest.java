package com.zhazhapan.util;

import com.zhazhapan.modules.constant.ValueConsts;
import org.junit.Test;

public class RandomUtilsTest {

    @Test
    public void getRandomInteger() {
        System.out.println(RandomUtils.getRandomInteger(ValueConsts.NINE_INT));
    }
}