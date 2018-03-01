package com.zhazhapan.util;

import org.junit.Test;

/**
 * @author pantao
 * @since 2018/1/23
 */
public class CheckerTest {

    @Test
    public void testMacOS() {
        assert Checker.isMacOS();
    }
}
