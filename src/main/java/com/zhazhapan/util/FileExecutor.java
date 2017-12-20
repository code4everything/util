/**
 * 
 */
package com.zhazhapan.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import org.apache.log4j.Logger;

import com.zhazhapan.modules.constant.Values;

/**
 * @author pantao
 *
 */
public class FileExecutor {

	private static Logger logger = Logger.getLogger(FileExecutor.class);

	/**
	 * 拆分文件
	 * 
	 * @param filePath
	 *            文件路径
	 * @param splitPoints
	 *            拆分点数组（函数将根据拆分点来分割文件）
	 * @throws IOException
	 *             异常
	 */
	public static void splitFile(String filePath, long[] splitPoints) throws IOException {
		splitFile(new File(filePath), splitPoints);
	}

	/**
	 * 拆分文件
	 * 
	 * @param file
	 *            文件
	 * @param splitPoints
	 *            拆分点数组（函数将根据拆分点来分割文件）
	 * @throws IOException
	 *             异常
	 */
	public static void splitFile(File file, long[] splitPoints) throws IOException {
		splitFile(file, splitPoints, file.getParent());
	}

	/**
	 * 拆分文件
	 * 
	 * @param filePath
	 *            文件路径
	 * @param splitPoints
	 *            拆分点数组（函数将根据拆分点来分割文件）
	 * @param storageFolder
	 *            保存路径
	 * @throws IOException
	 *             异常
	 */
	public static void splitFile(String filePath, long[] splitPoints, String storageFolder) throws IOException {
		splitFile(new File(filePath), splitPoints, storageFolder);
	}

	/**
	 * 拆分文件
	 * 
	 * @param file
	 *            文件
	 * @param splitPoints
	 *            拆分点数组（函数将根据拆分点来分割文件）
	 * @param storageFolder
	 *            保存路径
	 * @throws IOException
	 *             异常
	 */
	public static void splitFile(File file, long[] splitPoints, String storageFolder) throws IOException {
		splitPoints = Utils.concatArrays(new long[] { 0 }, splitPoints, new long[] { file.length() });
		String[] fileName = file.getName().split("\\.", 2);
		storageFolder += Values.SEPARATOR;
		logger.info("start to split file '" + file.getAbsolutePath() + "'");
		for (int i = 0; i < splitPoints.length - 1; i++) {
			long start = splitPoints[i], end = splitPoints[i + 1];
			long length = end - start;
			String filePath = storageFolder + fileName[0] + "_" + (i + 1) + "." + fileName[1];
			createNewFile(filePath);
			while (length > Integer.MAX_VALUE) {
				System.out.println("big file");
				saveFile(filePath, readFile(file, start, Integer.MAX_VALUE), true);
				start += Integer.MAX_VALUE;
				length -= Integer.MAX_VALUE;
			}
			if (length > 0) {
				saveFile(filePath, readFile(file, start, (int) length), true);
			}
		}
	}

	/**
	 * 重命名多个文件
	 * 
	 * @param filePath
	 *            文件路径数组
	 * @param fileNames
	 *            文件名，与文件数组一一对应
	 */
	public static void renameFiles(String[] filePath, String[] fileNames) {
		renameFiles(getFiles(filePath), fileNames);
	}

	/**
	 * 重命名多个文件
	 * 
	 * @param files
	 *            文件数组
	 * @param fileNames
	 *            文件名，与文件数组一一对应
	 */
	public static void renameFiles(File[] files, String[] fileNames) {
		int length = Integer.min(files.length, fileNames.length);
		for (int i = 0; i < length; i++) {
			String fileName = files[i].getParent() + Values.SEPARATOR + fileNames[i];
			if (!fileName.contains(".")) {
				fileName += files[i].getName().substring(files[i].getName().lastIndexOf("."));
			}
			files[i].renameTo(new File(fileName));
			logger.info("rename file '" + files[i].getAbsolutePath() + "' to '" + fileName + "'");
		}
	}

