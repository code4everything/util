/**
 * 
 */
package com.zhazhapan.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.zhazhapan.modules.constant.Values;

/**
 * @author pantao
 *
 */
public class Formatter {

	/**
	 * 日志输出
	 */
	private static Logger logger = Logger.getLogger(Formatter.class);

	/**
	 * 单位KB
	 */
	private static final String SIZE_KB = "KB";

	/**
	 * 单位MB
	 */
	private static final String SIZE_MB = "MB";

	/**
	 * 单位GB
	 */
	private static final String SIZE_GB = "GB";

	/**
	 * 单位TB
	 */
	private static final String SIZE_TB = "TB";

	/**
	 * yyyy-MM-dd HH:mm:ss格式
	 */
	private static final SimpleDateFormat DATE_WITH_LONG_TIME = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * yyyy-MM-dd格式
	 */
	private static SimpleDateFormat DATE_WITHOUT_TIME = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * HH:mm:ss格式
	 */
	private static SimpleDateFormat LONG_TIME = new SimpleDateFormat("HH:mm:ss");

	/**
	 * HH:mm格式
	 */
	private static SimpleDateFormat SHORT_TIME = new SimpleDateFormat("HH:mm");

	/**
	 * 文件名匹配（不能包含非法字符）,忽略大小写
	 */
	public static final Pattern FILE_NAME_PATTERN = Pattern.compile("([^/\\\\:*\"<>|?]+\\.)*[^/\\\\:*\"<>|?]+(\\?.*)?$",
			Pattern.CASE_INSENSITIVE);

	/**
	 * 将字符串转换为INT型
	 * 
	 * @param string
	 *            需要转换的字符串
	 * @return {@link Integer}
	 */
	public static int stringToInt(String string) {
		return stringToInteger(string);
	}

	/**
	 * 格式文件大小
	 * 
	 * @param size
	 *            单位为B的文件大小
	 * @return 格式化为KB、MB、GB、TB的{@link String}
	 */
	public static String formatSize(long size) {
		if (size < Values.KB) {
			return size + " B";
		} else if (size < Values.MB) {
			return formatDecimal((double) size / Values.KB) + " KB";
		} else if (size < Values.GB) {
			return formatDecimal((double) size / Values.MB) + " MB";
		} else if (size < Values.TB) {
			return formatDecimal((double) size / Values.GB) + " GB";
		} else {
			return formatDecimal((double) size / Values.TB) + " TB";
		}
	}

	/**
	 * 将格式化后的大小转换成long型
	 * 
	 * @param size
	 *            格式为 xx.xx B、xx.xx KB、xx.xx MB、xx.xx GB、xx.xx TB 的字符串
	 * @return 单位为B的{@link Long}
	 */
	public static long sizeToLong(String size) {
		if (Checker.isNotEmpty(size)) {
			String num = size.split(" ")[0];
			double result = 0;
			if (size.contains(SIZE_TB)) {
				result = stringToDouble(num) * Values.TB;
			} else if (size.contains(SIZE_GB)) {
				result = stringToDouble(num) * Values.GB;
			} else if (size.contains(SIZE_MB)) {
				result = stringToDouble(num) * Values.MB;
			} else if (size.contains(SIZE_KB)) {
				result = stringToDouble(num) * Values.KB;
			} else {
				result = stringToDouble(num);
			}
			return (long) result;
		}
		return -1;
	}

	/**
	 * 将String转换成Double
	 * 
	 * @param string
	 *            需要转换为字符串
	 * @return 转换结果，如果数值非法，则返回-1
	 */
	public static double stringToDouble(String string) {
		if (Checker.isDecimal(string)) {
			return Double.parseDouble(string);
		}
		return -1;
	}

	/**
	 * 将String转换成Long
	 * 
	 * @param string
	 *            需要转换的字符串
	 * @return 转换结果，如果数值非法，则返回-1
	 */
	public static long stringToLong(String string) {
		if (Checker.isNumber(string)) {
			return Long.parseLong(string);
		}
		return -1;
	}

