package com.zhazhapan.util;

import com.zhazhapan.modules.constant.ValueConsts;

/**
 * @author pantao
 * @date 2018/1/30
 */
public class WebUtils {

    private WebUtils() {}

    /**
     * 脚本过滤
     *
     * @param string {@link String}
     *
     * @return 过滤后的字符串
     */
    public static String scriptFilter(String string) {
        return Checker.checkNull(string).replaceAll(ValueConsts.SCRIPT_FILTER_PATTERN, "");
    }
}
