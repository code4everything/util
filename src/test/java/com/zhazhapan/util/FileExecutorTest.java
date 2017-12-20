/**
 * 
 */
package com.zhazhapan.util;

import java.io.IOException;

import org.junit.Test;

/**
 * @author pantao
 *
 */
public class FileExecutorTest {

	@Test
	public void testSplitFile() throws IOException {
		FileExecutor.splitFile("/Users/pantao/Desktop/test/Dump20171220.sql", new long[] { 1000, 2000, 3000 });
	}

	@Test
	public void testMergeFile() throws IOException {
		String[] files = new String[] { "/Users/pantao/Desktop/test/Dump20171220_1.sql",
				"/Users/pantao/Desktop/test/Dump20171220_2.sql" };
		FileExecutor.mergeFiles(files, "/Users/pantao/Desktop/test/test_merge.sql", "/\\*.*?\\*/;\r?\n?");
	}
}
