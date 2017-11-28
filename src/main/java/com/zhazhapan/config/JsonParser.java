/**
 * 
 */
package com.zhazhapan.config;

import java.util.HashMap;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zhazhapan.util.Checker;
import com.zhazhapan.util.FileExecutor;
import com.zhazhapan.util.Formatter;

/**
 * 通过路径获取json对象，您需要手动配置jsonPath或jsonObject
 * 
 * @author pantao
 */
public class JsonParser {

	/**
	 * 默认构造函数，如果您需要配置jsonPath，请调用{@link JsonParser#setJsonPath(String)}；
	 * 或配置jsonObject，请调用{@link JsonParser#setJsonObject(String)}或{@link JsonParser#setJsonObject(JSONObject)}
	 */
	public JsonParser() {

	}

	/**
	 * 自动配置jsonObject
	 * 
	 * @param jsonObject
	 */
	public JsonParser(JSONObject jsonObject) {
		setJsonObject(jsonObject);
	}

	/**
	 * 自动配置jsonPath
	 * 
	 * @param jsonPath
	 */
	public JsonParser(String jsonPath) {
		setJsonPath(jsonPath);
	}

	/**
	 * 自动配置jsonObject
	 * 
	 * @param json
	 *            Json文本
	 * @param isPretty
	 *            是否是格式化的Json，不清楚的话请设置为false
	 */
	public JsonParser(String json, boolean isPretty) {
		if (!isPretty) {
			json = Formatter.formatJson(json);
		}
		setJsonObject(json);
	}

	/**
	 * json文件的绝对路径
	 */
	private String jsonPath;

	/**
	 * 最后一个“.”号之前的key
	 */
	private String prefixKey = ".";

	/**
	 * 最后一个“.”号之后的key
	 */
	private String postKey = "";

	/**
	 * 需要解析的JsonObject
	 */
	private JSONObject jsonObject;

	/**
	 * 用来存储已经解析过Json对象
	 */
	private HashMap<String, JSONObject> jsonStore = new HashMap<String, JSONObject>(16);

	/**
	 * 获取JsonObject
	 * 
	 * @param key
	 *            例如：country
	 * @return {@link JSONObject}
	 * @throws Exception
	 *             when key is null
	 */
	public JSONObject getObject(String key) throws Exception {
		return get(key).getJSONObject(postKey);
	}

	/**
	 * 获取JsonArray
	 * 
	 * @param key
	 *            例如：country.province
	 * @return {@link JSONArray}
	 * @throws Exception
	 *             when key is null
	 */
	public JSONArray getArray(String key) throws Exception {
		return get(key).getJSONArray(postKey);
	}

	/**
	 * 获取String
	 * 
	 * @param key
	 *            例如：country.province[13].name
	 * @return {@link String}
	 * @throws Exception
	 *             when key is null
	 */
	public String getString(String key) throws Exception {
		return get(key).getString(postKey);
	}

	/**
	 * 获取Integer
	 * 
	 * @param key
	 *            例如：country.province[13].peopleNums
	 * @return {@link Integer}
	 * @throws Exception
	 *             when key is null
	 */
	public int getInteger(String key) throws Exception {
		return Formatter.stringToInt(getString(key));
	}

	/**
	 * 获取Double
	 * 
	 * @param key
	 *            例如：country.province[13].area
	 * @return {@link Double}
	 * @throws Exception
	 *             when key is null
	 */
	public double getDouble(String key) throws Exception {
		return Formatter.stringToDouble(getString(key));
	}

	/**
	 * 通过key解析JsonObject
	 * 
	 * @param key
	 * @return {@link JSONObject}
	 * @throws Exception
	 *             when key is null
	 */
	private JSONObject get(String key) throws Exception {
		if (Checker.isNotEmpty(key)) {
			JSONObject object = jsonObject;
			// 拆分key
			String[] keys = key.split("\\.");
			prefixKey = ".";
			for (int i = 0; i < keys.length - 1; i++) {
				String tempKey = keys[i];
				prefixKey += tempKey + ".";
				if (jsonStore.containsKey(tempKey)) {
					object = jsonStore.get(tempKey);
				} else {
					if (tempKey.contains("[") && tempKey.contains("]")) {
						// 解析数组
						JSONArray array = object.getJSONArray(tempKey.split("\\[")[0]);
						String idx = tempKey.substring(tempKey.indexOf("[") + 1, tempKey.length() - 1);
						object = array.getJSONObject(Formatter.stringToInt(idx));
					} else {
						object = object.getJSONObject(tempKey);
					}
					jsonStore.put(prefixKey, object);
				}
			}
			postKey = keys[keys.length - 1];
			return jsonStore.get(prefixKey);
		}
		return null;
	}

	/**
	 * 通过配置好的jsonPath，加载文本并配置到jsonObject
	 */
	private void load() {
		setJsonObject(new FileExecutor().readFile(jsonPath));
	}

	/**
	 * 获取当前json文件的绝对路径
	 * 
	 * @return {@link String}
	 */
	public String getJsonPath() {
		return jsonPath;
	}

	/**
	 * 配置jsonPath
	 * 
	 * @param jsonPath
	 */
	public void setJsonPath(String jsonPath) {
		this.jsonPath = jsonPath;
		load();
	}

	/**
	 * 获取当前解析的JsonObject
	 * 
	 * @return {@link JSONObject}
	 */
	public JSONObject getJsonObject() {
		return jsonObject;
	}

	/**
	 * 配置JsonObject
	 * 
	 * @param json
	 *            传入json文本，自动转换为JsonObject
	 */
	public void setJsonObject(String json) {
		setJsonObject(JSON.parseObject(json));
	}

	/**
	 * 配置JsonObject
	 * 
	 * @param jsonObject
	 */
	public void setJsonObject(JSONObject jsonObject) {
		jsonStore.put(".", jsonObject);
		this.jsonObject = jsonObject;
	}
}
