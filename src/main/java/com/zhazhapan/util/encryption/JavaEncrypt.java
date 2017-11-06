/**
 * 
 */
package com.zhazhapan.util.encryption;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Encoder;

/**
 * @author pantao
 *
 */
@SuppressWarnings("restriction")
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

	/**
	 * HMAC加密，单向
	 * 
	 * @param string
	 * @return
	 */
	public static String hmac(String string) {
		String code = "";
		try {
			KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacMD5");
			SecretKey secretKey = keyGenerator.generateKey();
			SecretKey secretKey2 = new SecretKeySpec(secretKey.getEncoded(), "HmacMD5");
			Mac mac = Mac.getInstance(secretKey.getAlgorithm());
			mac.init(secretKey2);
			code = new String(mac.doFinal(string.getBytes()));
		} catch (NoSuchAlgorithmException | InvalidKeyException | IllegalStateException e) {
			System.out.println("加密失败：" + e.getMessage());
		}
		return code;
	}

	/**
	 * 初始化加密器，双向加密，不支持中文
	 * 
	 * @param key
	 * @return
	 */
	private static boolean initDES(String key) {
		Security.addProvider(new com.sun.crypto.provider.SunJCE());
		boolean b = false;
		try {
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
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			System.out.println("初始化对称加密器失败：" + e.getMessage());
		}
		return b;
	}

	/**
	 * 为Cipher加密器提供一个解密开放接口
	 * 
	 * @param code
	 * @param key
	 * @return
	 */
	public static String decryptDES(String code, String key) {
		return cryptDES(code, Cipher.DECRYPT_MODE, key);
	}

	/**
	 * Cipher加密解密器
	 * 
	 * @param string
	 * @param opmode
	 * @param key
	 * @return
	 */
	private static String cryptDES(String string, int opmode, String key) {
		String result = "";
		int isInit = "DES".equals(key) ? DES : ("AES".equals(key) ? AES : ("RSA".equals(key) ? RSA : DES3));
		if (isInit == 1 ? true : (initDES(key) ? true : initDES(key))) {
			try {
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
					rsa.init(opmode, opmode == Cipher.ENCRYPT_MODE ? (RSAPublicKey) rsaKey.getPublic()
							: (RSAPrivateKey) rsaKey.getPrivate());
					result = new String(rsa.doFinal(string.getBytes("ISO-8859-1")), "ISO-8859-1");
				}
			} catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException
					| UnsupportedEncodingException e) {
				System.out.println((opmode == Cipher.ENCRYPT_MODE ? "加密" : "解密") + "失败：" + e.getMessage());
			}
		}
		return result;
	}

	/**
	 * RSA加密
	 * 
	 * @param string
	 * @return
	 */
	public static String rsa(String string) {
		return cryptDES(string, Cipher.ENCRYPT_MODE, "RSA");
	}

	/**
	 * DES3加密
	 * 
	 * @param string
	 * @return
	 */
	public static String des3(String string) {
		return cryptDES(string, Cipher.ENCRYPT_MODE, "DESede");
	}

	/**
	 * AES加密
	 * 
	 * @param string
	 * @return
	 */
	public static String aes(String string) {
		return cryptDES(string, Cipher.ENCRYPT_MODE, "AES");
	}

	/**
	 * DES加密
	 * 
	 * @param string
	 * @return
	 */
	public static String des(String string) {
		return cryptDES(string, Cipher.ENCRYPT_MODE, "DES");
	}

	/**
	 * BASE64加密
	 * 
	 * @param string
	 * @return
	 */
	public static String base64(String string) {
		return new BASE64Encoder().encodeBuffer(string.getBytes());
	}

	/**
	 * SHA加密，返回指定进制格式
	 * 
	 * @param string
	 * @param scale
	 * @return
	 */
	public static String sha(String string, int scale) {
		return messageDigest("SHA", string, scale);
	}

	/**
	 * SHA加密，返回32进制格式
	 * 
	 * @param string
	 * @return
	 */
	public static String sha(String string) {
		return messageDigest("SHA", string, 32);
	}

	/**
	 * MD5加密，返回指定进制格式
	 * 
	 * @param string
	 * @param scale
	 * @return
	 */
	public static String md5(String string, int scale) {
		return messageDigest("MD5", string, scale);
	}

	/**
	 * MD5加密，返回16进制格式
	 * 
	 * @param string
	 * @return
	 */
	public static String md5(String string) {
		return messageDigest("MD5", string, 16);
	}

	/**
	 * 消息摘要算法，单向加密
	 * 
	 * @param key
	 * @param string
	 * @param scale
	 * @return
	 */
	private static String messageDigest(String key, String string, int scale) {
		String code = "";
		try {
			MessageDigest md = MessageDigest.getInstance(key);
			md.update(string.getBytes());
			BigInteger bigInteger = new BigInteger(md.digest());
			code = bigInteger.toString(scale);
		} catch (Exception e) {
			System.out.println("加密失败：" + e.getMessage());
		}
		return code;
	}
}
