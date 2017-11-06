/**
 * 
 */
package com.zhazhapan.util.encryption;

import com.zhazhapan.util.CryptUtil;

/**
 * @author pantao
 *
 */
public class SimpleEncryt {

	public static String mix(String string, int key) {
		return ascii(JavaEncrypt.base64(xor(string, key)), key);
	}

	public static String xor(String string, int key) {
		char[] encrypt = string.toCharArray();
		for (int i = 0; i < encrypt.length; i++) {
			encrypt[i] = (char) (encrypt[i] ^ key);
		}
		return new String(encrypt);
	}

	public static String xor(String string, String key) {
		return xor(string, CryptUtil.stringToKey(string));
	}

	public static String ascii(String string, int key) {
		String code = "";
		int[] keys = CryptUtil.getKeys(key);
		for (int i = 0; i < string.length(); i++) {
			code += (char) (string.charAt(i) + (i + keys[0]) % keys[1] % keys[2]);
			// code += (char) (string.charAt(i) + (i + 1) % 5 % 3);
		}
		return code;
	}

	public static String ascii(String string, String key) {
		return ascii(string, CryptUtil.stringToKey(key));
	}
}
