/**
 *
 */
package com.zhazhapan.util;

import com.zhazhapan.modules.constant.Values;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author pantao
 */
public class Downloader {

    private static Logger logger = Logger.getLogger(Downloader.class);

    /**
     * 默认下载目录
     */
    private static String storageFolder = Values.USER_HOME + Values.SEPARATOR + "util";

    /**
     * 下载
     *
     * @param storageFolder 下载到指定目录
     * @param downloadURL   下载的URL
     * @return 是否下载成功
     */
    public static boolean download(String storageFolder, String downloadURL) {
        Downloader.storageFolder = storageFolder;
        return download(downloadURL);
    }

    /**
     * 下载文件
     *
     * @param downloadURL 下载的URL
     * @return 是否下载成功
     */
    public static boolean download(String downloadURL) {
        if (Checker.isHyperLink(downloadURL) && checkDownloadPath()) {
            logger.info("ready for download url: " + downloadURL + " storage in " + storageFolder);
        } else {
            logger.info("url or storage path are invalidated, can't download");
            return false;
        }
        int byteread = 0;
        String log = "download success from url '" + downloadURL + "' to local '";
        try {
            String fname = checkPath(storageFolder + Values.SEPARATOR + Formatter.getFileName(downloadURL));
            String tmp = fname + ".tmp";
            File file = new File(tmp);
            log += file.getAbsolutePath() + "'";
            URL url = new URL(downloadURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(1000 * 6);
            conn.setRequestProperty("Charset", "UTF-8");
            conn.setRequestProperty("User-Agent", Values.USER_AGENT);
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Accept", "*/*");
            InputStream inStream = conn.getInputStream();
            if (conn.getResponseCode() != Values.RESPONSE_OK || conn.getContentLength() <= 0) {
                return false;
            }
            FileOutputStream fs = new FileOutputStream(file);
            if (!file.exists()) {
                file.createNewFile();
            }
            byte[] buffer = new byte[1024];
            while ((byteread = inStream.read(buffer)) != -1) {
                fs.write(buffer, 0, byteread);
            }
            fs.flush();
            inStream.close();
            fs.close();
            file.renameTo(new File(fname));
            logger.info(log);
            return true;
        } catch (IOException e) {
            log = log.replace("success", "error") + ", message: " + e.getMessage();
            logger.error(log);
            return false;
        }
    }

    /**
     * 检查文件路径是否存在
     *
     * @param path 文件路径
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

    /**
     * 检测下载目录是否存在，不存在则创建
     *
     * @return {@link Boolean}
     */
    private static boolean checkDownloadPath() {
        if (Checker.isNotEmpty(storageFolder)) {
            File file = new File(storageFolder);
            if (!file.exists() && file.mkdirs()) {
                logger.info("mkdir '" + storageFolder + "' success");
            }
            return true;
        }
        return false;
    }

    /**
     * 获取下载目录
     *
     * @return {@link String}
     */
    public static String getStorageFolder() {
        return storageFolder;
    }

    /**
     * 设置下载的目录
     *
     * @param storageFolder 下载目录
     */
    public static void setStorageFolder(String storageFolder) {
        Downloader.storageFolder = storageFolder;
    }
}