	/**
	 * 合并文件
	 * 
	 * @param filePath
	 *            文件路径数组
	 * @param destinationFilePath
	 *            目标文件路径
	 * @param filterRegex
	 *            过滤规则（正则表达式，所有文件使用同一个过滤规则，为空时不过滤）
	 * @throws IOException
	 *             异常
	 */
	public static void mergeFiles(String[] filePath, String destinationFilePath, String filterRegex)
			throws IOException {
		mergeFiles(getFiles(filePath), new File(destinationFilePath), filterRegex);
	}

	/**
	 * 合并文件
	 * 
	 * @param files
	 *            文件数组
	 * @param destinationFile
	 *            目标文件
	 * @param filterRegex
	 *            过滤规则（正则表达式，所有文件使用同一个过滤规则，为空时不过滤）
	 * @throws IOException
	 *             异常
	 */
	public static void mergeFiles(File[] files, File destinationFile, String filterRegex) throws IOException {
		createNewFile(destinationFile);
		for (File file : files) {
			mergeFiles(file, destinationFile, filterRegex);
		}
	}

	/**
	 * 合并文件
	 * 
	 * @param filePath
	 *            文件路径数组
	 * @param destinationFilePath
	 *            目标文件路径
	 * @param filterRegex
	 *            过滤规则数组（正则表达式，与文件数组一一对应，即第个文件使用一个过滤规则，为空时不过滤）
	 * @throws IOException
	 *             异常
	 */
	public static void mergeFiles(String[] filePath, String destinationFilePath, String[] filterRegex)
			throws IOException {
		mergeFiles(getFiles(filePath), new File(destinationFilePath), filterRegex);
	}

	/**
	 * 合并文件
	 * 
	 * @param files
	 *            文件数组
	 * @param destinationFile
	 *            目标文件
	 * @param filterRegex
	 *            过滤规则数组（正则表达式，与文件数组一一对应，即第个文件使用一个过滤规则，为空时不过滤）
	 * @throws IOException
	 *             异常
	 */
	public static void mergeFiles(File[] files, File destinationFile, String[] filterRegex) throws IOException {
		if (filterRegex.length < files.length) {
			filterRegex = Utils.concatArrays(filterRegex, new String[files.length - filterRegex.length]);
		}
		createNewFile(destinationFile);
		int i = 0;
		for (File file : files) {
			mergeFiles(file, destinationFile, filterRegex[i++]);
		}
	}

	/**
	 * 根据文件路径数组获取文件数组
	 * 
	 * @param filePath
	 *            文件路径数组
	 * @return 文件数组
	 */
	public static File[] getFiles(String[] filePath) {
		File[] files = new File[filePath.length];
		int i = 0;
		for (String file : filePath) {
			files[i++] = new File(file);
		}
		return files;
	}

	/**
	 * 合并文件
	 * 
	 * @param executor
	 *            {@link FileExecutor}对象
	 * @param file
	 *            待读取文件
	 * @param destinationFile
	 *            目标文件
	 * @param filter
	 *            过滤规则
	 * @throws IOException
	 *             异常
	 */
	private static void mergeFiles(File file, File destinationFile, String filter) throws IOException {
		String content = readFile(file);
		if (Checker.isNotEmpty(filter)) {
			content = content.replaceAll(filter, "");
		}
		saveFile(destinationFile, content, true);
	}

	/**
	 * 创建一个新的空文件（如果文件存在，则删除重新创建）
	 * 
	 * @param filePath
	 *            文件路径
	 * @throws IOException
	 *             异常
	 */
	public static void createNewFile(String filePath) throws IOException {
		createNewFile(new File(filePath));
	}

