/**
 * 
 */
package com.zhazhapan.util;

import org.junit.Test;

import com.zhazhapan.modules.constant.Values;

/**
 * @author pantao
 *
 */
public class RandomTest {

	@Test
	public void testRandomColor() {
		for (int i = 0; i < Values.ONE_HUNDRED_INT; i++) {
			System.out.println(RandomUtils.getRandomColor().toString());
		}
	}

	@Test
	public void testRandomInteger() {
		for (int i = 0; i < Values.ONE_HUNDRED_INT; i++) {
			System.out.println(RandomUtils.getRandomInteger(0, 456));
		}
	}

	@Test
	public void testRandomDouble() {
		for (int i = 0; i < Values.ONE_HUNDRED_INT; i++) {
			System.out.println(RandomUtils.getRandomDouble(0, 999.999, 7));
		}
	}
}
