package com.zhazhapan.util.model;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.format.DateParser;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author pantao
 * @since 2018/9/24
 */
public class SimpleDateTime {

    private DateTime dateTime;

    private Integer year;

    private Integer month;

    private Integer day;

    private Integer hour;

    private Integer minute;

    private Integer second;

    public SimpleDateTime() {
        dateTime = new DateTime();
        parse();
    }

    public SimpleDateTime(Date date) {
        dateTime = new DateTime(date);
        parse();
    }

    public SimpleDateTime(Calendar calendar) {
        dateTime = new DateTime(calendar);
        parse();
    }

    public SimpleDateTime(long timeMillis) {
        dateTime = new DateTime(timeMillis);
        parse();
    }

    public SimpleDateTime(String dateStr, String format) {
        dateTime = new DateTime(dateStr, format);
        parse();
    }

    public SimpleDateTime(String dateStr, DateFormat dateFormat) {
        dateTime = new DateTime(dateStr, dateFormat);
        parse();
    }

    public SimpleDateTime(String dateStr, DateParser dateParser) {
        dateTime = new DateTime(dateStr, dateParser);
        parse();
    }

    public Integer getYear() {
        return year;
    }

    public Integer getMonth() {
        return month;
    }

    public Integer getDay() {
        return day;
    }

    public Integer getHour() {
        return hour;
    }

    public Integer getMinute() {
        return minute;
    }

    public Integer getSecond() {
        return second;
    }

    private void parse() {
        year = dateTime.getField(DateField.YEAR);
        month = dateTime.getField(DateField.MONTH);
        day = dateTime.getField(DateField.DAY_OF_MONTH);
        hour = dateTime.getField(DateField.HOUR_OF_DAY);
        minute = dateTime.getField(DateField.MINUTE);
        second = dateTime.getField(DateField.SECOND);
    }
}
