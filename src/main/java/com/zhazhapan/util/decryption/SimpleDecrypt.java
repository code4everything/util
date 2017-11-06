/**
 * 
 */
package com.zhazhapan.util.decryption;

import com.zhazhapan.util.CryptUtil;
import com.zhazhapan.util.encryption.SimpleEncryt;

/**
 * @author pantao
 *
 */
public class SimpleDecrypt {

	public static String xor(String code, int key) {
		return SimpleEncryt.xor(code, key);
	}

	public static String ascii(String code, int key) {
		String string = "";
		int[] keys = CryptUtil.getKeys(key);
		for (int i = 0; i < code.length(); i++) {
			string += (char) (code.charAt(i) - (i + keys[0]) % keys[1] % keys[2]);
		}
		return string;
	}

	public static String ascii(String code, String key) {
		return ascii(code, CryptUtil.stringToKey(key));
	}

	public static String mix(String code, int key) {
		return xor(JavaDecrypt.base64(ascii(code, key)), key);
	}

}
