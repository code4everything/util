package com.zhazhapan.util;

import com.zhazhapan.modules.constant.ValueConsts;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author pantao
 */
public class Checker {

    /**
     * 超链接匹配，忽略大小写
     */
    public static final Pattern HYPER_LINK_PATTERN = Pattern.compile("^(https*://)?([^\\s&;\"':<>]+\\.)+[a-z0-9]+" +
            "(/[^\\s]*)*$", Pattern.CASE_INSENSITIVE);

    /**
     * 日期匹配
     */
    public static final Pattern DATE_PATTERN = Pattern.compile("^[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$");

    /**
     * 整数匹配
     */
    public static final Pattern NUMBER_PATTERN = Pattern.compile("^[0-9]+$");

    /**
     * 数字匹配
     */
    public static final Pattern DECIMAL_PATTERN = Pattern.compile("^[0-9]+(\\.[0-9]+)?$");

    /**
     * 邮箱匹配，忽略大小写
     */
    public static final Pattern EMAIL_PATTERN = Pattern.compile("^[0-9a-z\\-]+([0-9a-z\\-]|(\\.[0-9a-z\\-]+))" +
            "*@[0-9a-z\\-]+(\\.[0-9a-z\\-]+)+$", Pattern.CASE_INSENSITIVE);

    /**
     * 图片匹配
     */
    public static final Pattern IMAGES_PATTERN = Pattern.compile(".*\\.(bmp|gif|jpe?g|png|tiff?|pcx|tga|svg|pic)$",
            Pattern.CASE_INSENSITIVE);

    private Checker() {
    }

    /**
     * 判断文件是否是图片
     *
     * @param file 文件
     * @return {@link Boolean}
     */
    public static boolean isImage(File file) {
        return isNotNull(file) && isImage(file.getName());
    }

    /**
     * 判断文件是否是图片
     *
     * @param file 文件
     * @return {@link Boolean}
     */
    public static boolean isImage(String file) {
        return isNotEmpty(file) && IMAGES_PATTERN.matcher(file).matches();
    }

    /**
     * 文件是否不存在
     *
     * @param file 文件
     * @return 是否不存在
     */
    public static boolean isNotExists(String file) {
        return !isExists(file);
    }

    /**
     * 文件是否存在
     *
     * @param file 文件
     * @return 是否存在
     */
    public static boolean isExists(String file) {
        return new File(Checker.checkNull(file)).exists();
    }

    /**
     * 判断字符串的长度是否在某个范围
     *
     * @param string 字符串
     * @param min 最小长度
     * @param max 最大长度
     * @return {@link Boolean}
     */
    public static boolean isLimited(String string, int min, int max) {
        return isNotEmpty(string) && string.length() >= min && string.length() <= max;
    }

    /**
     * 判断当前系统是否是Windows
     *
     * @return {@link Boolean}
     */
    public static boolean isWindows() {
        return Utils.getCurrentOS().startsWith("windows");
    }

    /**
     * 判断当前系统是否是Mac
     *
     * @return {@link Boolean}
     */
    public static boolean isMacOS() {
        return Utils.getCurrentOS().startsWith("mac");
    }

    /**
     * 判断当前系统是否是Linux
     *
     * @return {@link Boolean}
     */
    public static boolean isLinux() {
        return Utils.getCurrentOS().startsWith("linux");
    }

