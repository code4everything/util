package com.zhazhapan.util;

import com.alibaba.fastjson.JSONObject;
import com.zhazhapan.modules.constant.ValueConsts;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import org.apache.log4j.Logger;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.regex.Pattern;

/**
 * @author pantao
 */
public class Utils {

    /**
     * 匹配字符串是否有数字
     */
    private static final Pattern HAS_DIGIT_PATTERN = Pattern.compile(".*[0-9]+.*");

    /**
     * UID字符集
     */
    private static final char[] UID = "0123456789abcdefghijklmnopqrstuvwxyz".toCharArray();

    private static final int UID_LENGTH = UID.length;

    /**
     * 日志输出
     */
    private static Logger logger = Logger.getLogger(Utils.class);

    private Utils() {}

    /**
     * 清除空格
     *
     * @param string 字符串
     *
     * @return 清除空格后的字符串
     *
     * @since 1.1.0
     */
    public static String replaceSpace(String string) {
        return string.replace(" ", "");
    }

    /**
     * 分割long型
     *
     * @param value {@link Long}
     * @param splitPoints 分割点
     *
     * @return int数组
     *
     * @since 1.0.9
     */
    public static int[] split(long value, int... splitPoints) {
        int[] vars = null;
        if (Checker.isNotNull(splitPoints)) {
            String val = String.valueOf(value);
            int len = splitPoints.length;
            vars = new int[len + 1];
            for (int i = 0; i < len; i++) {
                vars[i] = Integer.parseInt(val.substring(i, splitPoints[i]));
            }
            vars[len] = Integer.parseInt(val.substring(splitPoints[len - 1]));
        }
        return vars;
    }

    /**
     * 数字转uid字符集
     *
     * @param number 数字集
     *
     * @return {@link String}
     *
     * @since 1.0.9
     */
    public static String toUidString(int number) {
        StringBuilder builder = new StringBuilder();
        while (number > 0) {
            builder.append(UID[number % UID_LENGTH]);
            number /= UID_LENGTH;
        }
        return builder.toString();
    }

    /**
     * 加载JSON配置文件，使用系统默认编码
     *
     * @param jsonPath JSON文件路径
     * @param clazz 类
     * @param <T> 类型值
     *
     * @return Bean
     *
     * @throws IOException 异常
     * @since 1.0.8
     */
    public static <T> T loadJsonToBean(String jsonPath, Class<T> clazz) throws IOException {
        return loadJsonToBean(jsonPath, ValueConsts.NULL_STRING, clazz);
    }

    /**
     * 加载JSON配置文件
     *
     * @param jsonPath JSON文件路径
     * @param encoding 编码格式，为null时使用系统默认编码
     * @param clazz 类
     * @param <T> 类型值
     *
     * @return Bean
     *
     * @throws IOException 异常
     * @since 1.0.8
     */
    public static <T> T loadJsonToBean(String jsonPath, String encoding, Class<T> clazz) throws IOException {
        JSONObject jsonObject = JSONObject.parseObject(FileExecutor.readFileToString(new File(jsonPath), encoding));
        return JSONObject.toJavaObject(jsonObject, clazz);
    }

    /**
     * 裁剪字符串
     *
     * @param value 值
     * @param trim 需要裁剪的值
     *
     * @return 裁剪后的字符串
     *
     * @since 1.0.8
     */
    public static String trim(String value, String trim) {
        return leftTrim(rightTrim(value, trim), trim);
    }

    /**
     * 裁剪字符串
     *
     * @param value 值
     * @param leftTrim 需要裁剪的值
     *
     * @return 裁剪后的字符串
     *
     * @since 1.0.8
     */
    public static String leftTrim(String value, String leftTrim) {
        if (Checker.isEmpty(value) || Checker.isEmpty(leftTrim)) {
            return value;
        }
        return value.indexOf(leftTrim) == 0 ? leftTrim(value.substring(leftTrim.length()), leftTrim) : value;
    }

    /**
     * 裁剪字符串
     *
     * @param value 值
     * @param rightTrim 需要裁剪的值
     *
     * @return 裁剪后的字符串
     *
     * @since 1.0.8
     */
    public static String rightTrim(String value, String rightTrim) {
        if (Checker.isEmpty(value) || Checker.isEmpty(rightTrim)) {
            return value;
        }
        int idx = value.length() - rightTrim.length();
        return value.lastIndexOf(rightTrim) == idx ? rightTrim(value.substring(0, idx), rightTrim) : value;
    }

    /**
     * 获取当前系统名称
     *
     * @return {@link String}
     */
    public static String getCurrentOS() {
        return System.getProperties().getProperty("os.name").toLowerCase();
    }

