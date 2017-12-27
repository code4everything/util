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

	String json = "{key1:test,key2:{child1:node1,child2:node2},key3:[{fast:json1},{fast:json2}],key4:[arr1,arr2]}";

	@Test
	public void testGetString() {
		JsonParser jsonParser = new JsonParser(json, false);
		try {
			assertEquals(jsonParser.eval("key1"), jsonParser.getString("key1"));
			assertEquals(jsonParser.eval("key2.child1"), jsonParser.getString("key2.child1"));
			assertEquals(jsonParser.eval("key3[1].fast"), jsonParser.getString("key3[1].fast"));
			assertEquals(jsonParser.eval("key4[1]"), jsonParser.getString("key4[1]"));
			assertEquals(jsonParser.eval("key4"), jsonParser.getArray("key4"));
			assertEquals(jsonParser.eval("key3[0]"), jsonParser.getObject("key3[0]"));
			jsonParser.set("key1", "test_set_value");
			assertEquals("test_set_value", jsonParser.eval("key1"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
