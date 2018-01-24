package com.zhazhapan.util;

import java.io.IOException;

import org.junit.Test;

/**
 * @author pantao
 */
public class FileExecutorTest {

    @Test
    public void testScanFolder() {
        System.out.println(Formatter.listToJson(FileExecutor.scanFolder("C:\\Users\\pantao\\Downloads\\image")));
    }

    @Test
    public void testSplitFile() throws IOException {
        FileExecutor.splitFile("/Users/pantao/Desktop/test/Dump20171220.sql", new long[]{1000, 2000, 3000});
    }

    @Test
    public void testMergeFile() throws IOException {
        String[] files = new String[]{"/Users/pantao/Desktop/test/Dump20171220_1.sql", "/Users/pantao/Desktop/test/Dump20171220_2.sql"};
        FileExecutor.mergeFiles(files, "/Users/pantao/Desktop/test/test_merge.sql", "/\\*.*?\\*/;\r?\n?");
    }

    @Test
    public void testRenameFile() {
        FileExecutor.renameFiles("C:\\Users\\pantao\\Downloads\\image", "bg_", ".jpg", 138);
    }

    @Test
    public void testCopyFiles() throws IOException {
        FileExecutor.copyFiles(new String[]{"/Users/pantao/Desktop/qiniu.jar"}, "/Users/pantao/Desktop/test");
    }

    @Test
    public void testCopyDirectories() throws IOException {
        FileExecutor.copyDirectories(new String[]{"/Users/pantao/Desktop/test"}, "/Users/pantao/Desktop/new");
    }
}
