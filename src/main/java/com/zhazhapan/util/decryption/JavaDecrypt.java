/**
 * 
 */
package com.zhazhapan.util.decryption;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.zhazhapan.util.encryption.JavaEncrypt;

import sun.misc.BASE64Decoder;

/**
 * @author pantao
 *
 */
public class JavaDecrypt {

	/**
	 * base64解密
	 * 
	 * @param code
	 *            {@link String}
	 * @return {@link String}
	 * @throws IOException
	 *             异常
	 */
	public static String base64(String code) throws IOException {
		String string = "";
		string = new String(new BASE64Decoder().decodeBuffer(code));
		return string;
	}

	/**
	 * des解密
	 * 
	 * @param code
	 *            {@link String}
	 * @return {@link String}
	 * @throws InvalidKeyException
	 *             异常
	 * @throws NoSuchAlgorithmException
	 *             异常
	 * @throws NoSuchPaddingException
	 *             异常
	 * @throws UnsupportedEncodingException
	 *             异常
	 * @throws IllegalBlockSizeException
	 *             异常
	 * @throws BadPaddingException
	 *             异常
	 */
	public static String des(String code) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException,
			UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException {
		return JavaEncrypt.decryptDES(code, "DES");
	}

	/**
	 * des3解密
	 * 
	 * @param code
	 *            {@link String}
	 * @return {@link String}
	 * @throws InvalidKeyException
	 *             异常
	 * @throws NoSuchAlgorithmException
	 *             异常
	 * @throws NoSuchPaddingException
	 *             异常
	 * @throws UnsupportedEncodingException
	 *             异常
	 * @throws IllegalBlockSizeException
	 *             异常
	 * @throws BadPaddingException
	 *             异常
	 */
	public static String des3(String code) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException,
			UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException {
		return JavaEncrypt.decryptDES(code, "DESede");
	}

	/**
	 * aes解密
	 * 
	 * @param code
	 *            {@link String}
	 * @return {@link String}
	 * @throws InvalidKeyException
	 *             异常
	 * @throws NoSuchAlgorithmException
	 *             异常
	 * @throws NoSuchPaddingException
	 *             异常
	 * @throws UnsupportedEncodingException
	 *             异常
	 * @throws IllegalBlockSizeException
	 *             异常
	 * @throws BadPaddingException
	 *             异常
	 */
	public static String aes(String code) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException,
			UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException {
		return JavaEncrypt.decryptDES(code, "AES");
	}

	/**
	 * rsa解密
	 * 
	 * @param code
	 *            {@link String}
	 * @return {@link String}
	 * @throws InvalidKeyException
	 *             异常
	 * @throws NoSuchAlgorithmException
	 *             异常
	 * @throws NoSuchPaddingException
	 *             异常
	 * @throws UnsupportedEncodingException
	 *             异常
	 * @throws IllegalBlockSizeException
	 *             异常
	 * @throws BadPaddingException
	 *             异常
	 */
	public static String rsa(String code) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException,
			UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException {
		return JavaEncrypt.decryptDES(code, "RSA");
	}
}
