package com.zhazhapan.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Vector;

/**
 * @author pantao
 * @since 2018/7/24
 */
public class ListUtils {

    public static final int DEFAULT_LIST_CAPACITY = 10;

    private ListUtils() {}

    /**
     * 生成一个 {@link ArrayList}
     *
     * @param capacity 初始化长度
     * @param values 值数组
     * @param <T> 值类型
     *
     * @return {@link ArrayList}
     *
     * @since 1.0.9
     */
    @SafeVarargs
    public static <T> ArrayList<T> getArrayList(int capacity, T... values) {
        return new ArrayList<T>(capacity) {{addAll(Arrays.asList(values));}};
    }

    /**
     * 生成一个 {@link ArrayList}
     *
     * @param values 值数组
     * @param <T> 值类型
     *
     * @return {@link ArrayList}
     *
     * @since 1.0.9
     */
    public static <T> ArrayList<T> getArrayList(T... values) {
        return getArrayList(DEFAULT_LIST_CAPACITY, values);
    }

    /**
     * 生成一个 {@link LinkedList}
     *
     * @param values 值数组
     * @param <T> 值类型
     *
     * @return {@link LinkedList}
     *
     * @since 1.0.9
     */
    @SafeVarargs
    public static <T> LinkedList<T> getLinkedList(T... values) {
        return new LinkedList<T>() {{addAll(Arrays.asList(values));}};
    }

    /**
     * 生成一个 {@link Vector}
     *
     * @param values 值数组
     * @param <T> 值类型
     *
     * @return {@link Vector}
     *
     * @since 1.0.9
     */
    public static <T> Vector<T> getVector(int capacity, T... values) {
        return new Vector<T>() {{addAll(Arrays.asList(values));}};
    }

    /**
     * 生成一个 {@link Vector}
     *
     * @param values 值数组
     * @param <T> 值类型
     *
     * @return {@link Vector}
     *
     * @since 1.0.9
     */
    public static <T> Vector<T> getVector(T... values) {
        return getVector(DEFAULT_LIST_CAPACITY, values);
    }
}
