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
}