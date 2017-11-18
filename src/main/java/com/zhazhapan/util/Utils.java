/**
 * 
 */
package com.zhazhapan.util;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.log4j.Logger;

import com.zhazhapan.modules.constant.Values;

import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

/**
 * @author pantao
 *
 */
public class Utils {

	/**
	 * 日志输出
	 */
	private static Logger logger = Logger.getLogger(Utils.class);

	/**
	 * 合并多个Short数组
	 * 
	 * @param array
	 * @param arrays
	 * @return
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
	 * @param array
	 * @param arrays
	 * @return
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
	 * @param array
	 * @param arrays
	 * @return
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
	 * @param array
	 * @param arrays
	 * @return
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
	 * 合并多个Charactor数组
	 * 
	 * @param array
	 * @param arrays
	 * @return
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
	 * @param array
	 * @param arrays
	 * @return
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
	 * @param array
	 * @param arrays
	 * @return
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
	 * @param array
	 * @param arrays
	 * @return
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
	 * @param array
	 * @param arrays
	 * @return
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
	 * @param array
	 * @param arrays
	 * @return
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
	 * @return 长度为4的Integer
	 */
	public static int getCurrentYear() {
		return Calendar.getInstance().get(Calendar.YEAR);
	}

	/**
	 * 获取当前月份
	 * 
	 * @return 长度2的Integer
	 */
	public static int getCurrentMonth() {
		return Calendar.getInstance().get(Calendar.MONTH);
	}

	/**
	 * 复制字符串至系统剪贴板
	 * 
	 * @param string
	 *            需要复制的字符串
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
	 * @param url
	 *            超链接
	 */
	public static void openLink(String url) {
		try {
			Desktop.getDesktop().browse(new URI(url));
		} catch (IOException | URISyntaxException e) {
			logger.error("open url '" + url + "' failed, message: " + e.getMessage());
		}
	}

	/**
	 * 使用系统默认的方式打开文件
	 * 
	 * @param path
	 */
	public static void openFile(String path) {
		openFile(new File(path));
	}

	/**
	 * 使用系统默认的方式打开文件
	 * 
	 * @param file
	 */
	public static void openFile(File file) {
		try {
			Desktop.getDesktop().open(file);
		} catch (IOException e) {
			logger.error("open file error: " + e.getMessage());
		}
	}

	/**
	 * 获取日期格式的号数
	 * 
	 * @param date
	 *            日期格式的字符串
	 * @return 类型为字符串的数值
	 */
	public static String getDay(String date) {
		return date.split(" ")[0].substring(8);
	}

	/**
	 * 获取日期格式的号数
	 * 
	 * @param date
	 *            日期
	 * @return 类型为字符串的数值
	 */
	public static String getDay(Date date) {
		return getDay(Formatter.dateToString(date));
	}

	/**
	 * 获取日期格式的月份
	 * 
	 * @param date
	 *            日期格式的字符串
	 * @return 类型为字符串的数值
	 */
	public static String getMonth(String date) {
		return date.substring(5, 7);
	}

	/**
	 * 获取日期格式的月份
	 * 
	 * @param date
	 *            日期
	 * @return 类型为字符串的数值
	 */
	public static String getMonth(Date date) {
		return getMonth(Formatter.dateToString(date));
	}

	/**
	 * 获取日期格式的年份
	 * 
	 * @param date
	 *            日期格式的字符串
	 * @return 类型为字符串的数值
	 */
	public static String getYear(String date) {
		return date.substring(0, 4);
	}

	/**
	 * 获取日期格式的年份
	 * 
	 * @param date
	 *            日期
	 * @return 类型为字符串的数值
	 */
	public static String getYear(Date date) {
		return getYear(Formatter.dateToString(date));
	}

	/**
	 * 获取数组中最大值
	 * 
	 * @param nums
	 * @return
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
	 * @param nums1
	 * @param nums2
	 * @return
	 */
	public static int[] mergeSortedArrays(int[] nums1, int[] nums2) {
		return mergeSortedArrays(nums1, nums2, false);
	}

	/**
	 * 将两个有序数组（同序）合并成一个有序数组
	 * 
	 * @param nums1
	 * @param nums2
	 * @return
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
