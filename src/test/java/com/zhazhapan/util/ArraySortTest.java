/**
 * 
 */
package com.zhazhapan.util;

import java.util.Arrays;

import org.junit.Test;

/**
 * @author pantao
 *
 */
public class ArraySortTest {

	@Test
	public void testSort() {
		int[] arrays = { 12, 3, 56, 2, 1, 33, 55, 32 };
		ArrayUtils.shellSort(arrays);
		System.out.println(Arrays.toString(arrays));
	}
}