	/**
	 * 创建一个新的空文件（如果文件存在，则删除重新创建）
	 * 
	 * @param file
	 *            文件
	 * @throws IOException
	 *             异常
	 */
	public static void createNewFile(File file) throws IOException {
		if (file.exists()) {
			file.delete();
		}
		file.createNewFile();
	}

	/**
	 * 删除文件
	 * 
	 * @param filePath
	 *            文件路径
	 * @return 是否删除成功
	 */
	public static boolean deleteFile(String filePath) {
		return deleteFile(new File(filePath));
	}

	/**
	 * 删除文件
	 * 
	 * @param file
	 *            文件
	 * @return 是否删除成功
	 */
	public static boolean deleteFile(File file) {
		if (file.exists()) {
			if (file.isDirectory()) {
				String[] children = file.list();
				if (Checker.isNotNull(children)) {
					for (String child : children) {
						deleteFile(new File(child));
					}
				}
			}
			return file.delete();
		}
		return false;
	}

	/**
	 * 保存文件，覆盖原内容
	 * 
	 * @param path
	 *            文件路径
	 * @param content
	 *            内容
	 * @throws IOException
	 *             异常
	 */
	public static void saveFile(String path, String content) throws IOException {
		saveFile(path, content, false);
	}

	/**
	 * 保存文件
	 * 
	 * @param path
	 *            文件路径
	 * @param content
	 *            内容
	 * @param append
	 *            保存方式
	 * @throws IOException
	 *             异常
	 */
	public static void saveFile(String path, String content, boolean append) throws IOException {
		saveFile(new File(path), content, append);
	}

	/**
	 * 保存文件，覆盖原内容
	 * 
	 * @param file
	 *            文件
	 * @param content
	 *            内容
	 * @throws IOException
	 *             异常
	 */
	public static void saveFile(File file, String content) throws IOException {
		saveFile(file, content, false);
	}

	/**
	 * 读取文件
	 * 
	 * @param path
	 *            路径
	 * @return {@link String}
	 * @throws IOException
	 *             异常
	 */
	public static String readFile(String path) throws IOException {
		return readFile(new File(path));
	}

	/**
	 * 读取文件
	 * 
	 * @param file
	 *            文件
	 * @return {@link String}
	 * @throws IOException
	 *             异常
	 */
	public static String readFile(File file) throws IOException {
		StringBuilder content = new StringBuilder();
		String line;
		BufferedReader reader = new BufferedReader(new FileReader(file));
		while ((line = reader.readLine()) != null) {
			content.append(line + "\r\n");
		}
		reader.close();
		return content.toString();
	}

	public static String readFile(File file, long start, int length) throws IOException {
		InputStreamReader isr = new InputStreamReader(new FileInputStream(file));
		isr.skip(start);
		char[] cs = new char[length];
		isr.read(cs);
		isr.close();
		int i = cs.length - 1;
		for (; i >= 0; i--) {
			if (cs[i] != 0) {
				break;
			}
		}
		return new String(Arrays.copyOf(cs, i + 1));
	}

	/**
	 * 保存日志文件（插入方式）
	 * 
	 * @param logPath
	 *            路径
	 * @param content
	 *            内容
	 * @throws IOException
	 *             异常
	 */
	public static void saveLogFile(String logPath, String content) throws IOException {
		File file = new File(logPath);
		if (file.exists()) {
			content += readFile(file);
		}
		saveFile(file, content);
	}

	/**
	 * 保存文件
	 * 
	 * @param file
	 *            文件
	 * @param content
	 *            内容
	 * @param append
	 *            保存方式
	 * @throws IOException
	 *             异常
	 */
	public static synchronized void saveFile(File file, String content, boolean append) throws IOException {
		if (Checker.isNotNull(file)) {
			if (!file.exists()) {
				file.createNewFile();
			}
			BufferedWriter out = new BufferedWriter(new FileWriter(file, append));
			out.write(content);
			out.close();
			logger.info("save file '" + file.getAbsolutePath() + "' success");
		}
	}
}
