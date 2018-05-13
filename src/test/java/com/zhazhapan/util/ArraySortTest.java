/**
 *
 */
package com.zhazhapan.util;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author pantao
 */
public class ArraySortTest {

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
