package com.zhazhapan.util;

/**
 * @author pantao
 */
public class CryptUtils {

    private static final int TEN = 10;

    private static final int HUNDRED = 100;

    private static final int THOUSAND = 1000;

    private CryptUtils() {}

    /**
     * 通过获取一个KEY数组
     *
     * @param key 可自定义一个INT型数值
     *
     * @return 长度为3的KEY数组
     *
     * @deprecated 无用类
     */
    public static int[] getKeys(int key) {
        int[] keys = {0, 1, 1};
        if (key > 0) {
            // 自定义算Key
            if (key < TEN) {
                keys[0] = key;
                keys[1] = key - key / 2;
                keys[2] = Math.abs(keys[1] - key % keys[1]);
            } else if (key < HUNDRED) {
                keys[0] = key % 10;
                keys[1] = Math.abs(key / 10 - keys[0]) % 9 + 1;
                keys[2] = keys[0] * keys[1] / (keys[0] + keys[1]) % 10;
            } else if (key < THOUSAND) {
                int middle = key % 100 / 10;
                keys[0] = key % 10;
                keys[1] = key / 100;
                keys[2] = ((int) (Math.pow(keys[1], keys[0]) / middle) % 9 + 1);
            } else {
                int length = String.valueOf(key).length();
                keys[0] = key % 10;
                keys[1] = key / (int) Math.pow(10, length - 1);
                length = (keys[0] + keys[1] + keys[0] * keys[1]) % (length - 2) + 2;
                keys[2] = (int) (key % Math.pow(10, length) / Math.pow(10, length - 1));
            }
            keys[2] = keys[2] % 8 + 2;
        }
        return keys;
    }

    /**
     * 通过字符串获取KEY
     *
     * @param string 可随机一个字符串
     *
     * @return INT型KEY
     */
    public static int stringToKey(String string) {
        string = Checker.checkNull(string);
        int length = string.length();
        if (length > THOUSAND) {
            return length;
        }
        int key = 0;
        for (int i = 0; i < length; i++) {
            key += (int) string.charAt(i);
        }
        return key;
    }
}
