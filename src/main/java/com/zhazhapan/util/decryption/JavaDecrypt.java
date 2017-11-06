/**
 * 
 */
package com.zhazhapan.util.decryption;

import java.io.IOException;

import com.zhazhapan.util.encryption.JavaEncrypt;

import sun.misc.BASE64Decoder;

/**
 * @author pantao
 *
 */
@SuppressWarnings("restriction")
public class JavaDecrypt {

	public static String base64(String code) {
		String string = "";
		try {
			string = new String(new BASE64Decoder().decodeBuffer(code));
		} catch (IOException e) {
			System.out.println("解密失败：" + e.getMessage());
		}
		return string;
	}

	public static String des(String code) {
		return JavaEncrypt.decryptDES(code, "DES");
	}

	public static String des3(String code) {
		return JavaEncrypt.decryptDES(code, "DESede");
	}

	public static String aes(String code) {
		return JavaEncrypt.decryptDES(code, "AES");
	}

	public static String rsa(String code) {
		return JavaEncrypt.decryptDES(code, "RSA");
	}
}
