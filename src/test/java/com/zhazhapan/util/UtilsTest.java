/**
 * 
 */
package com.zhazhapan.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * @author pantao
 *
 */
public class UtilsTest {

	int[] test1 = { 78, 56, 34, 23, 12, 1 };
	int[] test2 = { 45, 33, 32, 23, 22, 13 };
	int[] test3 = { 1, 2, 4, 6, 9 };
	int[] test4 = { 3, 5, 7, 8 };

	@Test
	public void testConcatArrays() {
		int[] nums = Utils.concatArrays(test1, test2, test4, test3);
		for (int i : nums) {
			System.out.print(i + " ");
		}
	}

	@Test
	public void testGetMaxValue() {
		int[] test1 = { 1, 2, 3, 2, 123, 34 };
		assertEquals(123, Utils.getMaxValue(test1));
	}

	@Test
	public void testMerageSortedArrays() {
		assertTrue(Checker.isSorted(Utils.mergeSortedArrays(test1, test2, true)));
		assertTrue(Checker.isSorted(Utils.mergeSortedArrays(test4, test3)));
	}
}
