package com.zhazhapan.util.office;

import com.zhazhapan.util.Checker;
import org.apache.log4j.Logger;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.*;

/**
 * @author pantao
 * @since 2018/2/28
 */
public class MsUtils {

    private static Logger logger = Logger.getLogger(MsUtils.class);

    private MsUtils() {}

    /**
     * 保存word文档
     *
     * @param document 文档对象
     * @param path 路径
     *
     * @throws IOException 异常
     */
    public static void writeTo(XWPFDocument document, String path) throws IOException {
        OutputStream os = new FileOutputStream(path);
        document.write(os);
        os.close();
        document.close();
        logger.info("文件已输出：" + path);
    }

    /**
     * 读取word文档
     *
     * @param path 路径
     *
     * @return {@link XWPFDocument}
     *
     * @throws IOException 异常
     */
    public static XWPFDocument getDocument(String path) throws IOException {
        InputStream is = new FileInputStream(path);
        XWPFDocument document = new XWPFDocument(is);
        is.close();
        return document;
    }

    protected static Integer checkInteger(Integer integer) {
        return Checker.isNull(integer) ? 0 : integer;
    }
}
