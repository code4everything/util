/**
 *
 */
package com.zhazhapan.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

/**
 * 发送邮件需要邮箱账号开启POP3/SMTP服务
 *
 * @author pantao
 */
public class MailSender {

    /**
     * 邮件服务器，默认使用QQ服务器
     */
    private static String host = "smtp.qq.com";
    /**
     * 个人名称
     */
    private static String personal = "personal";
    /**
     * 收件箱
     */
    private static String from = "";
    /**
     * 邮箱密码
     */
    private static String key = "";

    private static boolean sslEnable = true;

    /**
     * 邮件服务器端口
     */
    private static int port = 0;

    /**
     * 通过JSON配置
     *
     * @param jsonString {@link String}
     */
    public static void config(String jsonString) {
        config(JSON.parseObject(jsonString));
    }

    /**
     * 通过JSON配置
     *
     * @param jsonObject {@link JSONObject}
     */
    public static void config(JSONObject jsonObject) {
        config(jsonObject.getString("host"), jsonObject.getString("personal"), jsonObject.getString("from"),
                jsonObject.getString("key"), jsonObject.getInteger("port"));
        setSslEnable(jsonObject.getBoolean("ssl"));
    }

    /**
     * 配置邮箱
     *
     * @param host 邮件服务器
     * @param personal 个人名称
     * @param from 发件箱
     * @param key 密码
     * @param port 端口
     */
    public static void config(String host, String personal, String from, String key, int port) {
        config(host, personal, from, key);
        setPort(port);
    }

    /**
     * 配置邮箱
     *
     * @param mailHost 邮件服务器
     * @param personal 个人名称
     * @param from 发件箱
     * @param key 密码
     * @param port 端口
     */
    public static void config(MailHost mailHost, String personal, String from, String key, int port) {
        config(mailHost, personal, from, key);
        setPort(port);
    }

    /**
     * 配置邮箱
     *
     * @param mailHost 邮件服务器
     * @param personal 个人名称
     * @param from 发件箱
     * @param key 密码
     */
    public static void config(MailHost mailHost, String personal, String from, String key) {
        setHost(mailHost);
        setPersonal(personal);
        setFrom(from);
        setKey(key);
    }

    /**
     * 配置邮箱
     *
     * @param host 邮件服务器
     * @param personal 个人名称
     * @param from 发件箱
     * @param key 密码
     */
    public static void config(String host, String personal, String from, String key) {
        setHost(host);
        setPersonal(personal);
        setFrom(from);
        setKey(key);
    }

    /**
     * 发送邮件
     *
     * @param mailHost 邮件服务器
     * @param personal 个人名称
     * @param to 收件箱
     * @param title 标题
     * @param content 内容
     * @param from 收件箱
     * @param key 密码
     *
     * @throws Exception 异常
     */
    public static void sendMail(MailHost mailHost, String personal, String to, String title, String content, final
    String from, final String key) throws Exception {
        setHost(mailHost);
        setPersonal(personal);
        sendMail(to, title, content, from, key);
    }

    /**
     * 发送邮件
     *
     * @param host 邮件服务器
     * @param personal 个人名称
     * @param to 收件箱
     * @param title 标题
     * @param content 内容
     * @param from 收件箱
     * @param key 密码
     *
     * @throws Exception 异常
     */
    public static void sendMail(String host, String personal, String to, String title, String content, final String
            from, final String key) throws Exception {
        setHost(host);
        setPersonal(personal);
        sendMail(to, title, content, from, key);
    }

    /**
     * 发送邮件，调用此方法前请先检查邮件服务器是否已经设置，如果没有设置，请先设置{@link MailSender#setHost(String)}， 如不设置将使用默认的QQ邮件服务器
     *
     * @param to 收件箱
     * @param title 标题
     * @param content 内容
     * @param from 发件箱
     * @param key 密码
     *
     * @throws Exception 异常
     */
    public static void sendMail(String to, String title, String content, String from, String key) throws Exception {
        setFrom(from);
        setKey(key);
        sendMail(to, title, content);
    }

