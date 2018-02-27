package com.zhazhapan.util;

import javax.servlet.http.Cookie;

/**
 * @author pantao
 * @since 2018/1/26
 */
public class HttpUtils {

    /**
     * 通过名称获取Cookie
     *
     * @param name Cookie名
     * @param cookies cookie数组
     *
     * @return {@link Cookie}
     */
    public static Cookie getCookie(String name, Cookie[] cookies) {
        if (Checker.isNotNull(cookies)) {
            for (Cookie cookie : cookies) {
                if (name.equals(cookie.getName())) {
                    return cookie;
                }
            }
        }
        return null;
    }
}
