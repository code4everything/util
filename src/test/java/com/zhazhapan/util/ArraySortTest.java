/**
 * 
 */
package com.zhazhapan.util;

import org.junit.Test;

/**
 * @author pantao
 *
 */
public class ArraySortTest {

	@Test
	public void testHeapSort() {
		int[] arrays = { 12, 3, 56, 2, 1, 33, 55, 32 };
		ArraySort.bubbleSort(arrays);
		for (int i = 0; i < arrays.length; i++) {
			System.out.println(arrays[i]);
		}
	}
}
