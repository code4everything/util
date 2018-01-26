/**
 *
 */
package com.zhazhapan.modules.constant;

import java.io.File;

/**
 * @author pantao
 */
public class ValueConsts {

    /**
     * toString
     */
    public static final String TO_STRING_METHOD_NAME = "toString";

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
    public static final String[] USER_AGENT = {"mozilla/5.0 (windows nt 10.0; win64; x64) applewebkit/537.36 (khtml, like gecko) chrome/59.0.3071.115 safari/537.36", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:47.0) Gecko/20100101 Firefox/47.0", "Mozilla/5.0 (Macintosh; Intel Mac OS X x.y; rv:42.0) Gecko/20100101 Firefox/42.0", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.106 Safari/537.36 OPR/38.0.2220.41", "Mozilla/5.0 (iPhone; CPU iPhone OS 10_3_1 like Mac OS X) AppleWebKit/603.1.30 (KHTML, like Gecko) Version/10.0 Mobile/14E304 Safari/602.1", "Mozilla/5.0 (compatible; MSIE 9.0; Windows Phone OS 7.5; Trident/5.0; IEMobile/9.0)", "Mozilla/5.0 (iPhone; U; CPU like Mac OS X; en) AppleWebKit/420+ (KHTML, like Gecko) Version/3.0 Mobile/1A543a Safari/419.3"};

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
     * 数字 3
     */
    public static final int THREE_INT = 3;

    /**
     * 数字 100
     */
    public static final int ONE_HUNDRED_INT = 100;

    /**
     * 英文点号
     */
    public static final String DOT_SIGN = ".";

    /**
     * 美元符号
     */
    public static final String DOLLAR_SIGN = "$";

    /**
     * 符号“-.”
     */
    public static final String NEGATIVE_DOT_SIGN = "-.";

    /**
     * URL本地路径的前缀
     */
    public static final String LOCAL_FILE_URL = "file:";

    /**
     * 空格
     */
    public static final String SPACE = " ";

    private ValueConsts() {}
}
