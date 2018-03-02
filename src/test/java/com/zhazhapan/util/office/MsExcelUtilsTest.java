package com.zhazhapan.util.office;

import com.zhazhapan.modules.constant.ValueConsts;
import com.zhazhapan.util.Formatter;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

public class MsExcelUtilsTest {

    private String path = ValueConsts.USER_DESKTOP + ValueConsts.SEPARATOR + "test.xlsx";

    @Test
    public void appendTable() throws NoSuchMethodException, IOException, IllegalAccessException,
            InvocationTargetException {
        String[][] values = new String[][]{{"1", "小明", "男", Formatter.datetimeToString(new Date())}, {"2", "小明", "女",
                Formatter.datetimeToString(new Date())}};
        MsExcelUtils.appendTable(values, null);
        MsExcelUtils.writeAndClose(path);
    }


    @Test
    public void createXssfWorkbook() {
    }

    @Test
    public void createXssfSheet() {
    }

    @Test
    public void readTable() throws IOException {
        MsExcelUtils.setXssfWorkbook(path);
        List<List<String>> values = MsExcelUtils.readTable(ValueConsts.ZERO_INT);
        values.forEach((value) -> {
            value.forEach(val -> System.out.print(val + "\t"));
            System.out.println();
        });
    }

    @Test
    public void writeTo() {
    }

    @Test
    public void writeAndClose() {
    }

    @Test
    public void getXssfWorkbook() {
    }

    @Test
    public void setXssfWorkbook() {
    }

    @Test
    public void setXssfWorkbook1() {
    }

    @Test
    public void getXssfSheet() {
    }

    @Test
    public void setXssfSheet() {
    }
}