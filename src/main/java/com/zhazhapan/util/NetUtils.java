package com.zhazhapan.util;

import com.zhazhapan.config.JsonParser;
import com.zhazhapan.modules.constant.ValueConsts;
import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.DomSerializer;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.jsoup.Jsoup;
import org.w3c.dom.Document;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.io.InputStream;
import java.net.*;

/**
 * @author pantao
 * @since 2018/1/19
 */
public class NetUtils {

    private NetUtils() {}

    /**
     * 获取ip归属地
     *
     * @param ip ip地址
     *
     * @return 归属地
     *
     * @throws IOException 异常
     * @throws XPathExpressionException 异常
     * @throws ParserConfigurationException 异常
     */
    public static String getLocationByIp(String ip) throws IOException, XPathExpressionException,
            ParserConfigurationException {
        return evaluate(ValueConsts.IP_REGION_XPATH, getHtmlFromUrl("http://ip.chinaz.com/" + ip));
    }

    /**
     * XPath解析HTML内容
     *
     * @param xpath xpath表达式
     * @param html html内容
     *
     * @return 解析结果
     *
     * @throws XPathExpressionException 异常
     * @throws ParserConfigurationException 异常
     */
    public static String evaluate(String xpath, String html) throws XPathExpressionException,
            ParserConfigurationException {
        HtmlCleaner hc = new HtmlCleaner();
        TagNode tn = hc.clean(html);
        Document document = new DomSerializer(new CleanerProperties()).createDOM(tn);
        XPath xPath = XPathFactory.newInstance().newXPath();
        return xPath.evaluate(xpath, document);
    }

    /**
     * 获取网页内容
     *
     * @param url 链接
     *
     * @return {@link String}
     *
     * @throws IOException 异常
     */
    public static String getHtmlFromUrl(String url) throws IOException {
        return getDocumentFromUrl(url).html();
    }

    /**
     * 获取HTML文档
     *
     * @param url 链接
     *
     * @return {@link org.jsoup.nodes.Document}
     *
     * @throws IOException 异常
     */
    public static org.jsoup.nodes.Document getDocumentFromUrl(String url) throws IOException {
        return Jsoup.connect(url).get();
    }

    /**
     * 获取计算机名
     *
     * @return 计算机名
     */
    public static String getComputerName() {
        return System.getenv().get("COMPUTERNAME");
    }

    /**
     * 获取系统名称
     *
     * @return 系统名称
     */
    public static String getSystemName() {
        return System.getProperty("os.name");
    }

    /**
     * 获取系统架构
     *
     * @return 系统架构
     */
    public static String getSystemArch() {
        return System.getProperty("os.arch");
    }

    /**
     * 获取系统版本
     *
     * @return 系统版本
     */
    public static String getSystemVersion() {
        return System.getProperty("os.version");
    }

    /**
     * 获取Mac地址
     *
     * @return mac地址
     *
     * @throws UnknownHostException 异常
     * @throws SocketException 异常
     */
    public static String getMacAddress() throws UnknownHostException, SocketException {
        byte[] mac = NetworkInterface.getByInetAddress(Inet4Address.getLocalHost()).getHardwareAddress();
        StringBuilder jointMac = new StringBuilder();
        for (byte aMac : mac) {
            String macPart = Integer.toHexString(aMac & 0xFF);
            jointMac.append(macPart.length() == 1 ? "0" + macPart : macPart).append("-");
        }
        String macFormat = jointMac.toString();
        return macFormat.substring(0, macFormat.length() - 1);
    }

    /**
     * 获取公网IP和归属地
     *
     * @return 公网ip、address，如：{"ip":"127.0.0.1","address":"you ip location"}
     */
    public static JsonParser getPublicIpAndLocation() throws IOException {
        return new JsonParser(new URL(ValueConsts.URL_OF_PUBLIC_IP_SEARCH));
    }

    /**
     * 获取本地ip地址
     *
     * @return ip
     *
     * @throws UnknownHostException 异常
     */
    public static String getLocalIp() throws UnknownHostException {
        return Inet4Address.getLocalHost().getHostAddress();
    }

    /**
     * 将URL转换成String
     *
     * @param url {@link URL}
     *
     * @return {@link String}
     */
    public static String urlToString(URL url) {
        return Checker.isNull(url) ? "" : url.toString().replaceAll("file:" + (Checker.isWindows() ? "/?" : ""), "");
    }

    /**
     * 获取URL中的数据
     *
     * @param url 网络链接
     *
     * @return {@link String}
     *
     * @throws IOException 异常
     */
    public static String getDataOfUrl(String url) throws IOException {
        return getDataOfUrl(new URL(url));
    }

    /**
     * 获取URL中的数据
     *
     * @param url 网络链接
     *
     * @return {@link String}
     *
     * @throws IOException 异常
     */
    public static String getDataOfUrl(URL url) throws IOException {
        return FileExecutor.read(getInputStreamOfUrl(url));
    }

    /**
     * 获取URL的InputStream对象
     *
     * @param url 网络链接
     *
     * @return {@link InputStream}
     *
     * @throws IOException 异常
     */
    public static InputStream getInputStreamOfUrl(String url) throws IOException {
        return getInputStreamOfUrl(new URL(url));
    }

    /**
     * 获取URL的InputStream对象
     *
     * @param url 网络链接
     *
     * @return {@link InputStream}
     *
     * @throws IOException 异常
     */
    public static InputStream getInputStreamOfUrl(URL url) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        return getInputStreamOfConnection(conn);
    }

    /**
     * 获取HttpURLConnection的InputStream对象
     *
     * @param connection 链接对象
     *
     * @return {@link InputStream}
     *
     * @throws IOException 异常
     */
    public static InputStream getInputStreamOfConnection(HttpURLConnection connection) throws IOException {
        connection.setConnectTimeout(1000 * 6);
        connection.setRequestProperty("Charset", "UTF-8");
        connection.setRequestProperty("User-Agent", ValueConsts.USER_AGENT[0]);
        connection.setRequestProperty("Connection", "Keep-Alive");
        connection.setRequestProperty("Accept", "*/*");
        return connection.getInputStream();
    }
}
