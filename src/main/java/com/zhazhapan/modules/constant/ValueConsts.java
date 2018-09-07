package com.zhazhapan.modules.constant;

import javax.servlet.http.HttpServletResponse;
import java.io.File;

/**
 * @author pantao
 */
public class ValueConsts {

    public static final String ONE_STR = "1";

    public static final String TWO_STR = "2";

    public static final String THREE_STR = "3";

    public static final String FOUR_STR = "4";

    public static final String FIVE_STR = "5";

    public static final String SIX_STR = "6";

    public static final String SEVEN_STR = "7";

    public static final String EIGHT_STR = "8";

    public static final String NINE_STR = "9";

    public static final String COLON = ":";

    public static final String SUCCESS = "success";

    public static final String FAILED = "failed";

    public static final String ERROR_EN = "error";

    public static final int FORTY_EIGHT = 48;

    /**
     * 英文问号
     */
    public static final String QUESTION_MARK = "?";

    /**
     * whois查询的xpath表达式
     */
    public static final String WHOIS_DOMAIN_XPATH = "//ul[@class='WhoisLeft fl']";

    /**
     * 获取ip归属地的xpath
     */
    public static final String IP_REGION_XPATH = "//div[@class='WhoIpWrap jspu']/p[2]/span[4]";

    /**
     * 公网ip查询地址
     */
    public static final String URL_OF_PUBLIC_IP_SEARCH = "http://ip.chinaz.com/getip.aspx";

    /**
     * 井号
     */
    public static final String SHARP = "#";

    /**
     * 错误
     */
    public static final String ERROR = "错误";

    /**
     * 严重错误
     */
    public static final String FATAL_ERROR = "严重错误";

    /**
     * 数字9
     */
    public static final int NINE_INT = 9;

    /**
     * 英文逗号
     */
    public static final String COMMA_SIGN = ",";

    /**
     * 字符串“value”
     */
    public static final String VALUE_STRING = "value";

    /**
     * 字符串“key”
     */
    public static final String KEY_STRING = "key";

    /**
     * 数字32
     */
    public static final int THIRTY_TWO_INT = 32;

    /**
     * 字符串“token”
     */
    public static final String TOKEN_STRING = "token";

    /**
     * 分隔符“/”
     */
    public static final String SPLASH_STRING = "/";

    /**
     * 数字16
     */
    public static final int SIXTEEN_INT = 16;

    /**
     * 验证码上限
     */
    public static final int VERIFY_CODE_CEIL = 999999;

    /**
     * 验证码下限
     */
    public static final int VERIFY_CODE_FLOOR = 100000;

    /**
     * 字符串“password”
     */
    public static final String PASSWORD_STRING = "password";

    /**
     * 字符串“id”
     */
    public static final String ID_STRING = "id";

    /**
     * HttpServletResponse null
     */
    public static final HttpServletResponse NULL_RESPONSE = null;

    /**
     * 字符串null
     */
    public static final String NULL_STRING = null;

    /**
     * 字符串“user”
     */
    public static final String USER_STRING = "user";

    /**
     * 数字0
     */
    public static final int ZERO_INT = 0;

    /**
     * false
     */
    public static final boolean FALSE = false;

    /**
     * true
     */
    public static final boolean TRUE = true;

    /**
     * 空字符串
     */
    public static final String EMPTY_STRING = "";

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
    public static final String[] USER_AGENT = {"mozilla/5.0 (windows nt 10.0; win64; x64) applewebkit/537.36 (khtml, "
            + "like gecko) chrome/59.0.3071.115 safari/537.36", "Mozilla/5.0 (Windows NT  6.1; Win64; x64; rv:47" +
            ".0) Gecko/20100101  Firefox/47.0", "Mozilla/5.0 (Macintosh;Intel  Mac OS X x.y; rv:42.0) Gecko" +
            "/20100101  Firefox/42.0", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML,  like  Gecko)" +
            " Chrome/51.0.2704.103 Safari/537.36", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36  (KHTML, " +
            "like Gecko)  Chrome/51.0.2704.106 Safari/537.36 OPR/38.0.2220.41", "Mozilla/5.0 (iPhone;  CPU iPhone OS "
            + "10_3_1 like  Mac OS X) AppleWebKit/603.1.30 (KHTML, like Gecko) Version/10.0 Mobile/14E304 Safari" +
            "/602.1", "Mozilla/5.0 (compatible; MSIE 9.0; Windows Phone OS 7.5;  Trident/5.0; IEMobile/9.0)",
            "Mozilla/5.0 (iPhone; U; CPU like Mac  OS X; en) AppleWebKit/420+ (KHTML,  like  Gecko) Version/3.0 " +
                    "Mobile/1A543a Safari/419.3"};

    public static final int DEFAULT_DOWNLOAD_REMAINING_CENTER = 1048576;

    /**
     * 网页响应200
     */
    public static final int RESPONSE_OK = 200;

    /**
     * 数字 1
     */
    public static final int ONE_INT = 1;

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

    /**
     * 脚本匹配模式
     */
    public static final String SCRIPT_FILTER_PATTERN = "<script.*?>.*?</script>";

    /**
     * 桌面路径
     */
    public static final String USER_DESKTOP = USER_HOME + SEPARATOR + "Desktop";

    private ValueConsts() {}
}
