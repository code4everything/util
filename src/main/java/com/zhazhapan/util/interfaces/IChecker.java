package com.zhazhapan.util.interfaces;

/**
 * 回调检查函数
 *
 * @author pantao
 * @since 2018/5/9
 */
@FunctionalInterface
public interface IChecker {

    /**
     * 自定义检查函数
     *
     * @return {@link Boolean}
     */
    boolean check();
}