	/**
	 * 自定义格式化数值
	 * 
	 * @param number
	 *            需要格式化的数值
	 * @param format
	 *            格式，例如：#0.00
	 * @return {@link String}
	 */
	public static String customFormatDecimal(double number, String format) {
		return new DecimalFormat(format).format(number);
	}

	/**
	 * 使用默认方式格式化字符串，默认格式：#0.00
	 * 
	 * @param number
	 *            需要格式化的数值
	 * @return {@link String}
	 */
	public static String formatDecimal(double number) {
		return formatDecimal(number, "#0.00");
	}

	/**
	 * 自定义格式化数值
	 * 
	 * @param number
	 *            需要格式化的数值
	 * @param format
	 *            格式，例如：#0.00
	 * @return {@link String}
	 */
	public static String formatDecimal(double number, String format) {
		return customFormatDecimal(number, format);
	}

	/**
	 * 将时间戳转换为字符串
	 * 
	 * @param time
	 *            时间戳
	 * @return {@link String}
	 */
	public static synchronized String timeStampToString(long time) {
		return DATE_WITH_LONG_TIME.format(time);
	}

	/**
	 * 格式化JSON
	 * 
	 * @param string
	 *            JSON格式的字符串
	 * @return {@link String}
	 */
	public static String formatJson(String string) {
		String json;
		try {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			JsonParser jp = new JsonParser();
			JsonElement je = jp.parse(string);
			json = gson.toJson(je);
		} catch (Exception e) {
			logger.error("format json string error,json: " + string);
			json = string;
		}
		return json;
	}

	/**
	 * 转换为日期格式的字符串
	 * 
	 * @param date
	 *            日期
	 * @return 只有日期的{@link String}
	 */
	public static synchronized String dateToString(Date date) {
		return DATE_WITHOUT_TIME.format(Checker.checkDate(date));
	}

	/**
	 * 转换为日期和长时间格式的字符串
	 * 
	 * @param date
	 *            日期
	 * @return 日期+长时间的{@link String}
	 */
	public static synchronized String datetimeToString(Date date) {
		return DATE_WITH_LONG_TIME.format(Checker.checkDate(date));
	}

