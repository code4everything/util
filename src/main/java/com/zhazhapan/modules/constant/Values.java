/**
 * 
 */
package com.zhazhapan.modules.constant;

import java.io.File;

/**
 * @author pantao
 *
 */
public class Values {

	/**
	 * 用户的主目录
	 */
	public static final String USER_HOME = System.getProperty("user.home");

	/**
	 * 系统分隔符
	 */
	public static final String SEPARATOR = File.separator;

	/**
	 * KB大小，相对B
	 */
	public static final long KB = 1024;

	/**
	 * MB大小，相对B
	 */
	public static final long MB = KB * 1024;

	/**
	 * GB大小，相对B
	 */
	public static final long GB = MB * 1024;

	/**
	 * TB大小，相对B
	 */
	public static final long TB = GB * 1024;

	/**
	 * 请求头
	 */
	public static final String USER_AGENT = "mozilla/5.0 (windows nt 10.0; win64; x64) applewebkit/537.36 (khtml, like gecko) chrome/59.0.3071.115 safari/537.36";

	public static final int DEFAULT_DOWNLOAD_REMAINING_CENTER = 1048576;

	/**
	 * 网页响应200
	 */
	public static final int RESPONSE_OK = 200;

	/**
	 * 数字 2
	 */
	public static final int TWO_INT = 2;

	/**
	 * 英文点号
	 */
	public static final String DOT_SIGN = ".";

	/**
	 * 符号“-.”
	 */
	public static final String NEGATIVE_DOT_SIGN = "-.";
}
