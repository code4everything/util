package com.zhazhapan.util;

import com.google.common.net.HttpHeaders;
import com.zhazhapan.config.JsonParser;
import com.zhazhapan.modules.constant.ValueConsts;
import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.DomSerializer;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.jsoup.Jsoup;
import org.w3c.dom.Document;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author pantao
 * @since 2018/1/19
 */
public class NetUtils {

    /**
     * 协议
     *
     * @since 1.0.8
     */
    public static final String PROTOCOL_KEY = "$protocol";

    /**
     * 主机
     *
     * @since 1.0.8
     */
    public static final String HOST_KEY = "$host";

    /**
     * 路径
     *
     * @since 1.0.8
     */
    public static final String PATH_KEY = "$path";

    private NetUtils() {}

    /**
     * 是否是 ajax请求
     *
     * @param request {@link HttpServletRequest}
     *
     * @return {@link Boolean}
     *
     * @since 1.0.9
     */
    public static boolean isAjax(HttpServletRequest request) {
        return "XMLHttpRequest".equalsIgnoreCase(request.getHeader(HttpHeaders.X_REQUESTED_WITH));
    }

    /**
     * 解析URL
     *
     * @param url url
     *
     * @return {@link Map}
     *
     * @since 1.0.8
     */
    public static Map<String, String> parseUrl(String url) {
        Map<String, String> result = new HashMap<>(8);
        result.put(PROTOCOL_KEY, ValueConsts.EMPTY_STRING);
        result.put(HOST_KEY, ValueConsts.EMPTY_STRING);
        result.put(PATH_KEY, ValueConsts.EMPTY_STRING);
        if (Checker.isNotEmpty(url)) {
            String[] pros;
            final String protocolSplit = "://";
            if (url.contains(protocolSplit)) {
                pros = url.split(protocolSplit);
            } else {
                pros = new String[]{"", url};
            }
            // 设置主机、协议、路径
            result.put(PROTOCOL_KEY, pros[0]);
            if (pros.length < ValueConsts.TWO_INT) {
                pros = new String[]{pros[0], ValueConsts.EMPTY_STRING};
            }
            if (pros[1].contains(ValueConsts.SPLASH_STRING)) {
                int lastIndex = pros[1].lastIndexOf(ValueConsts.SPLASH_STRING);
                if (pros[1].startsWith(ValueConsts.SPLASH_STRING)) {
                    // 文件协议
                    result.put(PATH_KEY, pros[1].substring(1));
                } else if (pros[1].contains(ValueConsts.SPLASH_STRING)) {
                    int index = pros[1].indexOf("/");
                    // 设置主机
                    result.put(HOST_KEY, pros[1].substring(0, index));
                    // 设置参数
                    if (pros[1].contains(ValueConsts.QUESTION_MARK)) {
                        lastIndex = pros[1].indexOf(ValueConsts.QUESTION_MARK);
                        String[] params = pros[1].split("\\?")[1].split("&");
                        for (String param : params) {
                            String[] kv = param.split("=");
                            result.put(kv[0], kv[1]);
                        }
                    }
                    // 设置路径
                    if (lastIndex > index) {
                        String path = pros[1].substring(index + 1, lastIndex);
                        path = path.endsWith(ValueConsts.SPLASH_STRING) ? path.substring(0, path.length() - 1) : path;
                        result.put(PATH_KEY, path);
                    }
                } else {
                    result.put(HOST_KEY, pros[1]);
                }
            } else {
                result.put(HOST_KEY, pros[1]);
            }
        }
        return result;
    }

    /**
     * 脚本过滤
     *
     * @param string {@link String}
     *
     * @return 过滤后的字符串
     */
    public static String scriptFilter(String string) {
        return Checker.checkNull(string).replaceAll(ValueConsts.SCRIPT_FILTER_PATTERN, ValueConsts.EMPTY_STRING);
    }

    /**
     * 清除所有Cookie
     *
     * @param request {@link HttpServletRequest}
     * @param response {@link HttpServletResponse}
     *
     * @return {@link Boolean}
     *
     * @since 1.0.8
     */
    public static boolean clearCookie(HttpServletRequest request, HttpServletResponse response) {
        return clearCookie(request.getCookies(), response);
    }

    /**
     * 清除所有Cookie
     *
     * @param cookies {@link Cookie}
     * @param response {@link HttpServletResponse}
     *
     * @return {@link Boolean}
     *
     * @since 1.0.8
     */
    public static boolean clearCookie(Cookie[] cookies, HttpServletResponse response) {
        if (Checker.isNotEmpty(cookies)) {
            for (Cookie cookie : cookies) {
                removeCookie(cookie, response);
            }
            return true;
        }
        return false;
    }