	/**
	 * 从文件路径中获取文件名
	 * 
	 * @param string
	 *            文件路径
	 * @return {@link String}
	 */
	public static String getFileName(String string) {
		if (Checker.isNotEmpty(string)) {
			try {
				string = URLDecoder.decode(string, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				logger.error("decode url '" + string + "' error use encoding utf-8, message: " + e.getMessage());
			}
			Matcher matcher = FILE_NAME_PATTERN.matcher(string);
			if (matcher.find() && Checker.isNotEmpty(matcher.group(0))) {
				String name = matcher.group(0).split("\\?")[0];
				if (Checker.isNotEmpty(name)) {
					return name;
				}
			}
		}
		return "undefined";
	}

	/**
	 * 将String转换成FLOAT
	 * 
	 * @param string
	 *            需要转换的字符串
	 * @return 转换结果，如果数值非法，则返回-1
	 */
	public static float stringToFloat(String string) {
		if (Checker.isDecimal(string)) {
			return Float.parseFloat(string);
		}
		return -1;
	}

	/**
	 * 将String转换成Integer
	 * 
	 * @param string
	 *            需要转换的字符串
	 * @return 转换结果，如果数值非法，则返回-1
	 */
	public static int stringToInteger(String string) {
		if (Checker.isNumber(string)) {
			return Integer.parseInt(string);
		}
		return -1;
	}

	/**
	 * 将字符串转换成没有时间日期
	 * 
	 * @param date
	 *            日期格式的字符串
	 * @return 没有时间的日期
	 */
	public static Date stringToDate(String date) {
		return stringToCustomDateTime(DATE_WITHOUT_TIME, date);
	}

	/**
	 * 将字符串转换成没有日期的长时间
	 * 
	 * @param time
	 *            时间格式的字符串
	 * @return 没有日期的长时间
	 */
	public static Date stringToLongTime(String time) {
		return stringToCustomDateTime(LONG_TIME, time);
	}

	/**
	 * 将字符串转换成没有日期的短时间
	 * 
	 * @param time
	 *            时间格式的字符串
	 * @return 没有日期的短时间
	 */
	public static Date stringToShortTime(String time) {
		return stringToCustomDateTime(SHORT_TIME, time);
	}

	/**
	 * 将字符串转换成自定义格式的日期
	 * 
	 * @param datetime
	 *            日期格式的字符串
	 * @param formatWay
	 *            自定义格式
	 * @return 自定义格式的日期
	 */
	public static Date stringToCustomDateTime(String datetime, String formatWay) {
		return stringToCustomDateTime(new SimpleDateFormat(formatWay), datetime);
	}

	/**
	 * 将字符串转换成包含时间日期
	 * 
	 * @param datetime
	 *            日期格式的字符串
	 * @return 包含时间的日期
	 */
	public static Date stringToDatetime(String datetime) {
		return stringToCustomDateTime(DATE_WITH_LONG_TIME, datetime);
	}

	/**
	 * 将字符串转换成自定义格式的日期
	 * 
	 * @param datetime
	 *            日期格式的字符串
	 * @param sdf
	 *            自定义的格式
	 * @return 自定义格式的日期
	 */
	public static Date stringToCustomDateTime(SimpleDateFormat sdf, String datetime) {
		try {
			return sdf.parse(datetime);
		} catch (Exception e) {
			logger.info("format string to date error: " + e.getMessage());
		}
		return null;
	}

	/**
	 * 将日期转换成本地格式
	 * 
	 * @param date
	 *            日期
	 * @return “yyyy年MM月dd日”格式的字符串
	 */
	public static String toLocalDate(Date date) {
		return datetimeToCustomString(new SimpleDateFormat("yyyy年MM月dd日"), date);
	}

	/**
	 * 将日期转换成长时间格式的字符串
	 * 
	 * @param time
	 *            日期
	 * @return 长时间格式的字符串
	 */
	public static String longTimeToString(Date time) {
		return datetimeToCustomString(LONG_TIME, time);
	}

	/**
	 * 将日期转换成短时间格式的字符串
	 * 
	 * @param time
	 *            日期
	 * @return 短时间格式的字符串
	 */
	public static String shortTimeToString(Date time) {
		return datetimeToCustomString(SHORT_TIME, time);
	}

	/**
	 * 将日期转换成自定义格式的字符串
	 * 
	 * @param datetime
	 *            日期
	 * @param formatWay
	 *            自定义格式
	 * @return 自定义格式的字符串
	 */
	public static String datetimeToCustomString(Date datetime, String formatWay) {
		return datetimeToCustomString(new SimpleDateFormat(formatWay), datetime);
	}

	/**
	 * 将日期转换成自定义格式的字符串
	 * 
	 * @param datetime
	 *            日期
	 * @param sdf
	 *            自定义格式
	 * @return 自定义格式的字符串
	 */
	public static String datetimeToCustomString(SimpleDateFormat sdf, Date datetime) {
		return Checker.isNull(datetime) ? "" : sdf.format(datetime);
	}

	/**
	 * 将整数格式化为指定长度的字符串
	 * 
	 * @param data
	 *            整数
	 * @param length
	 *            长度
	 * @return {@link String}
	 */
	public static String numberFormat(long data, int length) {
		return String.format("%0" + length + "d", data);
	}

	/**
	 * 将java.time.LocalDate转换为java.util.Date
	 * 
	 * @param localDate
	 *            java.time.LocalDate
	 * @return java.util.Date
	 */
	public static Date localDateToDate(LocalDate localDate) {
		ZoneId zone = ZoneId.systemDefault();
		Instant instant = localDate.atStartOfDay().atZone(zone).toInstant();
		return Date.from(instant);
	}

	/**
	 * 将java.util.Date转换为java.time.LocalDate
	 * 
	 * @param date
	 *            java.util.Date
	 * @return java.time.LocalDate
	 */
	public static LocalDate dateToLocalDate(Date date) {
		Instant instant = date.toInstant();
		ZoneId zone = ZoneId.systemDefault();
		return LocalDateTime.ofInstant(instant, zone).toLocalDate();
	}
}
