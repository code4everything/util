/**
 *
 */
package com.zhazhapan.util;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author pantao
 */
public class ArraySortTest {

    @Test
    public void mapToArray() {
        Map<String, Integer> test = new HashMap<>();
        test.put("1", 1);
        test.put("2", 2);
        for (Integer i : ArrayUtils.mapToArray(test, Integer.class)) {
            System.out.println(i);
        }
    }

    @Test
    public void testSort() {
        int[] arrays = {12, 3, 56, 2, 1, 33, 55, 32};
        ArrayUtils.shellSort(arrays);
        System.out.println(Arrays.toString(arrays));
    }

    @Test
    public void testConcatArrays() {
        System.out.println(Arrays.toString(ArrayUtils.concatArrays(new int[]{1, 2}, new int[]{3, 4})));
    }

    @Test
    public void testUnique() {
        System.out.println(Arrays.toString(ArrayUtils.unique(new String[]{"123", "456", "123"})));
    }
}
