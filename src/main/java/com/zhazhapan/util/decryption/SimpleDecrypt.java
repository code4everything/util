package com.zhazhapan.util.decryption;

import com.zhazhapan.util.CryptUtils;
import com.zhazhapan.util.encryption.SimpleEncrypt;

import java.io.IOException;

/**
 * @author pantao
 */
public class SimpleDecrypt {

    private SimpleDecrypt() {}

    /**
     * 异或解密
     *
     * @param code {@link String}
     * @param key {@link Integer}
     *
     * @return {@link String}
     */
    public static String xor(String code, int key) {
        return SimpleEncrypt.xor(code, key);
    }

    /**
     * ascii解密
     *
     * @param code {@link String}
     * @param key {@link Integer}
     *
     * @return {@link String}
     */
    public static String ascii(String code, int key) {
        StringBuilder string = new StringBuilder();
        int[] keys = CryptUtils.getKeys(key);
        for (int i = 0; i < code.length(); i++) {
            string.append((char) (code.charAt(i) - (i + keys[0]) % keys[1] % keys[2]));
        }
        return string.toString();
    }

    /**
     * sscii解密
     *
     * @param code {@link String}
     * @param key {@link String}
     *
     * @return {@link String}
     */
    public static String ascii(String code, String key) {
        return ascii(code, CryptUtils.stringToKey(key));
    }

    /**
     * 混合解密
     *
     * @param code {@link String}
     * @param key {@link Integer}
     *
     * @return {@link String}
     *
     * @throws IOException 异常
     */
    public static String mix(String code, int key) throws IOException {
        return xor(JavaDecrypt.base64(ascii(code, key)), key);
    }
}
