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

import org.apache.log4j.Logger;

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

	public static void openFile(String path) {
		try {
			Desktop.getDesktop().open(new File(path));
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
}
