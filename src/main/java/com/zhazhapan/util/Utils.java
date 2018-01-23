/**
 *
 */
package com.zhazhapan.util;

import com.zhazhapan.modules.constant.Values;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.log4j.Logger;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Calendar;
import java.util.Date;
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
     * 日志输出
     */
    private static Logger logger = Logger.getLogger(Utils.class);

    private Utils() {}

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
            if (res.indexOf(Values.DOT_SIGN) == 0) {
                res.insert(0, "0");
            } else if (res.indexOf(Values.NEGATIVE_DOT_SIGN) == 0) {
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
     * 合并多个Short数组
     *
     * @param array 数组
     * @param arrays 数组
     *
     * @return 数组
     */
    @SafeVarargs
    public static short[] concatArrays(short[] array, short[]... arrays) {
        short[] res = array;
        for (short[] ele : arrays) {
            res = ArrayUtils.addAll(res, ele);
        }
        return res;
    }

    /**
     * 合并多个Long数组
     *
     * @param array 数组
     * @param arrays 数组
     *
     * @return 数组
     */
    @SafeVarargs
    public static long[] concatArrays(long[] array, long[]... arrays) {
        long[] res = array;
        for (long[] ele : arrays) {
            res = ArrayUtils.addAll(res, ele);
        }
        return res;
    }

    /**
     * 合并多个Float数组
     *
     * @param array 数组
     * @param arrays 数组
     *
     * @return 数组
     */
    @SafeVarargs
    public static float[] concatArrays(float[] array, float[]... arrays) {
        float[] res = array;
        for (float[] ele : arrays) {
            res = ArrayUtils.addAll(res, ele);
        }
        return res;
    }

    /**
     * 合并多个Double数组
     *
     * @param array 数组
     * @param arrays 数组
     *
     * @return 数组
     */
    @SafeVarargs
    public static double[] concatArrays(double[] array, double[]... arrays) {
        double[] res = array;
        for (double[] ele : arrays) {
            res = ArrayUtils.addAll(res, ele);
        }
        return res;
    }

    /**
     * 合并多个Character数组
     *
     * @param array 数组
     * @param arrays 数组
     *
     * @return 数组
     */
    @SafeVarargs
    public static char[] concatArrays(char[] array, char[]... arrays) {
        char[] res = array;
        for (char[] ele : arrays) {
            res = ArrayUtils.addAll(res, ele);
        }
        return res;
    }

    /**
     * 合并多个Byte数组
     *
     * @param array 数组
     * @param arrays 数组
     *
     * @return 数组
     */
    @SafeVarargs
    public static byte[] concatArrays(byte[] array, byte[]... arrays) {
        byte[] res = array;
        for (byte[] ele : arrays) {
            res = ArrayUtils.addAll(res, ele);
        }
        return res;
    }

    /**
     * 合并多个Boolean数组
     *
     * @param array 数组
     * @param arrays 数组
     *
     * @return 数组
     */
    @SafeVarargs
    public static boolean[] concatArrays(boolean[] array, boolean[]... arrays) {
        boolean[] res = array;
        for (boolean[] ele : arrays) {
            res = ArrayUtils.addAll(res, ele);
        }
        return res;
    }

    /**
     * 合并多个String数组
     *
     * @param array 数组
     * @param arrays 数组
     *
     * @return 数组
     */
    @SafeVarargs
    public static String[] concatArrays(String[] array, String[]... arrays) {
        String[] res = array;
        for (String[] ele : arrays) {
            res = ArrayUtils.addAll(res, ele);
        }
        return res;
    }

    /**
     * 合并多个Integer数组
     *
     * @param array 数组
     * @param arrays 数组
     *
     * @return 数组
     */
    @SafeVarargs
    public static int[] concatArrays(int[] array, int[]... arrays) {
        int[] res = array;
        for (int[] ele : arrays) {
            res = ArrayUtils.addAll(res, ele);
        }
        return res;
    }

    /**
     * 合并多个T数组
     *
     * @param array 数组
     * @param arrays 数组
     * @param <T> T类型
     *
     * @return 数组
     */
    @SafeVarargs
    public static <T> T[] concatArrays(T[] array, T[]... arrays) {
        T[] res = array;
        for (T[] ele : arrays) {
            res = ArrayUtils.addAll(res, ele);
        }
        return res;
    }

    /**
     * 获取当前年份
     *
     * @return 长度为4的{@link Integer}
     */
    public static int getCurrentYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    /**
     * 获取当前月份
     *
     * @return 长度2的{@link Integer}
     */
    public static int getCurrentMonth() {
        return Calendar.getInstance().get(Calendar.MONTH);
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
     * 获取日期格式的号数
     *
     * @param date 日期格式的字符串
     *
     * @return 类型为字符串的数值
     */
    public static String getDay(String date) {
        return date.split(" ")[0].substring(8);
    }

    /**
     * 获取日期格式的号数
     *
     * @param date 日期
     *
     * @return 类型为字符串的数值
     */
    public static String getDay(Date date) {
        return getDay(Formatter.dateToString(date));
    }

    /**
     * 获取日期格式的月份
     *
     * @param date 日期格式的字符串
     *
     * @return 类型为字符串的数值
     */
    public static String getMonth(String date) {
        return date.substring(5, 7);
    }

    /**
     * 获取日期格式的月份
     *
     * @param date 日期
     *
     * @return 类型为字符串的数值
     */
    public static String getMonth(Date date) {
        return getMonth(Formatter.dateToString(date));
    }

    /**
     * 获取日期格式的年份
     *
     * @param date 日期格式的字符串
     *
     * @return 类型为字符串的数值
     */
    public static String getYear(String date) {
        return date.substring(0, 4);
    }

    /**
     * 获取日期格式的年份
     *
     * @param date 日期
     *
     * @return 类型为字符串的数值
     */
    public static String getYear(Date date) {
        return getYear(Formatter.dateToString(date));
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
        for (int i = 0; i < last; i += Values.TWO_INT) {
            max = Integer.max(max, Integer.max(nums[i], nums[i + 1]));
        }
        return Integer.max(max, nums[last]);
    }

    /**
     * 合并两个升序数组
     *
     * @param nums1 数组
     * @param nums2 数组
     *
     * @return 数组
     */
    public static int[] mergeSortedArrays(int[] nums1, int[] nums2) {
        return mergeSortedArrays(nums1, nums2, false);
    }

    /**
     * 将两个有序数组（同序）合并成一个有序数组
     *
     * @param nums1 数组
     * @param nums2 数组
     * @param desc 是否为降序
     *
     * @return 数组
     */
    public static int[] mergeSortedArrays(int[] nums1, int[] nums2, boolean desc) {
        int len = nums1.length + nums2.length;
        int[] nums = new int[len];
        int m = 0;
        int n = 0;
        for (int i = 0; i < len; i++) {
            boolean inNums1 = n == nums2.length || (m != nums1.length && ((nums1[m] < nums2[n]) ^ desc));
            if (inNums1) {
                nums[i] = nums1[m++];
            } else {
                nums[i] = nums2[n++];
            }
        }
        return nums;
    }
}
