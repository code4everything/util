package com.zhazhapan.util;

import org.junit.Test;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;

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
}