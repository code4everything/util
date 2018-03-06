/**
 *
 */
package com.zhazhapan.util;

import com.zhazhapan.util.model.SimpleColor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

/**
 * @author pantao
 */
public class RandomUtils {

    private RandomUtils() {}

    public static String getRandomEmail() {
        return getRandomStringOnlyLowerCase(getRandomInteger(3, 10)) + "@" + getRandomStringOnlyLowerCase
                (getRandomInteger(3, 5)) + "." + getRandomStringOnlyLowerCase(getRandomInteger(1, 5));
    }

    /**
     * 获取随机整数
     *
     * @param length 长度
     *
     * @return {@link Integer}
     */
    public static int getRandomInteger(int length) {
        return getRandomInteger((int) Math.pow(10, length - 1), (int) Math.pow(10, length));
    }

    /**
     * 获取随机字符串（包括大小写字母，数字和符号）
     *
     * @param length 长度
     *
     * @return {@link String}
     */
    public static String getRandomString(int length) {
        return getRandomText(32, 126, length);
    }

    /**
     * 获取没有符号的随机字符串（包括大小写字母和数字）
     *
     * @param length 长度
     *
     * @return {@link String}
     */
    public static String getRandomStringWithoutSymbol(int length) {
        return getRandomTextIgnoreRange(48, 122, length, new int[]{58, 64}, new int[]{91, 96});
    }

    /**
     * 获取只有大小写字母的随机字符串
     *
     * @param length 长度
     *
     * @return {@link String}
     */
    public static String getRandomStringOnlyLetter(int length) {
        return getRandomTextIgnoreRange(65, 122, length, new int[]{91, 96});
    }

    /**
     * 获取只有小写字母的随机字符串
     *
     * @param length 长度
     *
     * @return {@link String}
     */
    public static String getRandomStringOnlyLowerCase(int length) {
        return getRandomText(97, 122, length);
    }

    /**
     * 获取只有大写字母的随机字符串
     *
     * @param length 长度
     *
     * @return {@link String}
     */
    public static String getRandomStringOnlyUpperCase(int length) {
        return getRandomText(65, 90, length);
    }

    /**
     * 获取自定义忽略多个区间的随机字符串
     *
     * @param floor ascii下限
     * @param ceil ascii上限
     * @param length 长度
     * @param ranges 忽略区间（包含下限和上限长度为2的一维数组），可以有多个忽略区间
     *
     * @return 字符串
     */
    public static String getRandomTextIgnoreRange(int floor, int ceil, int length, int[]... ranges) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append((char) getRandomIntegerIgnoreRange(floor, ceil, ranges));
        }
        return builder.toString();
    }

    /**
     * 获取自定义随机字符串
     *
     * @param floor ascii下限
     * @param ceil ascii上限
     * @param length 长度
     *
     * @return 字符串
     */
    public static String getRandomText(int floor, int ceil, int length) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append((char) getRandomInteger(floor, ceil));
        }
        return builder.toString();
    }

    /**
     * 获取随机浮点数，保留2位小数，下限为0，上限为{@link Double#MAX_VALUE}
     *
     * @return {@link Double}
     */
    public static double getRandomDouble() {
        return getRandomDouble(0, Double.MAX_VALUE);
    }

    /**
     * 获取随机浮点数，保留2位小数
     *
     * @param floor 下限
     * @param ceil 上限
     *
     * @return {@link Double}
     */
    public static double getRandomDouble(double floor, double ceil) {
        return getRandomDouble(floor, ceil, 2);
    }

    /**
     * 获取随机浮点数
     *
     * @param floor 下限
     * @param ceil 上限
     * @param precision 精度（小数位数）
     *
     * @return {@link Double}
     */
    public static double getRandomDouble(double floor, double ceil, int precision) {
        BigDecimal decimal = new BigDecimal(floor + new Random().nextDouble() * (ceil - floor));
        return decimal.setScale(precision, RoundingMode.HALF_UP).doubleValue();
    }

    /**
     * 获取随机整数，下限为0，上限为{@link Integer#MAX_VALUE}
     *
     * @return {@link Integer}
     */
    public static int getRandomInteger() {
        return getRandomInteger(0, Integer.MAX_VALUE);
    }

    /**
     * 获取忽略多个区间段的随机整数
     *
     * @param floor 下限
     * @param ceil 上限
     * @param ranges 忽略区间（包含下限和上限长度为2的一维数组），可以有多个忽略区间
     *
     * @return {@link Integer}
     */
    public static int getRandomIntegerIgnoreRange(int floor, int ceil, int[]... ranges) {
        int result = getRandomInteger(floor, ceil);
        for (int[] range : ranges) {
            if (range[0] <= result && result <= range[1]) {
                result = result - 1 - (range[1] - range[0]);
                break;
            }
        }
        return result;
    }

    /**
     * 获取随机整数
     *
     * @param floor 下限
     * @param ceil 上限
     *
     * @return {@link Integer}
     */
    public static int getRandomInteger(int floor, int ceil) {
        return floor + new Random().nextInt(ceil - floor);
    }

    /**
     * 获取随机颜色
     *
     * @param opacity 不透明度
     *
     * @return {@link SimpleColor}
     */
    public static SimpleColor getRandomColor(double opacity) {
        Random ran = new Random();
        int b = ran.nextInt(255);
        int r = 255 - b;
        return new SimpleColor(b + ran.nextInt(r), b + ran.nextInt(r), b + ran.nextInt(r), opacity);

    }

    /**
     * 获取随机颜色，默认不透明
     *
     * @return {@link SimpleColor}
     */
    public static SimpleColor getRandomColor() {
        return getRandomColor(1d);
    }
}
