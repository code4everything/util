package com.zhazhapan.util;

import org.junit.Test;

import java.util.Locale;

public class FormatterTest {

    @Test
    public void testToFinancialCharacter() {
        assert "壹万陆仟肆佰零玖元零贰分".equals(Formatter.toFinancialCharacter(16409.02));
        assert "壹仟肆佰零玖元伍角".equals(Formatter.toFinancialCharacter(1409.50));
        assert "陆仟零柒元壹角肆分".equals(Formatter.toFinancialCharacter(6007.14));
        assert "壹仟陆佰捌拾元叁角贰分".equals(Formatter.toFinancialCharacter(1680.32));
        assert "叁佰贰拾伍元零肆分".equals(Formatter.toFinancialCharacter(325.04));
        assert "肆仟叁佰贰拾壹元整".equals(Formatter.toFinancialCharacter(4321.00));
        assert "壹分".equals(Formatter.toFinancialCharacter(0.01));

        assert Formatter.toFinancialCharacter(123456789012.34).equals("壹仟贰佰叁拾肆亿伍仟陆佰柒拾捌万玖仟零壹拾贰元叁角肆分");
        assert Formatter.toFinancialCharacter(100010001000.10).equals("壹仟亿零壹仟万零壹仟元壹角");
        assert Formatter.toFinancialCharacter(900990099009.99).equals("玖仟零玖亿玖仟零玖万玖仟零玖元玖角玖分");
        assert Formatter.toFinancialCharacter(543200010001.01).equals("伍仟肆佰叁拾贰亿零壹万零壹元零壹分");
        assert Formatter.toFinancialCharacter(100000001110.00).equals("壹仟亿零壹仟壹佰壹拾元整");
        assert Formatter.toFinancialCharacter(101000000001.11).equals("壹仟零壹拾亿零壹元壹角壹分");
        assert Formatter.toFinancialCharacter(100000000000.01).equals("壹仟亿元零壹分");
    }

    @Test
    public void toFinancialCharacter() {
    }

    @Test
    public void toCurrency() {
        System.out.println(Formatter.toCurrency(Locale.CANADA, 233.23));
        System.out.println(Formatter.toCurrency(566));
    }

    @Test
    public void toCurrency1() {
    }

    @Test
    public void stringToInt() {
    }

    @Test
    public void formatSize() {
    }

    @Test
    public void sizeToLong() {
    }

    @Test
    public void stringToDouble() {
    }

    @Test
    public void stringToLong() {
    }

    @Test
    public void customFormatDecimal() {
    }

    @Test
    public void formatDecimal() {
    }

    @Test
    public void formatDecimal1() {
    }

    @Test
    public void timeStampToString() {
    }

    @Test
    public void mapToJson() {
    }

    @Test
    public void listToJson() {
    }

    @Test
    public void formatJson() {
    }

    @Test
    public void dateToString() {
    }

    @Test
    public void datetimeToString() {
    }

    @Test
    public void getFileName() {
    }

    @Test
    public void stringToFloat() {
    }

    @Test
    public void stringToInteger() {
    }

    @Test
    public void stringToShort() {
    }

    @Test
    public void stringToDate() {
    }

    @Test
    public void stringToLongTime() {
    }

    @Test
    public void stringToShortTime() {
    }

    @Test
    public void stringToCustomDateTime() {
    }

    @Test
    public void stringToDatetime() {
    }

    @Test
    public void stringToCustomDateTime1() {
    }

    @Test
    public void toLocalDate() {
    }

    @Test
    public void longTimeToString() {
    }

    @Test
    public void shortTimeToString() {
    }

    @Test
    public void datetimeToCustomString() {
    }

    @Test
    public void datetimeToCustomString1() {
    }

    @Test
    public void numberFormat() {
    }

    @Test
    public void localDateToDate() {
    }

    @Test
    public void dateToLocalDate() {
    }
}