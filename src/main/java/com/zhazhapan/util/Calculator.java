/**
 * 
 */
package com.zhazhapan.util;

import java.math.BigDecimal;
import java.util.Stack;
import java.util.regex.Pattern;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * 简单计算器
 * 
 * @author pantao
 *
 */
public class Calculator {

	private static final Pattern CALCULATION_PATTERN = Pattern
			.compile("^((\\d+(\\.\\d+)?|\\d*\\(.+\\))(\\+|-|\\*|/))*(\\d+(\\.\\d+)?|\\d*\\(.+\\))=?$");

	/**
	 * 默认精度
	 */
	private static int precision = 100;

	/**
	 * 连续计算，使用默认精度（100）
	 * 
	 * @param formula
	 *            表达式
	 * @return {@link BigDecimal}
	 * @throws Exception
	 *             异常
	 */
	public static BigDecimal calculate(String formula) throws Exception {
		formula = formula.replaceAll("\\s", "");
		if (CALCULATION_PATTERN.matcher(formula).matches()) {
			return doCalculate(formula);
		} else {
			throw new Exception("calculation formula is not valid");
		}
	}

	/**
	 * 开始计算
	 * 
	 * @param formula
	 *            表达式
	 * @return {@link BigDecimal}
	 * @throws Exception
	 *             异常
	 */
	private static BigDecimal doCalculate(String formula) throws Exception {
		Stack<BigDecimal> stack = new Stack<BigDecimal>();
		BigDecimal result = BigDecimal.valueOf(0);
		BigDecimal number = BigDecimal.valueOf(0);
		char sign = '+';
		boolean isDecimal = false;
		BigDecimal decimalSign = new BigDecimal(0.1);

		for (int i = 0; i < formula.length(); i++) {
			char c = formula.charAt(i);
			if (Character.isDigit(c)) {
				BigDecimal num = new BigDecimal(c - '0');
				number = isDecimal ? number.add(num.multiply(decimalSign))
						: num.add(number.multiply(BigDecimal.valueOf(10)));
				decimalSign = decimalSign.multiply(BigDecimal.valueOf(0.1));
			} else if (c == '.') {
				isDecimal = true;
				decimalSign = BigDecimal.valueOf(0.1);
			} else if (c == '(') {
				BigDecimal[] res = calculateInlineFormula(i + 1, formula);
				number = number.compareTo(BigDecimal.valueOf(0)) == 0 ? res[0] : number.multiply(res[0]);
				i = res[1].intValue();
			}
			if ((!Character.isDigit(c) && c != '.' && c != '(') || i == formula.length() - 1) {
				if (sign == '+') {
					stack.push(number);
				} else if (sign == '-') {
					stack.push(number.multiply(BigDecimal.valueOf(-1)));
				} else if (sign == '*') {
					stack.push(number.multiply(stack.pop()));
				} else if (sign == '/') {
					stack.push(stack.pop().divide(number, getPrecision(), BigDecimal.ROUND_HALF_UP));
				}
				sign = c;
				isDecimal = false;
				number = BigDecimal.valueOf(0);
			}
		}
		for (BigDecimal i : stack) {
			result = result.add(i);
		}
		return result.setScale(getPrecision(), BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * 计算（）内的表达式
	 * 
	 * @param start
	 *            开始位置
	 * @param formula
	 *            表达式
	 * @return 长度为2的{@link BigDecimal}数组，位置0表示计算结果，位置1表示结束位置
	 * @throws Exception
	 *             异常
	 */
	private static BigDecimal[] calculateInlineFormula(int start, String formula) throws Exception {
		BigDecimal[] result = new BigDecimal[2];
		String inlineFormula = "";
		for (; start < formula.length(); start++) {
			char c = formula.charAt(start);
			if (c == '(') {
				BigDecimal[] res = calculateInlineFormula(start + 1, formula);
				inlineFormula += res[0];
				start = res[1].intValue();
			} else if (c == ')') {
				break;
			} else {
				inlineFormula += c;
			}
		}
		result[0] = calculate(inlineFormula);
		result[1] = BigDecimal.valueOf(start);
		return result;
	}

	/**
	 * 连续计算，可能导致数据失真
	 * 
	 * @param formula
	 *            表达式
	 * @return {@link Double}
	 * @throws ScriptException
	 *             异常
	 */
	public static Object calculateUseEval(String formula) throws ScriptException {
		ScriptEngineManager mgr = new ScriptEngineManager();
		ScriptEngine jsEngine = mgr.getEngineByName("JavaScript");
		return jsEngine.eval(formula);
	}

	/**
	 * 获取当前精度
	 * 
	 * @return {@link Integer}
	 */
	public static int getPrecision() {
		return precision;
	}

	/**
	 * 设置精度，即精确到多少位小数
	 * 
	 * @param precision
	 *            精度
	 */
	public static void setPrecision(int precision) {
		Calculator.precision = precision;
	}
}