    /**
     * 发送邮件，调用此方法前请先检查邮件服务器是否已经设置，如果没有设置，请先设置{@link MailSender#setHost(String)}， 如不设置将使用默认的QQ邮件服务器
     *
     * @param to 收件箱
     * @param title 标题
     * @param content 内容
     *
     * @throws Exception 异常
     */
    public static void sendMail(String to, String title, String content) throws Exception {
        if (!Checker.isEmail(to)) {
            throw new Exception("this email address is not valid. please check it again");
        }
        // 获取系统属性
        Properties properties = System.getProperties();
        // 设置邮件服务器
        properties.setProperty("mail.smtp.host", host);
        if (port > 0) {
            properties.setProperty("mail.smtp.port", String.valueOf(port));
        }
        properties.put("mail.smtp.auth", "true");
        MailSSLSocketFactory sf;
        sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        properties.put("mail.smtp.ssl.enable", sslEnable);
        properties.put("mail.smtp.ssl.socketFactory", sf);
        // 获取session对象
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            public PasswordAuthentication getPasswordAuthentication() {
                // 发件人邮件用户名、密码
                return new PasswordAuthentication(from, key);
            }
        });

        // 创建默认的MimeMessage对象
        MimeMessage message = new MimeMessage(session);
        // Set From:头部头字段
        message.setFrom(new InternetAddress(from, personal, "UTF-8"));
        // Set To:头部头字段
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
        // Set Subject:头部头字段
        message.setSubject(title, "UTF-8");
        // 设置消息体
        message.setContent(content, "text/html;charset=UTF-8");
        message.setSentDate(new Date());
        // 发送消息
        Transport.send(message);
    }

    /**
     * 获取当前邮件服务器
     *
     * @return {@link String}
     */
    public static String getHost() {
        return host;
    }

    /**
     * 设置邮件服务器
     *
     * @param host {@link String}
     */
    public static void setHost(String host) {
        MailSender.host = host;
    }

    /**
     * 设置邮件服务器
     *
     * @param mailHost {@link MailHost}
     */
    public static void setHost(MailHost mailHost) {
        switch (mailHost) {
            case NE163:
                host = "smtp.163.com";
                break;
            case GMAIL:
                host = "smtp.gmail.com";
                break;
            case SINA:
                host = "smtp.sina.com";
                break;
            case OUTLOOK:
                host = "smtp-mail.outlook.com";
                break;
            default:
                host = "smtp.qq.com";
                break;
        }
    }

    /**
     * 获取个人名称
     *
     * @return {@link String}
     */
    public static String getPersonal() {
        return personal;
    }

    /**
     * 设置个人名称
     *
     * @param personal {@link String}
     */
    public static void setPersonal(String personal) {
        MailSender.personal = personal;
    }

    /**
     * 获取发件箱
     *
     * @return 发件箱
     */
    public static String getFrom() {
        return from;
    }

    /**
     * 设置发件箱
     *
     * @param from 发件箱
     */
    public static void setFrom(String from) {
        MailSender.from = from;
    }

    /**
     * 获取邮箱密码
     *
     * @return 密码
     */
    public static String getKey() {
        return key;
    }

    /**
     * 设置邮箱密码
     *
     * @param key 密码
     */
    public static void setKey(String key) {
        MailSender.key = key;
    }

    /**
     * 获取端口
     *
     * @return {@link Integer}
     */
    public static int getPort() {
        return port;
    }

    /**
     * 设置端口
     *
     * @param port {@link Integer}
     */
    public static void setPort(int port) {
        MailSender.port = port;
    }

    /**
     * 是否开启SSL
     *
     * @param sslEnable 是否开启SSL
     */
    public static void setSslEnable(boolean sslEnable) {
        MailSender.sslEnable = sslEnable;
    }

    public enum MailHost {
        /**
         * QQ邮件服务器
         */
        QQ,

        /**
         * 网易163邮件服务器
         */
        NE163,

        /**
         * 谷歌邮件服务器
         */
        GMAIL,

        /**
         * 新浪邮件服务器
         */
        SINA,

        /**
         * OutLook邮件服务器
         */
        OUTLOOK
    }
}
