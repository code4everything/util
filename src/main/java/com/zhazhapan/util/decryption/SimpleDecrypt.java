/**
 * 
 */
package com.zhazhapan.util.decryption;

import java.io.IOException;

import com.zhazhapan.util.CryptUtil;
import com.zhazhapan.util.encryption.SimpleEncryt;

/**
 * @author pantao
 *
 */
public class SimpleDecrypt {

	/**
	 * 异或解密
	 * 
	 * @param code
	 *            {@link String}
	 * @param key
	 *            {@link Integer}
	 * @return {@link String}
	 */
	public static String xor(String code, int key) {
		return SimpleEncryt.xor(code, key);
	}

	/**
	 * ascii解密
	 * 
	 * @param code
	 *            {@link String}
	 * @param key
	 *            {@link Integer}
	 * @return {@link String}
	 */
	public static String ascii(String code, int key) {
		String string = "";
		int[] keys = CryptUtil.getKeys(key);
		for (int i = 0; i < code.length(); i++) {
			string += (char) (code.charAt(i) - (i + keys[0]) % keys[1] % keys[2]);
		}
		return string;
	}

	/**
	 * sscii解密
	 * 
	 * @param code
	 *            {@link String}
	 * @param key
	 *            {@link String}
	 * @return {@link String}
	 */
	public static String ascii(String code, String key) {
		return ascii(code, CryptUtil.stringToKey(key));
	}

	/**
	 * 混合解密
	 * 
	 * @param code
	 *            {@link String}
	 * @param key
	 *            {@link Integer}
	 * @return {@link String}
	 * @throws IOException
	 *             异常
	 */
	public static String mix(String code, int key) throws IOException {
		return xor(JavaDecrypt.base64(ascii(code, key)), key);
	}

}
