package com.zhazhapan.util;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class WebUtilsTest {

    @Test
    public void testScriptFilter() {
        assertTrue("ff".equals(WebUtils.scriptFilter("<script>ss</script>ff")));
    }
}