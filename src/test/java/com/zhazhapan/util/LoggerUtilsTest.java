package com.zhazhapan.util;

import org.junit.Test;

public class LoggerUtilsTest {

    @Test
    public void getLogger() {
        LoggerUtils.getLogger(this).info("test get logger via object");
        LoggerUtils.getLogger(null).error("test get logger via null");
    }

    @Test
    public void info() {
        LoggerUtils.info("today is a nice day");
        LoggerUtils.info(this, "%s is fall in loved", "god");
    }
}