    /**
     * 删除指定Cookie
     *
     * @param name Cookie名
     * @param request {@link HttpServletRequest}
     * @param response {@link HttpServletResponse}
     *
     * @return {@link Boolean}
     *
     * @since 1.0.8
     */
    public static boolean removeCookie(String name, HttpServletRequest request, HttpServletResponse response) {
        return removeCookie(name, request.getCookies(), response);
    }

    /**
     * 删除指定Cookie
     *
     * @param name Cookie名
     * @param cookies {@link Cookie}
     * @param response {@link HttpServletResponse}
     *
     * @return {@link Boolean}
     *
     * @since 1.0.8
     */
    public static boolean removeCookie(String name, Cookie[] cookies, HttpServletResponse response) {
        if (Checker.isNotEmpty(name) && Checker.isNotEmpty(cookies)) {
            for (Cookie cookie : cookies) {
                if (name.equals(cookie.getName())) {
                    return removeCookie(cookie, response);
                }
            }
        }
        return false;
    }

    /**
     * 删除指定Cookie
     *
     * @param cookie {@link Cookie}
     * @param response {@link HttpServletResponse}
     *
     * @return {@link Boolean}
     *
     * @since 1.0.8
     */
    public static boolean removeCookie(Cookie cookie, HttpServletResponse response) {
        if (Checker.isNotNull(cookie)) {
            cookie.setMaxAge(0);
            return addCookie(cookie, response);
        }
        return false;
    }

    /**
     * 添加Cookie
     *
     * @param cookie {@link Cookie}
     * @param response {@link HttpServletResponse}
     *
     * @return {@link Boolean}
     *
     * @since 1.0.8
     */
    public static boolean addCookie(Cookie cookie, HttpServletResponse response) {
        if (Checker.isNotNull(cookie) && Checker.isNotNull(response)) {
            response.addCookie(cookie);
            return true;
        }
        return false;
    }

    /**
     * 添加Cookie
     *
     * @param response {@link HttpServletResponse}
     * @param name Cookie名
     * @param value Cookie值
     *
     * @return {@link Boolean}
     *
     * @since 1.0.8
     */
    public static boolean addCookie(HttpServletResponse response, String name, String value) {
        return addCookie(new Cookie(name, value), response);
    }

    /**
     * 添加Cookie
     *
     * @param response {@link HttpServletResponse}
     * @param name Cookie名
     * @param value Cookie值
     * @param expiry 有效期
     * @param uri 路径
     *
     * @return {@link Boolean}
     *
     * @since 1.0.8
     */
    public static boolean addCookie(HttpServletResponse response, String name, String value, int expiry, String uri) {
        Cookie cookie = new Cookie(name, value);
        if (expiry > 0) {
            cookie.setMaxAge(expiry);
        }
        if (Checker.isNotEmpty(uri)) {
            cookie.setPath(uri);
        }
        return addCookie(cookie, response);
    }

    /**
     * 通过名称获取Cookie
     *
     * @param name Cookie名
     * @param request {@link HttpServletRequest}
     *
     * @return {@link Cookie}
     *
     * @since 1.0.8
     */
    public static Cookie getCookie(String name, HttpServletRequest request) {
        return getCookie(name, request.getCookies());
    }

    /**
     * 通过名称获取Cookie
     *
     * @param name Cookie名
     * @param cookies cookie数组
     *
     * @return {@link Cookie}
     */
    public static Cookie getCookie(String name, Cookie[] cookies) {
        if (Checker.isNotEmpty(cookies)) {
            for (Cookie cookie : cookies) {
                if (name.equals(cookie.getName())) {
                    return cookie;
                }
            }
        }
        return null;
    }

    /**
     * whois查询
     *
     * @param domain 域名
     *
     * @return whois信息
     *
     * @throws IOException 异常
     * @throws XPathExpressionException 异常
     * @throws ParserConfigurationException 异常
     */
    public static String whois(String domain) throws IOException, XPathExpressionException,
            ParserConfigurationException {
        String whois = evaluate(ValueConsts.WHOIS_DOMAIN_XPATH, getHtmlFromUrl("http://whois.chinaz.com/" + domain));
        return whois.replaceAll("\\[whois\\s?反查]", ValueConsts.EMPTY_STRING).replaceAll("\\s{2,}", "\r\n");
    }

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
     * @return 公网ip、address，如：{"ip":"127.0.0.1","address":"your ip location"}
     *
     * @throws IOException 异常
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
