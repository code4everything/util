package com.zhazhapan.util.decryption;

import com.zhazhapan.util.encryption.JavaEncrypt;
import sun.misc.BASE64Decoder;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author pantao
 */
public class JavaDecrypt {

    private JavaDecrypt() {}

    /**
     * base64解密
     *
     * @param code {@link String}
     *
     * @return {@link String}
     *
     * @throws IOException 异常
     */
    public static String base64(String code) throws IOException {
        return new String(new BASE64Decoder().decodeBuffer(code));
    }

    /**
     * des解密
     *
     * @param code {@link String}
     *
     * @return {@link String}
     *
     * @throws InvalidKeyException 异常
     * @throws NoSuchAlgorithmException 异常
     * @throws NoSuchPaddingException 异常
     * @throws UnsupportedEncodingException 异常
     * @throws IllegalBlockSizeException 异常
     * @throws BadPaddingException 异常
     */
    public static String des(String code) throws InvalidKeyException, NoSuchAlgorithmException,
            NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException {
        return JavaEncrypt.decryptDES(code, "DES");
    }

    /**
     * des3解密
     *
     * @param code {@link String}
     *
     * @return {@link String}
     *
     * @throws InvalidKeyException 异常
     * @throws NoSuchAlgorithmException 异常
     * @throws NoSuchPaddingException 异常
     * @throws UnsupportedEncodingException 异常
     * @throws IllegalBlockSizeException 异常
     * @throws BadPaddingException 异常
     */
    public static String des3(String code) throws InvalidKeyException, NoSuchAlgorithmException,
            NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException {
        return JavaEncrypt.decryptDES(code, "DESede");
    }

    /**
     * aes解密
     *
     * @param code {@link String}
     *
     * @return {@link String}
     *
     * @throws InvalidKeyException 异常
     * @throws NoSuchAlgorithmException 异常
     * @throws NoSuchPaddingException 异常
     * @throws UnsupportedEncodingException 异常
     * @throws IllegalBlockSizeException 异常
     * @throws BadPaddingException 异常
     */
    public static String aes(String code) throws InvalidKeyException, NoSuchAlgorithmException,
            NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException {
        return JavaEncrypt.decryptDES(code, "AES");
    }

    /**
     * rsa解密
     *
     * @param code {@link String}
     *
     * @return {@link String}
     *
     * @throws InvalidKeyException 异常
     * @throws NoSuchAlgorithmException 异常
     * @throws NoSuchPaddingException 异常
     * @throws UnsupportedEncodingException 异常
     * @throws IllegalBlockSizeException 异常
     * @throws BadPaddingException 异常
     */
    public static String rsa(String code) throws InvalidKeyException, NoSuchAlgorithmException,
            NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException {
        return JavaEncrypt.decryptDES(code, "RSA");
    }
}