    /**
     * 检查数组是否已经排好序
     *
     * @param nums 数组
     * @return {@link Boolean}
     */
    public static boolean isSorted(int[] nums) {
        boolean desc = nums[0] - nums[nums.length - 1] >= 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (!desc && nums[i] > nums[i + 1]) {
                return false;
            }
            if (desc && nums[i] < nums[i + 1]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 是否为日期格式
     *
     * @param date 需要判断的日期
     * @return {@link Boolean}
     */
    public static boolean isDate(String date) {
        return isNotNull(date) && DATE_PATTERN.matcher(date).matches();
    }

    /**
     * 替换字符之前检查字符串是否为空
     *
     * @param string 需要检测的字符串
     * @param oldChar 需要替换的字符
     * @param newChar 新的字符
     * @return {@link String}
     */
    public static String replace(String string, char oldChar, char newChar) {
        return checkNull(string).replace(oldChar, newChar);
    }

    /**
     * 替换字符串之前检查字符串是否为空
     *
     * @param string 需要检测的字符串
     * @param oldString 需要替换的字符串
     * @param newString 新的字符串
     * @return {@link String}
     */
    public static String replace(String string, String oldString, String newString) {
        return checkNull(string).replace(oldString, newString);
    }

    /**
     * 是否为邮箱格式
     *
     * @param email 需要判断的邮箱地址
     * @return {@link Boolean}
     */
    public static boolean isEmail(String email) {
        return isNotEmpty(email) && EMAIL_PATTERN.matcher(email).matches();
    }

    /**
     * 是否为数字（含小数）格式
     *
     * @param decimal 需要判断的数字
     * @return {@link Boolean}
     */
    public static boolean isDecimal(String decimal) {
        return isNotEmpty(decimal) && DECIMAL_PATTERN.matcher(decimal).matches();
    }

    /**
     * 是否为整数格式
     *
     * @param number 需要判断的整数
     * @return {@link Boolean}
     */
    public static boolean isNumber(String number) {
        return isNotEmpty(number) && NUMBER_PATTERN.matcher(number).matches();
    }

    /**
     * 对象是否为NULL
     *
     * @param object 需要判断的对象
     * @return {@link Boolean}
     */
    public static boolean isNull(Object object) {
        return object == null;
    }

    /**
     * 对象是否不为NULL
     *
     * @param object 需要判断的对象
     * @return {@link Boolean}
     */
    public static boolean isNotNull(Object object) {
        return !isNull(object);
    }

    /**
     * 字符串是否为NULL或空
     *
     * @param string 需要判断的字符串
     * @return {@link Boolean}
     * @deprecated 请使用isEmpty方法
     */
    public static boolean isNullOrEmpty(String string) {
        return isEmpty(string);
    }

    /**
     * 字符串是否为NULL或空
     *
     * @param string 需要判断的字符串
     * @return {@link Boolean}
     */
    public static boolean isEmpty(String string) {
        return isNull(string) || string.isEmpty();
    }

    /**
     * 字符串是否不为空
     *
     * @param string 需要判断的字符串
     * @return {@link Boolean}
     */
    public static boolean isNotEmpty(String string) {
        return !isEmpty(string);
    }

    /**
     * 检测字符串是否为NULL
     *
     * @param value 需要检测的字符串
     * @return {@link String}
     */
    public static String checkNull(String value) {
        return checkNull(value, ValueConsts.EMPTY_STRING);
    }

    /**
     * 检测字符串是否为NULL
     *
     * @param value 需要检测的字符串
     * @param elseValue string为null返回的字符串
     * @return {@link String}
     */
    public static String checkNull(String value, String elseValue) {
        return isNull(value) ? elseValue : value;
    }

    /**
     * 检查Double是否为null
     *
     * @param value 值
     * @param elseValue 为null返回的值
     * @return {@link Double}
     */
    public static Double checkNull(Double value, Double elseValue) {
        return isNull(value) ? elseValue : value;
    }

    /**
     * 检查Double是否为null
     *
     * @param value 值
     * @param elseValue 为null返回的值
     * @return {@link Double}
     */
    public static Double checkNull(Double value, double elseValue) {
        return checkNull(value, Double.valueOf(elseValue));
    }

    /**
     * 检查Double是否为null
     *
     * @param value 值
     * @return {@link Double}
     */
    public static Double checkNull(Double value) {
        return checkNull(value, 0);
    }

    /**
     * 检查Integer是否为null
     *
     * @param value 值
     * @param elseValue 为null返回的值
     * @return {@link Integer}
     */
    public static Integer checkNull(Integer value, Integer elseValue) {
        return isNull(value) ? elseValue : value;
    }

    /**
     * 检查Integer是否为null
     *
     * @param value 值
     * @param elseValue 为null返回的值
     * @return {@link Integer}
     */
    public static Integer checkNull(Integer value, int elseValue) {
        return checkNull(value, Integer.valueOf(elseValue));
    }

    /**
     * 检查Integer是否为null
     *
     * @param value 值
     * @return {@link Integer}
     */
    public static Integer checkNull(Integer value) {
        return checkNull(value, 0);
    }

    /**
     * 检查Long是否为null
     *
     * @param value 值
     * @param elseValue 为null返回的值
     * @return {@link Long}
     */
    public static Long checkNull(Long value, Long elseValue) {
        return isNull(value) ? elseValue : value;
    }

    /**
     * 检查Long是否为null
     *
     * @param value 值
     * @param elseValue 为null返回的值
     * @return {@link Long}
     */
    public static Long checkNull(Long value, long elseValue) {
        return checkNull(value, Long.valueOf(elseValue));
    }

    /**
     * 检查Long是否为null
     *
     * @param value 值
     * @return {@link Long}
     */
    public static Long checkNull(Long value) {
        return checkNull(value, 0);
    }

    /**
     * 检查Float是否为null
     *
     * @param value 值
     * @param elseValue 为null返回的值
     * @return {@link Float}
     */
    public static Float checkNull(Float value, Float elseValue) {
        return isNull(value) ? elseValue : value;
    }

    /**
     * 检查Float是否为null
     *
     * @param value 值
     * @param elseValue 为null返回的值
     * @return {@link Float}
     */
    public static Float checkNull(Float value, float elseValue) {
        return checkNull(value, Float.valueOf(elseValue));
    }

    /**
     * 检查Float是否为null
     *
     * @param value 值
     * @return {@link Float}
     */
    public static Float checkNull(Float value) {
        return checkNull(value, 0);
    }

    /**
     * 检查Short是否为null
     *
     * @param value 值
     * @param elseValue 为null返回的值
     * @return {@link Short}
     */
    public static Short checkNull(Short value, Short elseValue) {
        return isNull(value) ? elseValue : value;
    }

    /**
     * 检查Short是否为null
     *
     * @param value 值
     * @param elseValue 为null返回的值
     * @return {@link Short}
     */
    public static Short checkNull(Short value, short elseValue) {
        return checkNull(value, Short.valueOf(elseValue));
    }

    /**
     * 检查Short是否为null
     *
     * @param value 值
     * @return {@link Short}
     */
    public static Short checkNull(Short value) {
        short s = 0;
        return checkNull(value, s);
    }

    /**
     * 检查Character是否为null
     *
     * @param value 值
     * @param elseValue 为null返回的值
     * @return {@link Character}
     */
    public static Character checkNull(Character value, Character elseValue) {
        return isNull(value) ? elseValue : value;
    }

    /**
     * 检查Character是否为null
     *
     * @param value 值
     * @param elseValue 为null返回的值
     * @return {@link Character}
     */
    public static Character checkNull(Character value, char elseValue) {
        return checkNull(value, Character.valueOf(elseValue));
    }

    /**
     * 检查Character是否为null
     *
     * @param value 值
     * @return {@link Character}
     */
    public static Character checkNull(Character value) {
        return checkNull(value, '0');
    }

    /**
     * 检测整数是否为NULL
     *
     * @param longNum 需要检测的整数
     * @return {@link Long}
     * @deprecated 这个方法没有作用
     */
    public static long checkNull(long longNum) {
        return longNum;
    }

    /**
     * 判断LIST是否不为空
     *
     * @param list 需要判断的LIST
     * @return {@link Boolean}
     */
    public static boolean isNotEmpty(List<?> list) {
        return !isEmpty(list);
    }

    /**
     * 判断LIST是否为空或NULL
     *
     * @param list 需要判断的LIST
     * @return {@link Boolean}
     */
    public static boolean isEmpty(List<?> list) {
        return isNull(list) || list.isEmpty();
    }

    /**
     * 判断MAP是否为不空
     *
     * @param map 需要判断的MAP
     * @return {@link Boolean}
     */
    public static boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }

    /**
     * 判断MAP是否为空或NULL
     *
     * @param map 需要判断的MAP
     * @return {@link Boolean}
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return isNull(map) || map.isEmpty();
    }

    /**
     * 是否为超链接
     *
     * @param hyperLink 需要匹配超链接
     * @return {@link Boolean}
     */
    public static boolean isHyperLink(String hyperLink) {
        return isNotEmpty(hyperLink) && HYPER_LINK_PATTERN.matcher(hyperLink).matches();
    }

    /**
     * 检测日期是否为NULL
     *
     * @param date 需要检测的日期
     * @return {@link Date}
     */
    public static Date checkDate(Date date) {
        return isNull(date) ? new Date() : date;
    }
}
