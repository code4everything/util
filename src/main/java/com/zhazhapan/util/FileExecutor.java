/**
 * 
 */
package com.zhazhapan.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.log4j.Logger;

/**
 * @author pantao
 *
 */
public class FileExecutor {

	private Logger logger = Logger.getLogger(FileExecutor.class);

	/**
	 * 保存文件，覆盖原内容
	 * 
	 * @param path
	 *            文件路径
	 * @param content
	 *            内容
	 */
	public void saveFile(String path, String content) {
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
	 */
	public void saveFile(String path, String content, boolean append) {
		saveFile(new File(path), content, append);
	}

	/**
	 * 保存文件，覆盖原内容
	 * 
	 * @param file
	 *            文件
	 * @param content
	 *            内容
	 */
	public void saveFile(File file, String content) {
		saveFile(file, content, false);
	}

	/**
	 * 读取文件
	 * 
	 * @param path
	 *            路径
	 * @return {@link String}
	 */
	public String readFile(String path) {
		return readFile(new File(path));
	}

	/**
	 * 读取文件
	 * 
	 * @param file
	 *            文件
	 * @return {@link String}
	 */
	public String readFile(File file) {
		StringBuilder content = new StringBuilder();
		String line;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			while ((line = reader.readLine()) != null) {
				content.append(line + "\r\n");
			}
			reader.close();
		} catch (IOException e) {
			logger.error("read file error, messages: " + e.getMessage());
		}
		return content.toString();
	}

	/**
	 * 保存日志文件（插入方式）
	 * 
	 * @param logPath
	 *            路径
	 * @param content
	 *            内容
	 */
	public void saveLogFile(String logPath, String content) {
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
	 */
	public void saveFile(File file, String content, boolean append) {
		if (Checker.isNotNull(file)) {
			try {
				if (!file.exists()) {
					file.createNewFile();
				}
				BufferedWriter out = new BufferedWriter(new FileWriter(file, append));
				out.write(content);
				out.close();
				logger.info("save file '" + file.getAbsolutePath() + "' success");
			} catch (IOException e) {
				logger.error("save file failed, messages: " + e.getMessage());
			}
		}
	}
}
