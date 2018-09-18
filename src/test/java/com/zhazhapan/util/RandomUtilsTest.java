package com.zhazhapan.util;

import com.zhazhapan.modules.constant.ValueConsts;
import org.junit.Test;

public class RandomUtilsTest {

    @Test
    public void getRandomInteger() {
        System.out.println(RandomUtils.getRandomInteger(ValueConsts.NINE_INT));
    }

    @Test
    public void getRandomUid() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            System.out.println(RandomUtils.getRandomUid());
            Thread.sleep(1000);
        }
    }

    @Test
    public void getRandomEmail() {
    }

    @Test
    public void getRandomString() {
    }

    @Test
    public void getRandomStringWithoutSymbol() {
    }

    @Test
    public void getRandomStringOnlyLetter() {
    }

    @Test
    public void getRandomStringOnlyLowerCase() {
    }

    @Test
    public void getRandomStringOnlyUpperCase() {
    }

    @Test
    public void getRandomTextIgnoreRange() {
    }

    @Test
    public void getRandomText() {
    }

    @Test
    public void getRandomDouble() {
    }

    @Test
    public void getRandomDouble1() {
    }

    @Test
    public void getRandomDouble2() {
    }

    @Test
    public void getRandomIntegerIgnoreRange() {
    }

    @Test
    public void getRandomColor() {
    }

    @Test
    public void getRandomColor1() {
    }

    @Test
    public void getRandomNumber() {
        for (int i = 0; i < 100; i++) {
            assert Checker.isNumber(RandomUtils.getRandomNumber(11));
        }
    }
}