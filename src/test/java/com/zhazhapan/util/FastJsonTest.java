/**
 * 
 */
package com.zhazhapan.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.zhazhapan.config.JsonParser;

/**
 * @author pantao
 *
 */
public class FastJsonTest {

	String json = "{key1:test,key2:{child1:node1,child2:node2},key3:[{fast:json1},{fast:json2}]}";

	@Test
	public void testGetString() {
		JsonParser jsonParser = new JsonParser(json, false);
		try {
			assertEquals("test", jsonParser.getString("key1"));
			assertEquals("node1", jsonParser.getString("key2.child1"));
			assertEquals("json2", jsonParser.getString("key3[1].fast"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
