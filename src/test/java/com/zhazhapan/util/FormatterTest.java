package com.zhazhapan.util;

import org.junit.Test;

public class FormatterTest {

    @Test
    public void testToFinancialCharacter() throws Exception {
        assert "壹万陆仟肆佰零玖元零贰分".equals(Formatter.toFinancialCharacter(16409.02));
        assert "壹仟肆佰零玖元伍角".equals(Formatter.toFinancialCharacter(1409.50));
        assert "陆仟零柒元壹角肆分".equals(Formatter.toFinancialCharacter(6007.14));
        assert "壹仟陆佰捌拾元叁角贰分".equals(Formatter.toFinancialCharacter(1680.32));
        assert "叁佰贰拾伍元零肆分".equals(Formatter.toFinancialCharacter(325.04));
        assert "肆仟叁佰贰拾壹元整".equals(Formatter.toFinancialCharacter(4321.00));
        assert "壹分".equals(Formatter.toFinancialCharacter(0.01));

        assert Formatter.toFinancialCharacter(123456789012.34).equals("壹仟贰佰叁拾肆亿伍仟陆佰柒拾捌万玖仟零壹拾贰元叁角肆分");
        System.out.println(Formatter.toFinancialCharacter(100010001000.10));
        assert Formatter.toFinancialCharacter(100010001000.10).equals("壹仟亿零壹仟万零壹仟元壹角");
        assert Formatter.toFinancialCharacter(900990099009.99).equals("玖仟零玖亿玖仟零玖万玖仟零玖元玖角玖分");
        assert Formatter.toFinancialCharacter(543200010001.01).equals("伍仟肆佰叁拾贰亿零壹万零壹元零壹分");
        assert Formatter.toFinancialCharacter(100000001110.00).equals("壹仟亿零壹仟壹佰壹拾元整");
        assert Formatter.toFinancialCharacter(101000000001.11).equals("壹仟零壹拾亿零壹元壹角壹分");
        assert Formatter.toFinancialCharacter(100000000000.01).equals("壹仟亿元零壹分");
    }
}