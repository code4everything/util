/**
 * 
 */
package com.zhazhapan.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.zhazhapan.modules.constant.Values;

/**
 * @author pantao
 *
 */
public class Downloader {

	private static Logger logger = Logger.getLogger(Downloader.class);

	private static String storageFolder = Values.USER_HOME + Values.SEPARATOR + "util";

	public static void downloadFromNet(String storageFolder, String downloadURL, boolean insertUUID) {
		Downloader.storageFolder = storageFolder;
		downloadFromNet(downloadURL, insertUUID);
	}

	public static void downloadFromNet(String downloadURL) {
		downloadFromNet(downloadURL, false);
	}

	/**
	 * 下载文件
	 */
	public static void downloadFromNet(String downloadURL, boolean insertUUID) {
		if (Checker.isHyperLink(downloadURL) && checkDownloadPath()) {
			logger.info("ready for download url: " + downloadURL + " storage in " + storageFolder);
		} else {
			logger.info("url or storage path are invalidated, can't download");
			return;
		}
		checkDownloadPath();
		int byteread = 0;
		String uuid = insertUUID ? UUID.randomUUID().toString() : "";
		File file = new File(storageFolder + Values.SEPARATOR + uuid + Formatter.getFileName(downloadURL));
		String log = "download success from url '" + downloadURL + "' to local '" + file.getAbsolutePath() + "'";
		try {
			URL url = new URL(downloadURL);
			URLConnection conn = url.openConnection();
			conn.setConnectTimeout(1000 * 60);
			conn.setRequestProperty("User-Agent", Values.USER_AGENT);
			InputStream inStream = conn.getInputStream();
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
			logger.info(log);
		} catch (IOException e) {
			log = log.replace("success", "error") + ", message: " + e.getMessage();
			logger.error(log);
		}
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
