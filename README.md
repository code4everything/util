封装一些常用的功能（JDK至少需要1.8），持续更新

**使用Maven**

``` xml
<dependency>
    <groupId>com.zhazhapan</groupId>
    <artifactId>util</artifactId>
    <version>1.0.1</version>
</dependency>
```

**或手动导入：**	[点我下载](http://oq3iwfipo.bkt.clouddn.com/tools/zhazhapan/util-1.0.1.jar)

## 功能

**1. JSON快速解析，基于fastjson**

*使用*

``` java
// 1. 导包
import com.zhazhapan.config.JsonParser;

String json = "{key1:test,key2:{child1:node1,child2:node2},key3:[{fast:json1},{fast:json2}],key4:[arr1,arr2]}";

// 2. 新建一个解析对象

// 2.1 使用json字符串
JsonParser jsonParser = new JsonParser(json, false);
// 2.2 或使用文件路径（可能抛出IO异常）
JsonParser jsonParser = new JsonParser("/Users/pantao/Desktop/test.json");

// 3. 愉快的使用
String str = jsonParser.getString("key3[1].fast");
String str1 = jsonParser.getString("key4[1]");
JSONObject jsonObject = jsonParser.getObject("key3[0]");
JSONArray jsonArray = jsonParser.getArray("key4"); 
```

**2. 简单计算器（v1.0.1中加入）**

*使用*

``` java
// 1. 导包
import com.zhazhapan.util.Calculator;

// 2. 设置（如果没有设置将使用默认的精度）计算精度（即保存多少位小数）
Calculator.setPrecision(5);

// 3. 计算（有可能抛出计算式不合法的异常）
String formula = "(23-9*7)/22+2(35-45+56/(12-9+4*(22/10-2)))";
BigDecimal bd = Calculator.calculate(formula);
double res = db.doubleValue();
```

**3. 网络文件下载**

*使用*

``` java
// 1. 导包
import com.zhazhapan.util.Downloader;

// 2. 下载
String storagePath = "/Users/pantao/Desktop";
Downloader.download(storagePath, "http://oq3iwfipo.bkt.clouddn.com/tools/zhazhapan/util.jar");
```

**4. 简单的读写文件**

*使用*

``` java
// 1. 导包
import com.zhazhapan.util.FileExecutor;

// 2. 创建对象
FileExecutor executor = new FileExecutor();

// 3. 写文件（可能抛出IO异常）
String write = "write something";
executor.saveFile("/Users/pantao/Desktop/test.txt", write, false);

// 4. 读文件（可能抛出IO异常）
String read = executor.readFile("/Users/pantao/Desktop/test.txt");
```

**5. 简单的线程池**

*使用*

``` java
// 1. 导包
import com.zhazhapan.util.ThreadPool;

// 2. 初始化线程池
// 2.1 方式一
ThreadPool.setCorePoolSize(1);
ThreadPool.setMaximumPoolSize(5);
ThreadPool.setKeepAliveTime(100);
ThreadPool.setTimeUnit(TimeUnit.MILLISECONDS);
ThreadPool.init();
// 2.2 方式二
ThreadPool.init(1, 5, 100, TimeUnit.MILLISECONDS);

// 3. 使用线程池
ThreadPool.executor.submit(() -> {
	//do somthing here
});

// 4. 或者新建线程池
ThreadPool threadPool = new ThreadPool(1, 5, 100, TimeUnit.MILLISECONDS);

// 4.1 使用新建的线程池
threadPool.newExecutor.submit(() -> {
	//do somthing here
});
```

**6. 简单的加密与解密**

``` java
// 提供一些Java常用的加密（包括md5，base64,sha等加密）
import com.zhazhapan.util.decryption.JavaEncrypt;

// 如何使用
String code = JavaEncrypt.md5("this is a md5 encrypt method");

// 主要是base64解密
import com.zhazhapan.util.decryption.JavaDecrypt;

// 异或加密和ascii加密
import com.zhazhapan.util.decryption.SimpleEncryt;

// 如何使用
String code = SimpleEncryt.ascii("simple encrypt", "key");

// 异或解密和ascii解密
import com.zhazhapan.util.decryption.SimpleDecrypt;
```

**7. 弹窗（使用JavaFX，JDK最低要求1.8）**

*使用*

``` java
// 1. 导入
import com.zhazhapan.util.dialog.Alerts;

// 2. 弹窗并接收返回的信息
Optional<ButtonType> result = Alerts.showConfirmation("title", "some information");
if (result.get() == ButtonType.OK) {
	// do something here
} else {
	// do something here
}

// 3. 其他类型的弹窗基本上跟上面的操作一样

// 4. 弹出输入框
// 4.1 导包
import com.zhazhapan.util.dialog.Dialogs;
// 4.2 弹出并接收返回的内容（如何用户点击取消将返回null）
String msg = Dialogs.showInputDialog("title", "header", "content", "default value");
```

**8. `com.zhazhapan.util.Checker`类**

*所有参数均不用考虑为null的情况，Checker会处理*

方法名|简要说明
--------|---
isSorted|检查数组是否是升序或降序
isDate|是否是日期格式
replace|替换字符串
isEmail|是否是邮箱格式
isDecimal|是否是数字（包括小数）格式
isNumber|是否是整数
isNull|对象是否为null
isNotNull|对象是否不为null
isNullOrEmpty|字符串是否为null或空
isNotEmpty|是否不为null且不为空
checkNull|检测字符串是否为null，如果是，返回空字符串，否则返回原字符串
isEmpty|列表或集合是否为空
isHyperLink|是否是超链接（URL）
checkDate|检测日期是否为null，如果是，返回当前日期，否则返回原日期

**9. `com.zhazhapan.util.Formatter`类**

方法名|简要说明
--------|---
stringToInt|将字符串转换为Integer
formatSize|格式化文件大小为KB、MB、GB、TB格式
sizeToLong|将格式化后的文件大小转换为单位为B（字节）的long型
stringToDouble|字符串转换为Double
stringToLong|字符串转换为Long
customFormatDecimal|将Double转换为自定义格式的字符串
formatDecimal|将Double转换为默认格式（#0.00）的字符串
timeStampToString|将时间戳转换为“yyyy-MM-dd HH:mm:ss”格式的字符串
formatJson|将json文本转换为标准格式的json
dateToString|将日期转换为“yyyy-MM-dd”格式的字符串
datetimeToString|将日期转换为“yyyy-MM-dd HH:mm:ss”格式的字符串
getFileName|从路径（包括网络路径）中获取文件名
stringToFloat|将字符串转换为Float
stringToInteger|同stringToInt
stringToDate|将字符串转换为“yyyy-MM-dd”格式的日期
stringToLongTime|将字符串转换为“HH:mm:ss”格式的日期
stringToShortTime|将字符串转换为“HH:mm”格式的日期
stringToCustomDateTime|将字符串转换为自定义格式的日期
stringToDatetime|将字符串转换为“yyyy-MM-dd HH:mm:ss”格式的日期
toLocalDate|将日期转换为“yyyy年MM月dd日”格式的字符串
longTimeToString|将日期转换为“HH:mm:ss”格式的字符串
shortTimeToString|将日期转换为“HH:mm”格式的字符串
datetimeToCustomString|将日期转换为自定义格式的字符串
numberFormat|将数字格式化为指定长度的字符串（不足用0补齐）
localDateToDate|将java.time.LocalDate转换为java.util.Date
dateToLocalDate|将java.util.Date转换为java.time.LocalDate

**10. `com.zhazhapan.util.Utils`类**

方法名|简要说明
--------|---
extractDouble|从字符串中提取数字并转换为Double
extractFloat|从字符串中提取数字并转换为Float
extractShort|从字符串中提取数字并转换为Short
extractLong|从字符串中提取数字并转换为Long
extractInt|从字符串中提取数字并转换为Integer
extractDigit|从字符串中提取出数字，包括最后一个“.”号
maxLengthString|返回多个字符串中长度最长的字符串
concatArrays|合并多个数组
getCurrentMonth()|获取当前月份
copyToClipboard|复制字符串至系统剪贴板
openLink|使用系统默认的浏览器打开超链接
openFile|使用系统默认的方式打开文件
getDay|获取日期
getMonth|获取月份
getYear|获取年份
getMaxValue|从多个数字中获取最大值
mergeSortedArrays|将两个已经排好序（同序）的数组合并一个有序数组