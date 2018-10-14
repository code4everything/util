package com.zhazhapan.util.interfaces;

import java.util.List;
import java.util.Map;

/**
 * @author pantao
 * @since 2018/10/14
 **/
public interface SimpleLogService<T> {

    /**
     * 保存日志
     *
     * @param log {@link T}
     *
     * @return {@link T}
     *
     * @since 1.1.1
     */
    T save(T log);

    /**
     * 保存异常信息
     *
     * @param log {@link T}
     * @param exceptionClass 异常类
     * @param exceptionDetail 异常详情
     *
     * @return {@link T}
     *
     * @since 1.1.1
     */
    T saveException(T log, String exceptionClass, String exceptionDetail);

    /**
     * 清楚过期的日志
     *
     * @since 1.1.1
     */
    default void removeExpired() {}

    /**
     * 查询日志
     *
     * @param criteria 查询日志
     *
     * @return 日志列表
     *
     * @since 1.1.1
     */
    default List<T> listBy(Map<String, Object> criteria) { return null;}
}
