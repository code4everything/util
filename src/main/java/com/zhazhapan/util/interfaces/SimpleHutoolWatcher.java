package com.zhazhapan.util.interfaces;

import java.nio.file.Path;
import java.nio.file.WatchEvent;

/**
 * @author pantao
 * @since 2018/10/11
 **/
public interface SimpleHutoolWatcher {

    /**
     * 文件发生变化
     *
     * @param event 时间
     * @param currentPath 当前路径
     */
    default void onModify(WatchEvent<?> event, Path currentPath) {}

    /**
     * 执行一些业务代码
     */
    default void doSomething() {}
}
