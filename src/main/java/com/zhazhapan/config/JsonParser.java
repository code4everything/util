/**
 * 
 */
package com.zhazhapan.config;

import java.io.IOException;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.zhazhapan.util.Checker;
import com.zhazhapan.util.FileExecutor;
import com.zhazhapan.util.Formatter;

/**
 * 通过路径获取json对象，您需要手动配置jsonPath或jsonObject
 * 
 * @author pantao
 */
public class JsonParser {

	private Logger logger = Logger.getLogger(getClass());

	/**
	 * 最大缓存大小
	 */
	private int maxCache = 1024;

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
	 *            {@link JSONObject}
	 */
	public JsonParser(JSONObject jsonObject) {
		setJsonObject(jsonObject);
	}

	/**
	 * 自动配置jsonPath，根据JSON文件路径读取json并转换为JSONObject
	 * 
	 * @param jsonPath
	 *            JSON文件的路径
	 * @throws IOException
	 *             异常
	 */
	public JsonParser(String jsonPath) throws IOException {
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
	 * 需要解析的JsonObject
	 */
	private JSONObject jsonObject;

	/**
	 * 用来存储已经解析过Json对象
	 */
	private volatile HashMap<String, JSONObject> jsonStore = new HashMap<String, JSONObject>(16);

	/**
	 * 根据路径设置值
	 * 
	 * @param path
	 *            <a href=
	 *            "https://github.com/alibaba/fastjson/wiki/JSONPath">路径语法</a>
	 * @param value
	 *            值对象
	 * @return 是否设置成功
	 * @throws Exception
	 *             异常
	 */
	public boolean set(String path, Object value) throws Exception {
		jsonStore.clear();
		return JSONPath.set(jsonObject, checkPath(path), value);
	}

	/**
	 * 根据路径获取对象
	 * 
	 * @param path
	 *            <a href=
	 *            "https://github.com/alibaba/fastjson/wiki/JSONPath">路径语法</a>
	 * @return 对象
	 */
	public Object eval(String path) {
		String key = pathToKey(path);
		if (jsonStore.containsKey(key)) {
			return jsonStore.get(key);
		} else {
			Object object = JSONPath.eval(jsonObject, checkPath(path));
			try {
				put(key, (JSONObject) object);
			} catch (Exception e) {
				logger.error("save json cache error(this message has no effect for you): " + e.getMessage());
			}
			return object;
		}
	}

	/**
	 * 获取大小
	 * 
	 * @param path
	 *            <a href=
	 *            "https://github.com/alibaba/fastjson/wiki/JSONPath">路径语法</a>
	 * @return 大小
	 */
	public int size(String path) {
		return JSONPath.size(jsonObject, checkPath(path));
	}

	/**
	 * 是否包含某个对象
	 * 
	 * @param path
	 *            <a href=
	 *            "https://github.com/alibaba/fastjson/wiki/JSONPath">路径语法</a>
	 * @return {@link Boolean}
	 */
	public boolean contains(String path) {
		return JSONPath.contains(jsonObject, checkPath(path));
	}

	/**
	 * 是否包含某个值
	 * 
	 * @param path
	 *            <a href=
	 *            "https://github.com/alibaba/fastjson/wiki/JSONPath">路径语法</a>
	 * @param value
	 *            值
	 * @return {@link Boolean}
	 */
	public boolean containsValue(String path, Object value) {
		return JSONPath.containsValue(jsonObject, checkPath(path), value);
	}

	/**
	 * 数组追加数组
	 * 
	 * @param path<a
	 *            href= "https://github.com/alibaba/fastjson/wiki/JSONPath">路径语法</a>
	 * @param values
	 *            数组
	 */
	public void arrayAdd(String path, Object... values) {
		jsonStore.clear();
		JSONPath.arrayAdd(jsonObject, checkPath(path), values);
	}

	/**
	 * 移除某个路径
	 * 
	 * @param path
	 *            <a href=
	 *            "https://github.com/alibaba/fastjson/wiki/JSONPath">路径语法</a>
	 * @return 是否移除成功
	 */
	public boolean remove(String path) {
		jsonStore.clear();
		return JSONPath.remove(jsonObject, checkPath(path));
	}

	/**
	 * 检查路径头是否正确
	 * 
	 * @param key
	 *            路径
	 * @return 正确路径头
	 */
	private String checkPath(String key) {
		if (!key.startsWith("$")) {
			if (!key.startsWith(".")) {
				key = "." + key;
			}
			key = "$" + key;
		}
		if (key.endsWith(".")) {
			key = key.substring(0, key.length() - 1);
		}
		return key;
	}

	/**
	 * 获取JsonObject
	 * 
	 * @param key
	 *            例如：country
	 * @return {@link JSONObject}
	 * @throws Exception
	 *             when key is invalid
	 */
	public JSONObject getObject(String key) throws Exception {
		return (JSONObject) get(key, JSONObject.class);
	}

	/**
	 * 获取JsonArray
	 * 
	 * @param key
	 *            例如：country.province
	 * @return {@link JSONArray}
	 * @throws Exception
	 *             when key is invalid
	 */
	public JSONArray getArray(String key) throws Exception {
		return (JSONArray) get(key, JSONArray.class);
	}

	/**
	 * 获取String
	 * 
	 * @param key
	 *            例如：country.province[13].name
	 * @return {@link String}
	 * @throws Exception
	 *             when key is invalid
	 */
	public String getString(String key) throws Exception {
		return (String) get(key, String.class);
	}

	/**
	 * 获取Integer
	 * 
	 * @param key
	 *            例如：country.province[13].peopleNums
	 * @return {@link Integer}
	 * @throws Exception
	 *             when key is invalid
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
	 *             when key is invalid
	 */
	public double getDouble(String key) throws Exception {
		return Formatter.stringToDouble(getString(key));
	}

	/**
	 * 缓存
	 * 
	 * @param key
	 *            键
	 * @param jsonObject
	 *            值
	 */
	private void put(String key, JSONObject jsonObject) {
		if (jsonStore.size() > maxCache || jsonStore.size() == Integer.MAX_VALUE) {
			// 清除缓存
			jsonStore.clear();
		}
		jsonStore.put(key, jsonObject);
	}

	/**
	 * 通过key解析JsonObject
	 * 
	 * @param key
	 * @return {@link JSONObject}
	 * @throws Exception
	 *             when key is invalid
	 */
	private <T> Object get(String key, Class<T> classT) throws Exception {
		if (Checker.isNotEmpty(key)) {
			JSONObject object = jsonObject;
			// 拆分key
			String[] keys = key.split("\\.");
			String prefixKey = ".";
			key = pathToKey(key);
			if (jsonStore.containsKey(key)) {
				object = jsonStore.get(key);
			} else {
				for (int i = 0; i < keys.length; i++) {
					String tempKey = keys[i];
					prefixKey += tempKey + ".";
					if (jsonStore.containsKey(tempKey)) {
						object = jsonStore.get(tempKey);
					} else if (i < keys.length - 1) {
						if (tempKey.matches(".*\\[\\d+\\]$")) {
							// 解析数组
							JSONArray array = object.getJSONArray(tempKey.split("\\[")[0]);
							String idx = tempKey.substring(tempKey.indexOf("[") + 1, tempKey.length() - 1);
							object = array.getJSONObject(Formatter.stringToInt(idx));
						} else {
							object = object.getJSONObject(tempKey);
						}
						put(prefixKey, object);
					} else {
						key = keys[keys.length - 1];
						if (key.matches(".*\\[\\d+\\]$")) {
							int leftIdx = key.lastIndexOf("[");
							JSONArray array = object.getJSONArray(key.substring(0, leftIdx));
							int idx = Formatter.stringToInt(key.substring(leftIdx + 1, key.length() - 1));
							if (classT == JSONObject.class) {
								return array.getJSONObject(idx);
							} else if (classT == JSONArray.class) {
								return array.getJSONArray(idx);
							} else {
								return array.getString(idx);
							}
						} else if (classT == JSONObject.class) {
							return object.getJSONObject(key);
						} else if (classT == JSONArray.class) {
							return object.getJSONArray(key);
						} else {
							return object.getString(key);
						}
					}
				}
			}
			return object;
		}
		return null;
	}

	/**
	 * 通过配置好的jsonPath，加载文本并配置到jsonObject
	 * 
	 * @throws IOException
	 *             异常
	 */
	private void load() throws IOException {
		setJsonObject(FileExecutor.readFile(jsonPath));
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
	 * 配置jsonPath，权限JSON文件路径读取json并转换为JSONObject
	 * 
	 * @param jsonPath
	 *            JSON文件的路径
	 * @throws IOException
	 *             异常
	 */
	public void setJsonPath(String jsonPath) throws IOException {
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
	 *            {@link JSONObject}
	 */
	public void setJsonObject(JSONObject jsonObject) {
		jsonStore.put(".", jsonObject);
		this.jsonObject = jsonObject;
	}

	/**
	 * 获取最大缓存值
	 * 
	 * @return {@link Integer}
	 */
	public int getMaxCache() {
		return maxCache;
	}

	/**
	 * 设置最大缓存值
	 * 
	 * @param maxCache
	 *            {@link Integer}
	 */
	public void setMaxCache(int maxCache) {
		this.maxCache = maxCache;
	}

	/**
	 * 获取当前缓存大小
	 * 
	 * @return {@link Integer}
	 */
	public int getCacheSize() {
		return jsonStore.size();
	}

	/**
	 * 清除JSON缓存
	 */
	public void clearCache() {
		jsonStore.clear();
	}

	/**
	 * 将JSONPath的路径简单地转换为JsonParser支持的key
	 * 
	 * @param path
	 *            路径
	 * @return key
	 */
	private String pathToKey(String path) {
		if (path.startsWith("$")) {
			path = path.substring(1);
		}
		if (!path.startsWith(".")) {
			path = "." + path;
		}
		if (!path.endsWith(".")) {
			path += ".";
		}
		return path;
	}
}
