package com.zhazhapan.util;

import org.junit.Test;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class NetUtilsTest {

    @Test
    public void getComputerName() {
        System.out.println(NetUtils.getComputerName());
    }

    @Test
    public void evaluate() throws XPathExpressionException, IOException, ParserConfigurationException {
        String html = NetUtils.getHtmlFromUrl("http://ip.chinaz.com/125.69.41.90");
        System.out.println(NetUtils.evaluate("//div[@class='WhoIpWrap jspu']/p[2]/span[4]", html));
    }

    @Test
    public void getSystemName() {
    }

    @Test
    public void getSystemArch() {
    }

    @Test
    public void getSystemVersion() {
    }

    @Test
    public void getMacAddress() {
    }

    @Test
    public void getPublicIpAndLocation() {
    }

    @Test
    public void getLocalIp() {
    }

    @Test
    public void urlToString() {
    }

    @Test
    public void getDataOfUrl() {
    }

    @Test
    public void getDataOfUrl1() {
    }

    @Test
    public void getInputStreamOfUrl() {
    }

    @Test
    public void getInputStreamOfUrl1() {
    }

    @Test
    public void getInputStreamOfConnection() {
    }

    @Test
    public void whois() throws ParserConfigurationException, XPathExpressionException, IOException {
        System.out.println(NetUtils.whois("zhazhapan.com"));
    }

    @Test
    public void getLocationByIp() {
    }

    @Test
    public void getHtmlFromUrl() {
    }

    @Test
    public void getDocumentFromUrl() {
    }

    @Test
    public void parseUrl() {
        System.out.println(NetUtils.parseUrl("http://127.0.0.1:8080/heart/api/date" +
                ".html?love-you=forever&coding=everyday"));
        System.out.println(NetUtils.parseUrl("http://"));
        System.out.println(NetUtils.parseUrl("http"));
        System.out.println(NetUtils.parseUrl("https://zhazhapan.com"));
        System.out.println(NetUtils.parseUrl("https://github.zhazhapan.com/"));
    }

    @Test
    public void scriptFilter() {
    }

    @Test
    public void clearCookie() {
    }

    @Test
    public void clearCookie1() {
    }

    @Test
    public void removeCookie() {
    }

    @Test
    public void removeCookie1() {
    }

    @Test
    public void removeCookie2() {
    }

    @Test
    public void addCookie() {
    }

    @Test
    public void addCookie1() {
    }

    @Test
    public void addCookie2() {
    }

    @Test
    public void getCookie() {
    }

    @Test
    public void getCookie1() {
    }

    @Test
    public void post() {
        Map<String, String> map = new HashMap<>();
        map.put("email", "tao@zhazhapan.com");
        map.put("method", "1");
        System.out.println(NetUtils.post("http://localhost:49986/common/code", map));
    }

    @Test
    public void isAjax() {
    }
}