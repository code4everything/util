package com.zhazhapan.util.office;

import com.zhazhapan.modules.constant.ValueConsts;
import com.zhazhapan.util.Checker;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author pantao
 * @since 2018/2/28
 */
public class MsExcelUtils {

    private static Logger logger = Logger.getLogger(MsWordUtils.class);

    private static XSSFWorkbook xssfWorkbook = null;

    private static XSSFSheet xssfSheet = null;

    private MsExcelUtils() {}

    /**
     * 创建工作簿
     */
    public static void createXssfWorkbook() {
        xssfWorkbook = new XSSFWorkbook();
    }

    /**
     * 创建工作表
     *
     * @param sheetName 工作表名
     */
    public static void createXssfSheet(String sheetName) {
        createXssfWorkbookIfNull();
        xssfSheet = xssfWorkbook.createSheet(sheetName);
    }

    /**
     * 添加一个表格
     *
     * @param values 值
     * @param styles 样式
     */
    public static void appendTable(String[][] values, HSSFCellStyle[][] styles) {
        createXssfSheetIfNull();
        if (Checker.isNotNull(values)) {
            for (int i = 0; i < values.length; i++) {
                XSSFRow row = xssfSheet.createRow(i);
                for (int j = 0; j < values[i].length; j++) {
                    XSSFCell cell = row.createCell(j);
                    cell.setCellValue(values[i][j]);
                    if (Checker.isNotNull(styles)) {
                        int rowIndex = i < styles.length ? i : styles.length - 1;
                        int colIndex = j < styles[rowIndex].length ? j : styles[rowIndex].length - 1;
                        cell.setCellStyle(styles[rowIndex][colIndex]);
                    }
                }
            }
        }
    }

    /**
     * 读取表格
     *
     * @param sheetIndex 表索性
     *
     * @return {@link List}
     */
    public static List<List<String>> readTable(int sheetIndex) {
        setXssfSheet(xssfWorkbook.getSheetAt(sheetIndex));
        return readTable();
    }

    /**
     * 读取表格
     *
     * @param sheetName 表名
     *
     * @return {@link List}
     */
    public static List<List<String>> readTable(String sheetName) {
        setXssfSheet(xssfWorkbook.getSheet(sheetName));
        return readTable();
    }

    /**
     * 读取表格
     *
     * @return {@link List}
     */
    public static List<List<String>> readTable() {
        List<List<String>> values = new ArrayList<>(ValueConsts.THIRTY_TWO_INT);
        for (int i = 0; i < xssfSheet.getLastRowNum() + 1; i++) {
            XSSFRow row = xssfSheet.getRow(i);
            if (Checker.isNotNull(row)) {
                List<String> value = new ArrayList<>(ValueConsts.THIRTY_TWO_INT);
                for (int j = 0; j < row.getLastCellNum(); j++) {
                    XSSFCell cell = row.getCell(j);
                    if (Checker.isNotNull(cell)) {
                        value.add(cell.getStringCellValue());
                    }
                }
                if (Checker.isNotEmpty(value)) {
                    values.add(value);
                }
            }
        }
        return values;
    }

    /**
     * 保存到本地，不关闭工作簿
     *
     * @param path 路径
     *
     * @throws IOException 异常
     * @throws NoSuchMethodException 异常
     * @throws IllegalAccessException 异常
     * @throws InvocationTargetException 异常
     */
    public static void writeTo(String path) throws IOException, NoSuchMethodException, IllegalAccessException,
            InvocationTargetException {
        MsUtils.writeTo(xssfWorkbook, path);
    }

    /**
     * 保存到本地，关闭工作簿，并创建一个新的工作簿
     *
     * @param path 路径
     *
     * @throws IOException 异常
     * @throws NoSuchMethodException 异常
     * @throws IllegalAccessException 异常
     * @throws InvocationTargetException 异常
     */
    public static void writeAndClose(String path) throws InvocationTargetException, NoSuchMethodException,
            IllegalAccessException, IOException {
        writeTo(path);
        xssfWorkbook.close();
        createXssfWorkbook();
    }

    /**
     * 获取当前操作的工作簿
     *
     * @return 工作簿
     */
    public static XSSFWorkbook getXssfWorkbook() {
        return xssfWorkbook;
    }

    /**
     * 修改当前操作的工作簿
     *
     * @param xssfWorkbook {@link XSSFWorkbook}
     */
    public static void setXssfWorkbook(XSSFWorkbook xssfWorkbook) {
        MsExcelUtils.xssfWorkbook = xssfWorkbook;
    }

    /**
     * 修改当前操作的工作簿
     *
     * @param path 本地工作簿文件路径
     */
    public static void setXssfWorkbook(String path) throws IOException {
        MsExcelUtils.xssfWorkbook = new XSSFWorkbook(path);
    }

    /**
     * 获取当前操作的工作表
     *
     * @return {@link XSSFSheet}
     */
    public static XSSFSheet getXssfSheet() {
        return xssfSheet;
    }

    /**
     * 修改当前操作的工作表
     *
     * @param xssfSheet {@link XSSFSheet}
     */
    public static void setXssfSheet(XSSFSheet xssfSheet) {
        MsExcelUtils.xssfSheet = xssfSheet;
    }

    private static void createXssfWorkbookIfNull() {
        if (Checker.isNull(xssfWorkbook)) {
            xssfWorkbook = new XSSFWorkbook();
        }
    }

    private static void createXssfSheetIfNull() {
        if (Checker.isNull(xssfSheet)) {
            createXssfWorkbookIfNull();
            xssfSheet = xssfWorkbook.createSheet();
        }
    }
}
