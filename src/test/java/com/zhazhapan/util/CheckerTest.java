package com.zhazhapan.util;

import com.zhazhapan.modules.constant.ValueConsts;
import org.junit.Test;

/**
 * @author pantao
 * @since 2018/1/23
 */
public class CheckerTest {

    private final String TRUE = "true";

    private final String FALSE = "false";

    @Test
    public void testMacOS() {
        assert Checker.isMacOS();
    }

    @Test
    public void testCheck() {
        for (int i = 0; i < 100; i++) {
            boolean b = RandomUtils.nextBoolean();
            assert Checker.check(TRUE, FALSE, b).equals(b ? TRUE : FALSE);
        }
        for (int i = 0; i < 100; i++) {
            boolean b = RandomUtils.nextBoolean();
            assert Checker.check(1, 2, b) == (b ? 1 : 2);
        }
        // 处理复杂的逻辑
        for (int i = 0; i < 100; i++) {
            // 推荐使用lambda表达式
            String res = Checker.check(TRUE, FALSE, () -> {
                String kk = RandomUtils.getRandomString(ValueConsts.NINE_INT);
                String mm = RandomUtils.getRandomString(ValueConsts.NINE_INT);
                return kk.length() / 2 < mm.indexOf(ValueConsts.COMMA_SIGN);
            });
            System.out.println(res);
        }
    }

    @Test
    public void objectIn() {
        String s = "ss";
        String[] mm = {"ss", "gg"};
        String[] gg = {"gg", "ss"};
        String[] hh = {"s", "g"};
        assert Checker.isIn(s, mm);
        assert Checker.isIn(s, gg);
        assert !Checker.isIn(s, hh);
    }

    @Test
    public void isImage() {
    }

    @Test
    public void isImage1() {
    }

    @Test
    public void isNotExists() {
    }

    @Test
    public void isExists() {
    }

    @Test
    public void isLimited() {
    }

    @Test
    public void isWindows() {
    }

    @Test
    public void isMacOS() {
    }

    @Test
    public void isLinux() {
    }

    @Test
    public void isSorted() {
    }

    @Test
    public void isDate() {
    }

    @Test
    public void replace() {
    }

    @Test
    public void replace1() {
    }

    @Test
    public void isEmail() {
    }

    @Test
    public void isDecimal() {
    }

    @Test
    public void isNumber() {
    }

    @Test
    public void isNull() {
    }

    @Test
    public void isNotNull() {
    }

    @Test
    public void isNullOrEmpty() {
    }

    @Test
    public void isEmpty() {
    }

    @Test
    public void isNotEmpty() {
    }

    @Test
    public void checkNull() {
    }

    @Test
    public void checkNull1() {
    }

    @Test
    public void checkNull2() {
    }

    @Test
    public void checkNull3() {
    }

    @Test
    public void checkNull4() {
    }

    @Test
    public void checkNull5() {
    }

    @Test
    public void checkNull6() {
    }

    @Test
    public void checkNull7() {
    }

    @Test
    public void checkNull8() {
    }

    @Test
    public void checkNull9() {
    }

    @Test
    public void checkNull10() {
    }

    @Test
    public void checkNull11() {
    }

    @Test
    public void checkNull12() {
    }

    @Test
    public void checkNull13() {
    }

    @Test
    public void checkNull14() {
    }

    @Test
    public void checkNull15() {
    }

    @Test
    public void checkNull16() {
    }

    @Test
    public void checkNull17() {
    }

    @Test
    public void checkNull18() {
    }

    @Test
    public void checkNull19() {
    }

    @Test
    public void check() {
    }

    @Test
    public void check1() {
    }

    @Test
    public void checkNull20() {
    }

    @Test
    public void isNotEmpty1() {
    }

    @Test
    public void isEmpty1() {
    }

    @Test
    public void isNotEmpty2() {
    }

    @Test
    public void isEmpty2() {
    }

    @Test
    public void isHyperLink() {
    }

    @Test
    public void checkDate() {
    }

    @Test
    public void checkNull21() {
    }

    @Test
    public void checkNull22() {
    }
}