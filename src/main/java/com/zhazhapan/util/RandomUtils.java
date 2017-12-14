/**
 * 
 */
package com.zhazhapan.util;

import java.util.Random;

import javafx.scene.paint.Color;

/**
 * @author pantao
 *
 */
public class RandomUtils extends Random {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2032548613375949428L;

	/**
	 * 获取随机颜色
	 * 
	 * @param opacity
	 *            不透明度
	 * @return {@link Color}
	 */
	public static Color getRandomColor(double opacity) {
		Random ran = new Random(47);
		int b = ran.nextInt(255);
		int r = 255 - b;
		return new Color(b + ran.nextInt(r), b + ran.nextInt(r), b + ran.nextInt(r), opacity);
	}

	/**
	 * 获取随机颜色，默认不透明
	 * 
	 * @return {@link Color}
	 */
	public static Color getRandomColor() {
		return getRandomColor(1d);
	}
}
