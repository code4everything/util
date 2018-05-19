package com.zhazhapan.util;

import com.zhazhapan.modules.constant.ValueConsts;

/**
 * @author pantao
 * @since 2018/1/30
 * @deprecated 这个类将不再更新维护，请使用 {@link NetUtils}
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
