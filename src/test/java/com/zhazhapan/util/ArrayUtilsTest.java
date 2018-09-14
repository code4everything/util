package com.zhazhapan.util;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ArrayUtilsTest {

    /**
     * 实现复制数组，或者扩容数组
     */
    @Test
    public void copyOf() {
        // 定义一个数据源数组
        Integer[] array = {1, 2, 3};
        // 定义一个目标数组，用于测试
        Integer[] resultArray = {1, 2};
        // 以前复制数组
        Integer[] array1 = new Integer[2];
        System.arraycopy(array, 0, array1, 0, 2);
        assert Arrays.equals(resultArray, array1);
        // 现在复制数组，是不是比以前的跟简洁了，嘿嘿
        Integer[] array2 = ArrayUtils.copyOf(array, 2);
        assert Arrays.equals(array2, resultArray);
    }

    /**
     * 将 {@link Map} 中的 Value 转换成数组
     */
    @Test
    public void mapToArray() {
        // 定义一个集合
        Map<String, String> map = new HashMap<>();
        map.put("monday", "nice day");
        map.put("tuesday", "so bad");
        // 定义一个结果数组，需要说明的是结果数组的顺序是和集合相反的
        String[] resultArray = {"so bad", "nice day"};
        // 转换成数组
        String[] array = ArrayUtils.mapToArray(map, String.class);
        assert Arrays.equals(resultArray, array);
    }

    /**
     * 将 {@link Map}中的 Key 转换成数组
     */
    @Test
    public void mapKeyToArray() {
        // 定义一个集合
        Map<String, String> map = new HashMap<>();
        map.put("monday", "nice day");
        map.put("tuesday", "so bad");
        // 定义一个结果数组，需要说明的是结果数组的顺序是和集合相反的
        String[] resultArray = {"tuesday", "monday"};
        // 转换成数组
        String[] array = ArrayUtils.mapKeyToArray(map, String.class);
        assert Arrays.equals(resultArray, array);
    }

    @Test
    public void concatArrays() {
    }

    @Test
    public void concatArrays1() {
    }

    @Test
    public void concatArrays2() {
    }

    @Test
    public void concatArrays3() {
    }

    @Test
    public void concatArrays4() {
    }

    @Test
    public void concatArrays5() {
    }

    @Test
    public void concatArrays6() {
    }

    @Test
    public void concatArrays7() {
    }

    @Test
    public void concatArrays8() {
    }

    @Test
    public void concatArrays9() {
    }

    /**
     * 合并两个同序的数组
     */
    @Test
    public void mergeSortedArrays() {
        // 定义一个升序数组
        int[] array1 = {1, 3, 6, 9};
        // 再定义一个升序数组，嘿嘿
        int[] array2 = {2, 3, 7, 8};
        // 定义一个结果数组
        int[] resultArray = {1, 2, 3, 6, 7, 8, 9};
        // 合并两个同序数组，这里我们忽略相等的数字
        int[] array = ArrayUtils.mergeSortedArrays(array1, array2, true, false);
        assert Arrays.equals(resultArray, array);
    }

    @Test
    public void mergeSortedArrays1() {
    }

    @Test
    public void unique() {
    }

    @Test
    public void unique1() {
    }

    @Test
    public void unique2() {
    }

    @Test
    public void unique3() {
    }

    @Test
    public void unique4() {
    }

    @Test
    public void heapSort() {
    }

    @Test
    public void mergeSort() {
    }

    @Test
    public void shellSort() {
    }

    @Test
    public void selectSort() {
    }

    @Test
    public void quickSort() {
    }

    @Test
    public void insertSort() {
    }

    @Test
    public void bubbleSort() {
    }
}