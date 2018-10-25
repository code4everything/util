package com.zhazhapan.util.model;

import com.zhazhapan.util.DateUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

public class SimpleDateTimeTest {

    @Test
    public void getMonth() {
        SimpleDateTime dateTime = new SimpleDateTime();
        Assert.assertEquals(DateUtils.getMonth(new Date()), dateTime.getMonth().toString());
    }
}
