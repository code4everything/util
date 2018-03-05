/**
 *
 */
package com.zhazhapan.util.encryption;

import org.apache.commons.codec.binary.Hex;
import sun.misc.BASE64Encoder;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

/**
 * @author pantao
 */
public class JavaEncrypt {

    private static final String DES_CRYPT_WAY = "DES";

    private static final String DES3_CRYPT_WAY = "DESede";

    private static final String AES_CRYPT_WAY = "AES";

    private static short DES = 0;
    private static SecretKey secretKey;
    private static Cipher cipher;

    private static short DES3 = 0;
    private static SecretKey secretKey3;
    private static Cipher cipher3;

    private static short AES = 0;
    private static SecretKey aesKey;
    private static Cipher aes;

    private static short RSA = 0;
    private static KeyPair rsaKey;
    private static Cipher rsa;

    private JavaEncrypt() {}

    /**
     * HMAC加密，单向
     *
     * @param string {@link String}
     *
     * @return {@link String}
     *
     * @throws NoSuchAlgorithmException 异常
     * @throws InvalidKeyException 异常
     */
    public static String hmac(String string) throws NoSuchAlgorithmException, InvalidKeyException {
        String code = "";
        KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacMD5");
        SecretKey secretKey = keyGenerator.generateKey();
        SecretKey secretKey2 = new SecretKeySpec(secretKey.getEncoded(), "HmacMD5");
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());
        mac.init(secretKey2);
        return code;
    }

    /**
     * 初始化加密器，双向加密，不支持中文
     *
     * @param key {@link String}
     *
     * @return {@link Boolean}
     *
     * @throws NoSuchAlgorithmException 异常
     * @throws NoSuchPaddingException 异常
     */
    private static boolean initDES(String key) throws NoSuchAlgorithmException, NoSuchPaddingException {
        Security.addProvider(new com.sun.crypto.provider.SunJCE());
        boolean b = false;
        // DES、DESede、AES为对称加密，RSA为非对称加密
        if (DES_CRYPT_WAY.equals(key)) {
            KeyGenerator keygen = KeyGenerator.getInstance(key);
            secretKey = keygen.generateKey();
            cipher = Cipher.getInstance(key);
            DES = 1;
        } else if (DES3_CRYPT_WAY.equals(key)) {
            KeyGenerator keygen = KeyGenerator.getInstance(key);
            secretKey3 = keygen.generateKey();
            cipher3 = Cipher.getInstance(key);
            DES3 = 1;
        } else if (AES_CRYPT_WAY.equals(key)) {
            KeyGenerator keygen = KeyGenerator.getInstance(key);
            aesKey = keygen.generateKey();
            aes = Cipher.getInstance(key);
            AES = 1;
        } else {
            // RSA加密
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(key);
            keyPairGen.initialize(1024);
            rsaKey = keyPairGen.generateKeyPair();
            rsa = Cipher.getInstance(key);
            RSA = 1;
        }
        b = true;
        return b;
    }

    /**
     * 为Cipher加密器提供一个解密开放接口
     *
     * @param code {@link String}
     * @param key {@link String}
     *
     * @return {@link String}
     *
     * @throws BadPaddingException 异常
     * @throws IllegalBlockSizeException 异常
     * @throws UnsupportedEncodingException 异常
     * @throws NoSuchPaddingException 异常
     * @throws NoSuchAlgorithmException 异常
     * @throws InvalidKeyException 异常
     */
    public static String decryptDES(String code, String key) throws InvalidKeyException, NoSuchAlgorithmException,
            NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException {
        return cryptDES(code, Cipher.DECRYPT_MODE, key);
    }

    /**
     * Cipher加密解密器
     *
     * @param string {@link String}
     * @param opmode {@link Integer}
     * @param key {@link String}
     *
     * @return {@link String}
     *
     * @throws NoSuchPaddingException 异常
     * @throws NoSuchAlgorithmException 异常
     * @throws InvalidKeyException 异常
     * @throws BadPaddingException 异常
     * @throws IllegalBlockSizeException 异常
     * @throws UnsupportedEncodingException 异常
     */
    private static String cryptDES(String string, int opmode, String key) throws NoSuchAlgorithmException,
            NoSuchPaddingException, InvalidKeyException, UnsupportedEncodingException, IllegalBlockSizeException,
            BadPaddingException {
        String result = "";
        int isInit = "DES".equals(key) ? DES : ("AES".equals(key) ? AES : ("RSA".equals(key) ? RSA : DES3));
        if (isInit == 1 || initDES(key) || initDES(key)) {
            if (DES_CRYPT_WAY.equals(key)) {
                cipher.init(opmode, secretKey);
                result = new String(cipher.doFinal(string.getBytes("ISO-8859-1")), "ISO-8859-1");
            } else if (DES3_CRYPT_WAY.equals(key)) {
                cipher3.init(opmode, secretKey3);
                result = new String(cipher3.doFinal(string.getBytes("ISO-8859-1")), "ISO-8859-1");
            } else if (AES_CRYPT_WAY.equals(key)) {
                aes.init(opmode, aesKey);
                result = new String(aes.doFinal(string.getBytes("ISO-8859-1")), "ISO-8859-1");
            } else {
                rsa.init(opmode, opmode == Cipher.ENCRYPT_MODE ? (RSAPublicKey) rsaKey.getPublic() : (RSAPrivateKey)
                        rsaKey.getPrivate());
                result = new String(rsa.doFinal(string.getBytes("ISO-8859-1")), "ISO-8859-1");
            }
        }
        return result;
    }

    /**
     * RSA加密
     *
     * @param string {@link String}
     *
     * @return {@link String}
     *
     * @throws BadPaddingException 异常
     * @throws IllegalBlockSizeException 异常
     * @throws UnsupportedEncodingException 异常
     * @throws NoSuchPaddingException 异常
     * @throws NoSuchAlgorithmException 异常
     * @throws InvalidKeyException 异常
     */
    public static String rsa(String string) throws InvalidKeyException, NoSuchAlgorithmException,
            NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException {
        return cryptDES(string, Cipher.ENCRYPT_MODE, "RSA");
    }

    /**
     * DES3加密
     *
     * @param string {@link String}
     *
     * @return {@link String}
     *
     * @throws BadPaddingException 异常
     * @throws IllegalBlockSizeException 异常
     * @throws UnsupportedEncodingException 异常
     * @throws NoSuchPaddingException 异常
     * @throws NoSuchAlgorithmException 异常
     * @throws InvalidKeyException 异常
     */
    public static String des3(String string) throws InvalidKeyException, NoSuchAlgorithmException,
            NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException {
        return cryptDES(string, Cipher.ENCRYPT_MODE, "DESede");
    }

    /**
     * AES加密
     *
     * @param string {@link String}
     *
     * @return {@link String}
     *
     * @throws BadPaddingException 异常
     * @throws IllegalBlockSizeException 异常
     * @throws UnsupportedEncodingException 异常
     * @throws NoSuchPaddingException 异常
     * @throws NoSuchAlgorithmException 异常
     * @throws InvalidKeyException 异常
     */
    public static String aes(String string) throws InvalidKeyException, NoSuchAlgorithmException,
            NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException {
        return cryptDES(string, Cipher.ENCRYPT_MODE, "AES");
    }

    /**
     * DES加密
     *
     * @param string {@link String}
     *
     * @return {@link String}
     *
     * @throws BadPaddingException 异常
     * @throws IllegalBlockSizeException 异常
     * @throws UnsupportedEncodingException 异常
     * @throws NoSuchPaddingException 异常
     * @throws NoSuchAlgorithmException 异常
     * @throws InvalidKeyException 异常
     */
    public static String des(String string) throws InvalidKeyException, NoSuchAlgorithmException,
            NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException {
        return cryptDES(string, Cipher.ENCRYPT_MODE, "DES");
    }

    /**
     * BASE64加密
     *
     * @param string {@link String}
     *
     * @return {@link String}
     */
    public static String base64(String string) {
        return new BASE64Encoder().encodeBuffer(string.getBytes());
    }

    /**
     * SHA加密，返回指定进制格式
     *
     * @param string {@link String}
     * @param scale {@link String}
     *
     * @return {@link String}
     *
     * @throws NoSuchAlgorithmException 异常
     */
    public static String sha(String string, int scale) throws NoSuchAlgorithmException {
        return messageDigest("SHA", string, scale);
    }

    /**
     * SHA加密，返回32进制格式
     *
     * @param string {@link String}
     *
     * @return {@link String}
     *
     * @throws NoSuchAlgorithmException 异常
     */
    public static String sha(String string) throws NoSuchAlgorithmException {
        return messageDigest("SHA", string, 32);
    }

    /**
     * SHA-256加密
     *
     * @param string {@link String}
     *
     * @return {@link String}
     *
     * @throws NoSuchAlgorithmException 异常
     * @throws UnsupportedEncodingException 异常
     */
    public static String sha256(String string) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest messageDigest;
        messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] hash = messageDigest.digest(string.getBytes("UTF-8"));
        return String.valueOf(Hex.encodeHex(hash));
    }

    /**
     * MD5加密，返回指定进制格式
     *
     * @param string {@link String}
     * @param scale {@link String}
     *
     * @return {@link String}
     *
     * @throws NoSuchAlgorithmException 异常
     */
    public static String md5(String string, int scale) throws NoSuchAlgorithmException {
        return messageDigest("MD5", string, scale);
    }

    /**
     * MD5加密，返回16进制格式
     *
     * @param string {@link String}
     *
     * @return {@link String}
     *
     * @throws NoSuchAlgorithmException 异常
     */
    public static String md5(String string) throws NoSuchAlgorithmException {
        return messageDigest("MD5", string, 16);
    }

    /**
     * 消息摘要算法，单向加密
     *
     * @param key {@link String}
     * @param string {@link String}
     * @param scale {@link Integer}
     *
     * @return {@link String}
     *
     * @throws NoSuchAlgorithmException 异常
     */
    private static String messageDigest(String key, String string, int scale) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(key);
        md.update(string.getBytes());
        BigInteger bigInteger = new BigInteger(md.digest());
        return bigInteger.toString(scale);
    }
}