    /**
     * 抽取字符串的数字，并转换为Double
     *
     * @param string {@link String}
     *
     * @return {@link Double}
     */
    public static double extractDouble(String string) {
        return Double.parseDouble(extractDigit(string));
    }

    /**
     * 抽取字符串的数字，并转换为Float
     *
     * @param string {@link String}
     *
     * @return {@link Float}
     */
    public static float extractFloat(String string) {
        return Float.parseFloat(extractDigit(string));
    }

    /**
     * 抽取字符串的数字，并转换为Short
     *
     * @param string {@link String}
     *
     * @return {@link Short}
     */
    public static short extractShort(String string) {
        return Short.parseShort(extractDigit(string).replace(".", ""));
    }

    /**
     * 抽取字符串的数字，并转换为Long
     *
     * @param string {@link String}
     *
     * @return {@link Long}
     */
    public static long extractLong(String string) {
        return Long.parseLong(extractDigit(string).replace(".", ""));
    }

    /**
     * 抽取字符串的数字，并转换为Integer
     *
     * @param string {@link String}
     *
     * @return {@link Integer}
     */
    public static int extractInt(String string) {
        return Integer.parseInt(extractDigit(string).replace(".", ""));
    }

    /**
     * 抽取字符串的数字（包括最后一个点号）
     *
     * @param string {@link String}
     *
     * @return {@link String}
     */
    public static String extractDigit(String string) {
        StringBuilder res = new StringBuilder();
        if (HAS_DIGIT_PATTERN.matcher(string).matches()) {
            string = string.replaceAll("(\\s|[a-zA-Z])+", "");
            res = new StringBuilder(string.indexOf("-") == 0 ? "-" : "");
            int dotIdx = string.lastIndexOf(".");
            for (int i = 0; i < string.length(); i++) {
                char c = string.charAt(i);
                if (Character.isDigit(c) || i == dotIdx) {
                    res.append(c);
                }
            }
            if (res.indexOf(ValueConsts.DOT_SIGN) == 0) {
                res.insert(0, "0");
            } else if (res.indexOf(ValueConsts.NEGATIVE_DOT_SIGN) == 0) {
                res = new StringBuilder("-0." + res.substring(2, res.length()));
            }
        }
        return res.toString();
    }

    /**
     * 返回多个字符串中长度最长的字符串
     *
     * @param strings 多个字符串
     *
     * @return {@link String}
     */
    public static String maxLengthString(String... strings) {
        String res = "";
        for (String string : strings) {
            if (string.length() > res.length()) {
                res = string;
            }
        }
        return res;
    }

    /**
     * 复制字符串至系统剪贴板
     *
     * @param string 需要复制的字符串
     */
    public static void copyToClipboard(String string) {
        ClipboardContent content = new ClipboardContent();
        content.putString(string);
        Clipboard.getSystemClipboard().setContent(content);
        logger.info("copy '" + string + "' to clipboard");
    }

    /**
     * 使用系统默认的浏览器打开超链接
     *
     * @param url 超链接
     *
     * @throws URISyntaxException 异常
     * @throws IOException 异常
     */
    public static void openLink(String url) throws IOException, URISyntaxException {
        Desktop.getDesktop().browse(new URI(url));
    }

    /**
     * 使用系统默认的方式打开文件
     *
     * @param path 路径
     *
     * @throws IOException 异常
     */
    public static void openFile(String path) throws IOException {
        openFile(new File(path));
    }

    /**
     * 使用系统默认的方式打开文件
     *
     * @param file 文件
     *
     * @throws IOException 异常
     */
    public static void openFile(File file) throws IOException {
        Desktop.getDesktop().open(file);
    }

    /**
     * 执行命令
     *
     * @param command 命令
     *
     * @throws IOException 异常
     * @since 1.0.8
     */
    public static void run(String command) throws IOException {
        Runtime.getRuntime().exec(command);
    }

    /**
     * 获取当前工作路径
     *
     * @return 路径
     *
     * @since 1.0.8
     */
    public static String getCurrentWorkDir() {
        return Paths.get(ValueConsts.DOT_SIGN).toAbsolutePath().normalize().toString();
    }

    /**
     * 获取数组中最大值
     *
     * @param nums 数组
     *
     * @return {@link Integer}
     */
    public static int getMaxValue(int... nums) {
        int max = 0;
        int last = nums.length - 1;
        for (int i = 0; i < last; i += ValueConsts.TWO_INT) {
            max = Integer.max(max, Integer.max(nums[i], nums[i + 1]));
        }
        return Integer.max(max, nums[last]);
    }
}
