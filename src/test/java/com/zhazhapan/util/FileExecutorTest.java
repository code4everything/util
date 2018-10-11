package com.zhazhapan.util;

import com.zhazhapan.util.interfaces.SimpleHutoolWatcher;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * @author pantao
 */
public class FileExecutorTest {

    @Test
    public void testCreateFolder() {
        assert FileExecutor.createFolder("/Users/pantao/Desktop/upload/20180131");
    }

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
        String[] files = new String[]{"/Users/pantao/Desktop/test/Dump20171220_1.sql",
                "/Users/pantao/Desktop/test" + "/Dump20171220_2.sql"};
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

    @Test
    public void renameTo() {
        File file = new File("c:\\test\\haha\\lfss.apk");
        System.out.println("" + true);
    }

    @Test
    public void listFile() {
    }

    @Test
    public void listFile1() {
    }

    @Test
    public void getFileSuffix() {
    }

    @Test
    public void getFileSuffix1() {
    }

    @Test
    public void scanFolderAsArray() {
    }

    @Test
    public void scanFolder() {
    }

    @Test
    public void scanFolder1() {
    }

    @Test
    public void read() {
    }

    @Test
    public void read1() {
    }

    @Test
    public void read2() {
    }

    @Test
    public void read3() {
    }

    @Test
    public void copyDirectories() {
    }

    @Test
    public void copyDirectories1() {
    }

    @Test
    public void copyDirectories2() {
    }

    @Test
    public void copyDirectories3() {
    }

    @Test
    public void copyFiles() {
    }

    @Test
    public void copyFiles1() {
    }

    @Test
    public void createFolder() {
    }

    @Test
    public void createFolder1() {
    }

    @Test
    public void copyFiles2() {
    }

    @Test
    public void copyFiles3() {
    }

    @Test
    public void splitFile() {
    }

    @Test
    public void splitFile1() {
    }

    @Test
    public void splitFile2() {
    }

    @Test
    public void splitFile3() {
    }

    @Test
    public void renameFiles() {
    }

    @Test
    public void renameFiles1() {
    }

    @Test
    public void renameFiles2() {
    }

    @Test
    public void renameFiles3() {
    }

    @Test
    public void renameFiles4() {
    }

    @Test
    public void renameFiles5() {
    }

    @Test
    public void mergeFiles() {
    }

    @Test
    public void mergeFiles1() {
    }

    @Test
    public void mergeFiles2() {
    }

    @Test
    public void mergeFiles3() {
    }

    @Test
    public void getFiles() {
    }

    @Test
    public void getFiles1() {
    }

    @Test
    public void createFile() {
    }

    @Test
    public void createFile1() {
    }

    @Test
    public void createNewFile() {
    }

    @Test
    public void createNewFile1() {
    }

    @Test
    public void deleteFile() {
    }

    @Test
    public void deleteFile1() {
    }

    @Test
    public void saveFile() {
    }

    @Test
    public void saveFile1() {
    }

    @Test
    public void saveFile2() {
    }

    @Test
    public void readFile() {
    }

    @Test
    public void readFile1() {
    }

    @Test
    public void readFile2() {
    }

    @Test
    public void saveLogFile() {
    }

    @Test
    public void saveFile3() {
    }

    @Test
    public void watchFile() {
        FileExecutor.watchFile("c:\\", new SimpleHutoolWatcher() {
            @Override
            public void doSomething() {
                // do something
            }
        }, true);
    }

    @Test
    public void watchFile1() {
    }

    @Test
    public void parseJsonArray() {
    }

    @Test
    public void parseJsonArray1() {
    }

    @Test
    public void parseJsonArray2() {
    }

    @Test
    public void parseJsonObject() {
    }

    @Test
    public void parseJsonObject1() {
    }

    @Test
    public void parseJsonObject2() {
    }

    @Test
    public void getSuffix() {
    }

    @Test
    public void getSuffix1() {
    }
}
