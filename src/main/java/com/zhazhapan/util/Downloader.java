/**
 * 
 */
package com.zhazhapan.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.log4j.Logger;

import com.zhazhapan.modules.constant.Values;

/**
 * @author pantao
 *
 */
public class Downloader {

	private static Logger logger = Logger.getLogger(Downloader.class);

	private static String storageFolder = Values.USER_HOME + Values.SEPARATOR + "util";

	public static void download(String storageFolder, String downloadURL) {
		Downloader.storageFolder = storageFolder;
		download(downloadURL);
	}

	/**
	 * 下载文件
	 */
	public static void download(String downloadURL) {
		if (Checker.isHyperLink(downloadURL) && checkDownloadPath()) {
			logger.info("ready for download url: " + downloadURL + " storage in " + storageFolder);
		} else {
			logger.info("url or storage path are invalidated, can't download");
			return;
		}
		int byteread = 0;
		String fname = checkPath(storageFolder + Values.SEPARATOR + Formatter.getFileName(downloadURL));
		String tmp = fname + ".tmp";
		File file = new File(tmp);
		String log = "download success from url '" + downloadURL + "' to local '" + file.getAbsolutePath() + "'";
		try {
			URL url = new URL(downloadURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(1000 * 6);
			conn.setRequestProperty("Charset", "UTF-8");
			conn.setRequestProperty("User-Agent", Values.USER_AGENT);
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setRequestProperty("Accept", "*/*");
			InputStream inStream = conn.getInputStream();
			if (conn.getResponseCode() != Values.RESPONSE_OK || conn.getContentLength() <= 0) {
				return;
			}
			FileOutputStream fs = new FileOutputStream(file);
			if (!file.exists()) {
				file.createNewFile();
			}
			byte[] buffer = new byte[1024];
			while ((byteread = inStream.read(buffer)) != -1) {
				fs.write(buffer, 0, byteread);
			}
			inStream.close();
			fs.close();
			file.renameTo(new File(fname));
			logger.info(log);
		} catch (IOException e) {
			log = log.replace("success", "error") + ", message: " + e.getMessage();
			logger.error(log);
		}
	}

	/**
	 * 检查文件路径是否存在
	 * 
	 * @param path
	 *            文件路径
	 * @return 文件的绝对路径，如果文件存在，则生成一个新路径
	 */
	private static String checkPath(String path) {
		File file = new File(path);
		int idx = path.lastIndexOf(".");
		String post = "";
		if (idx > -1) {
			post = path.substring(idx);
			path = path.substring(0, idx);
		}
		int i = 0;
		while (file.exists()) {
			file = new File(path + "_" + (++i) + post);
		}
		return file.getAbsolutePath();
	}

	private static boolean checkDownloadPath() {
		if (Checker.isNotEmpty(storageFolder)) {
			File file = new File(storageFolder);
			if (!file.exists()) {
				file.mkdirs();
				logger.info("mkdir '" + storageFolder + "' success");
			}
			return true;
		}
		return false;
	}

	public static String getStorageFolder() {
		return storageFolder;
	}

	public static void setStorageFolder(String storageFolder) {
		Downloader.storageFolder = storageFolder;
	}
}
