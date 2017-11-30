/**
 * 
 */
package com.zhazhapan.util;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * @author pantao
 *
 */
public class CalculatorTest {

	@Test
	public void testCalculator() {
		String for1 = "2+4-4+78-26";
		String for2 = "2.56+4.33-4.445+78.1212-26.667";
		String for3 = "1.2+2.3";
		String for4 = "2.3*2";
		String for5 = "1.2+2.3*3.45-56.2/43=";
		String for6 = "5(23+12)+(23-21)/2";
		String for7 = "(23-9*7)/22+2(35-45+56/(12-9+4*(22/10-2)))";
		Calculator.setPrecision(5);
		try {
			assertTrue(54 == Calculator.calculate(for1).doubleValue());
			assertTrue(53.8992 == Calculator.calculate(for2).doubleValue());
			assertTrue(3.5 == Calculator.calculate(for3).doubleValue());
			assertTrue(4.6 == Calculator.calculate(for4).doubleValue());
			assertTrue(7.82802 == Calculator.calculate(for5).doubleValue());
			assertTrue(176 == Calculator.calculate(for6).doubleValue());
			assertTrue(7.65550 == Calculator.calculate(for7).doubleValue());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
