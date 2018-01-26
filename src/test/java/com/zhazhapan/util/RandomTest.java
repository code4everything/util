/**
 *
 */
package com.zhazhapan.util;

import com.zhazhapan.modules.constant.ValueConsts;
import org.junit.Test;

/**
 * @author pantao
 */
public class RandomTest {

    @Test
    public void testRandomColor() {
        for (int i = 0; i < ValueConsts.ONE_HUNDRED_INT; i++) {
            System.out.println(RandomUtils.getRandomColor().toString());
        }
    }

    @Test
    public void testRandomInteger() {
        for (int i = 0; i < ValueConsts.ONE_HUNDRED_INT; i++) {
            System.out.println(RandomUtils.getRandomInteger(0, 456));
        }
    }

    @Test
    public void testRandomDouble() {
        for (int i = 0; i < ValueConsts.ONE_HUNDRED_INT; i++) {
            System.out.println(RandomUtils.getRandomDouble(0, 999.999, 7));
        }
    }

    @Test
    public void testRandomString() {
        for (int i = 0; i < ValueConsts.ONE_HUNDRED_INT; i++) {
            System.out.println(RandomUtils.getRandomString(10));
        }
    }

    @Test
    public void testRandomStringWithoutSymbol() {
        for (int i = 0; i < ValueConsts.ONE_HUNDRED_INT; i++) {
            System.out.println(RandomUtils.getRandomStringWithoutSymbol(10));
        }
    }

    @Test
    public void testRandomStringOnlyLetter() {
        for (int i = 0; i < ValueConsts.ONE_HUNDRED_INT; i++) {
            System.out.println(RandomUtils.getRandomStringOnlyLetter(10));
        }
    }

    @Test
    public void testRandomStringOnlyLowerCase() {
        for (int i = 0; i < ValueConsts.ONE_HUNDRED_INT; i++) {
            System.out.println(RandomUtils.getRandomStringOnlyLowerCase(10));
        }
    }

    @Test
    public void testRandomStringOnlyUpperCase() {
        for (int i = 0; i < ValueConsts.ONE_HUNDRED_INT; i++) {
            System.out.println(RandomUtils.getRandomStringOnlyUpperCase(10));
        }
    }

    @Test
    public void testRandomEmail() {
        for (int i = 0; i < ValueConsts.ONE_HUNDRED_INT; i++) {
            System.out.println(RandomUtils.getRandomEmail());
        }
    }
}
