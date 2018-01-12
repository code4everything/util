/**
 *
 */
package com.zhazhapan.util.encryption;

import com.zhazhapan.util.CryptUtil;

/**
 * @author pantao
 */
public class SimpleEncryt {

    private SimpleEncryt() {}

    /**
     * 混合加密
     *
     * @param string {@link String}
     * @param key {@link Integer}
     *
     * @return {@link String}
     */
    public static String mix(String string, int key) {
        return ascii(JavaEncrypt.base64(xor(string, key)), key);
    }

    /**
     * 异或加密
     *
     * @param string {@link String}
     * @param key {@link Integer}
     *
     * @return {@link String}
     */
    public static String xor(String string, int key) {
        char[] encrypt = string.toCharArray();
        for (int i = 0; i < encrypt.length; i++) {
            encrypt[i] = (char) (encrypt[i] ^ key);
        }
        return new String(encrypt);
    }

    /**
     * 异或加密
     *
     * @param string {@link String}
     * @param key {@link String}
     *
     * @return {@link String}
     */
    public static String xor(String string, String key) {
        return xor(string, CryptUtil.stringToKey(string));
    }

    /**
     * ascii加密
     *
     * @param string {@link String}
     * @param key {@link Integer}
     *
     * @return {@link String}
     */
    public static String ascii(String string, int key) {
        StringBuilder code = new StringBuilder();
        int[] keys = CryptUtil.getKeys(key);
        for (int i = 0; i < string.length(); i++) {
            code.append((char) (string.charAt(i) + (i + keys[0]) % keys[1] % keys[2]));
        }
        return code.toString();
    }

    /**
     * ascii加密
     *
     * @param string {@link String}
     * @param key {@link String}
     *
     * @return {@link String}
     */
    public static String ascii(String string, String key) {
        return ascii(string, CryptUtil.stringToKey(key));
    }
}
