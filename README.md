封装一些常用的功能（JDK至少需要1.8），持续更新

> [**API文档**](https://apidoc.gitee.com/code4everything/util/)

**使用Maven**

``` xml
<dependency>
    <groupId>com.zhazhapan</groupId>
    <artifactId>util</artifactId>
    <version>1.0.9</version>
</dependency>
```

**或手动导入：**	[点我下载](http://oq3iwfipo.bkt.clouddn.com/tools/zhazhapan/util-1.0.9.jar)

> [**GitHub地址**](https://github.com/code4everything/util)
> [**Gitee地址**](https://gitee.com/code4everything/util)

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
// 2.3 或使用URL对象
JsonParser jsonParser = new JsonParser(new URL("http://somehost.com/sometext.json"));

// 3. 愉快的使用
String str = jsonParser.getString("key3[1].fast");
String str1 = jsonParser.getString("key4[1]");
JSONObject jsonObject = jsonParser.getObject("key3[0]");
JSONArray jsonArray = jsonParser.getArray("key4");
// 或使用JSONPath路径语法
Object object = jsonParser.eval("$.key2.child1");

// 4. 修改指定路径值
jsonParser.set("key1", "test_set_value");

// 5. 删除指定路径值
jsonParser.remove("key1");

// 6. 指定路径是否包含某个值
boolean b = jsonParser.containsValue("key1", "test");

// 7. 返回并格式化JSON
jsonParser.toString();
```

备注：[fastjson jsonpath路径语法](https://github.com/alibaba/fastjson/wiki/JSONPath)

**2. 简单计算器**

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

// 2. 写文件（可能抛出IO异常）
String write = "write something";
FileExecutor.saveFile("/Users/pantao/Desktop/test.txt", write, false);

// 3. 读文件（可能抛出IO异常）
String read = FileExecutor.readFile("/Users/pantao/Desktop/test.txt");

// 4. 批量复制文件夹
FileExecutor.copyDirectories(new String[] { "/Users/pantao/Desktop/test" }, "/Users/pantao/Desktop/new");

// 5. 批量复制文件
FileExecutor.copyFiles(new String[] { "/Users/pantao/Desktop/qiniu.jar" }, "/Users/pantao/Desktop/test");

// 6. 批量重命名文件
String[] files = new String[] { "/Users/pantao/Desktop/test/Dump20171220_3.sql", "/Users/pantao/Desktop/test/Dump20171220_4.sql" };
FileExecutor.renameFiles(files, new String[] { "test_rename_1", "test_rename_2.txt" });

// 7. 拆分文件，通过拆分点拆分
FileExecutor.splitFile("/Users/pantao/Desktop/test/Dump20171220.sql", new long[] { 1000, 2000, 3000 });

// 8. 合并文件
String[] files = new String[] { "/Users/pantao/Desktop/test/Dump20171220_1.sql", "/Users/pantao/Desktop/test/Dump20171220_2.sql" };
FileExecutor.mergeFiles(files, "/Users/pantao/Desktop/test/test_merge.sql", "");

// 9. 创建文件（如果存在则删除）
FileExecutor.createNewFile("test.txt");

// 10. 删除文件或文件夹（递归删除）
FileExecutor.deleteFile("/Users/pantao/Desktop/test/test_merge.sql");

// 11. 扫描文件夹下面所有的文件（返回一个文件列表）
FileExecutor.scanFolder("/Users/Pantao/Desktop");

// 12. 创建文件，如果文件不存在的话
FileExecutor.createFile("test.txt");
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

String sha256 = JavaEncrypt.sha256("123456");

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
// 4.2 弹出并接收返回的内容（如果用户点击取消将返回null）
String msg = Dialogs.showInputDialog("title", "header", "content", "default value");
```

**8. `com.zhazhapan.util.Checker`类**

*所有参数均不用考虑为null的情况，Checker会处理*

| 方法名           | 简要说明                             |
| ------------- | -------------------------------- |
| isSorted      | 检查数组是否是升序或降序                     |
| isDate        | 是否是日期格式                          |
| replace       | 替换字符串                            |
| isEmail       | 是否是邮箱格式                          |
| isDecimal     | 是否是数字（包括小数）格式                    |
| isNumber      | 是否是整数                            |
| isNull        | 对象是否为null                        |
| isNotNull     | 对象是否不为null                       |
| isNullOrEmpty | 字符串是否为null或空                     |
| isNotEmpty    | 是否不为null且不为空                     |
| checkNull     | 检测字符串是否为null，如果是，返回空字符串，否则返回原字符串 |
| isEmpty       | 列表或集合是否为空                        |
| isHyperLink   | 是否是超链接（URL）                      |
| checkDate     | 检测日期是否为null，如果是，返回当前日期，否则返回原日期   |
|isWindows|判断当前系统是否是Windows|
|isMacOS|判断当前系统是否是Mac|
|isLinux|判断当前系统是否是Linux|
|isLimited|判断字符串的长度是否在某个范围|
|isImage|判断文件是否是图片|

**9. `com.zhazhapan.util.Formatter`类**

| 方法名                    | 简要说明                                  |
| ---------------------- | ------------------------------------- |
| stringToInt            | 将字符串转换为Integer                        |
| formatSize             | 格式化文件大小为KB、MB、GB、TB格式                 |
| sizeToLong             | 将格式化后的文件大小转换为单位为B（字节）的long型           |
| stringToDouble         | 字符串转换为Double                          |
| stringToLong           | 字符串转换为Long                            |
| customFormatDecimal    | 将Double转换为自定义格式的字符串                   |
| formatDecimal          | 将Double转换为默认格式（#0.00）的字符串             |
| timeStampToString      | 将时间戳转换为“yyyy-MM-dd HH:mm:ss”格式的字符串    |
| formatJson             | 将json文本转换为标准格式的json                   |
| dateToString           | 将日期转换为“yyyy-MM-dd”格式的字符串              |
| datetimeToString       | 将日期转换为“yyyy-MM-dd HH:mm:ss”格式的字符串     |
| getFileName            | 从路径（包括网络路径）中获取文件名                     |
| stringToFloat          | 将字符串转换为Float                          |
| stringToInteger        | 同stringToInt                          |
| stringToDate           | 将字符串转换为“yyyy-MM-dd”格式的日期              |
| stringToLongTime       | 将字符串转换为“HH:mm:ss”格式的日期                |
| stringToShortTime      | 将字符串转换为“HH:mm”格式的日期                   |
| stringToCustomDateTime | 将字符串转换为自定义格式的日期                       |
| stringToDatetime       | 将字符串转换为“yyyy-MM-dd HH:mm:ss”格式的日期     |
| toLocalDate            | 将日期转换为“yyyy年MM月dd日”格式的字符串             |
| longTimeToString       | 将日期转换为“HH:mm:ss”格式的字符串                |
| shortTimeToString      | 将日期转换为“HH:mm”格式的字符串                   |
| datetimeToCustomString | 将日期转换为自定义格式的字符串                       |
| numberFormat           | 将数字格式化为指定长度的字符串（不足用0补齐）               |
| localDateToDate        | 将java.time.LocalDate转换为java.util.Date |
| dateToLocalDate        | 将java.util.Date转换为java.time.LocalDate |
| listToJson        | 将List转换为JSON |
| mapToJson        | 将Map转换为JSON |
|getFileSuffix|获取文件后缀名|

**10. `com.zhazhapan.util.Utils`类**

| 方法名               | 简要说明                    |
| ----------------- | ----------------------- |
| extractDouble     | 从字符串中提取数字并转换为Double     |
| extractFloat      | 从字符串中提取数字并转换为Float      |
| extractShort      | 从字符串中提取数字并转换为Short      |
| extractLong       | 从字符串中提取数字并转换为Long       |
| extractInt        | 从字符串中提取数字并转换为Integer    |
| extractDigit      | 从字符串中提取出数字，包括最后一个“.”号   |
| maxLengthString   | 返回多个字符串中长度最长的字符串        |
| copyToClipboard   | 复制字符串至系统剪贴板             |
| openLink          | 使用系统默认的浏览器打开超链接         |
| openFile          | 使用系统默认的方式打开文件           |
| getMaxValue       | 从多个数字中获取最大值             |
|getCurrentOS()|获取当前系统名称|

**11. `com.zhazhapan.util.ArrayUtils`类**

|方法名|简要说明|
|------|---------------|
|heapSort|堆排序|
|mergeSort|归并排序|
|shellSort|希尔排序|
|selectSort|选择排序|
|quickSort|快速排序|
|insertSort|插入排序|
|bubbleSort|冒泡排序|
|unique|数组去重|
| concatArrays      | 合并多个数组                  |
| mergeSortedArrays | 将两个已经排好序（同序）的数组合并一个有序数组 |

**12. `com.zhazhapan.util.DateUtils`类**

|方法名|简要说明|
|------|---------------|
|getWeekAsChinese|获取某个日期的星期，返回一个中文字符串|
|getWeek|获取某个日期的星期，返回一个整型|
|addHour|某个日期后推多少个小时|
|addMinute|某个日期后推多少个分钟|
|addSecond|某个日期后推多少个秒|
|addYear|某个日期后推多少个年|
|addMonth|某个日期后推多少个月|
|addDay|某个日期后推多少个天|
| getCurrentMonth() | 获取当前月份                  |
| getDay            | 获取日期                    |
| getMonth          | 获取月份                    |
| getYear           | 获取年份                    |

**13. 发送邮件**

``` java
// 导包
import com.zhazhapan.util.MailSender;

// 如何使用，下面的key指的是开启了POP3/IMAP/SMTP/Exchange/CardDAV/CalDAV服务，生成的授权码
MailSender.config("smtp.qq.com", "zhazhapan", "735817834@qq.com", "key");
MailSender.sendMail("tao@zhazhapan.com", "title", "this is a test email");
```

**14. `com.zhazhapan.util.RandomUtils`类**

|方法名|简要说明|
|------|---------------|
|getRandomDouble|获取一个随机的双精度类型|
|getRandomInteger|获取一个随机的整型|
|getRandomIntegerIgnoreRange|获取一个可以忽略某个区间的随机整型|
|getRandomColor|获取一个随机的颜色|
|getRandomEmail|获取一个随机邮箱|
|getRandomString|获取一个随机字符串|
|getRandomStringWithoutSymbol|获取一个只有数字和字母的随机字符串|
|getRandomStringOnlyLetter|获取一个只有字母的随机字符串|
|getRandomStringOnlyLowerCase|获取一个只有小写字母的随机字符串|
|getRandomStringOnlyUpperCase|获取一个只有大写字母的随机字符串|
|getRandomTextIgnoreRange|获取一个可以忽略某个范围字符的随机字符串|
|getRandomText|获取一个字符串|

**15. JSON与Bean互相转换**

*Bean转JSON*

``` java
// 一个Bean类
// 也可以不用注解
@ToJsonString(type = JsonType.PRETTY, modifier = FieldModifier.PRIVATE, method = JsonMethod.HANDLE)
public class User {
    public int id;
    private String name;
    private Date birth;

    public User() {}

    public User(int id, String name, Date birth) {
        this.id = id;
        this.name = name;
        this.birth = birth;
    }

    @Override
    public String toString() {
        try {
            // 1. 不使用注解
            return BeanUtils.toJsonString(this);
            // 2. 使用注解
            return BeanUtils.toJsonStringByAnnotation(this);
        } catch (IllegalAccessException e) {
            return e.getMessage();
        }
    }
}

// 外部类调用
User user = new User(1, "test", new Date());
System.out.println(user.toString());

// 不使用注解的输出
{"name":"test","birth":1516429105846,"id":1}

// 使用注解的输出
{
  "name": "test",
  "birth": "Sat Jan 20 14:18:25 CST 2018"
}
```

*JSON转Bean*
``` java
BeanUtils.jsonPutIn(jsonObject, bean);
```


**16. `com.zhazhapan.util.NetUtils`类**

|方法名|简要说明|
|------|---------------|
|getDataOfUrl|获取URL对应的网页内容|
|getInputStreamOfUrl|获取URL对应的InputStream对象|
|getInputStreamOfConnection|获取HttpURLConnection对应的InputStream对象|
|evaluate|XPath解析HTML内容|
|getHtmlFromUrl|获取网页内容|
|getDocumentFromUrl|获取HTML文档|
|getComputerName|获取计算机名|
|getSystemName|获取系统名称|
|getSystemArch|获取系统架构|
|getSystemVersion|获取系统版本|
|getMacAddress|获取Mac地址|
|getPublicIpAndLocation|获取公网IP和归属地|
|getLocalIp|获取本地ip地址|
|getLocationByIp|获取ip归属地|

**17. `com.zhazhapan.util.ReflectUtils`类**

|方法名|简要说明|
|------|---------------|
|scanPackage|扫描包下面所有的类|
|invokeMethod|通过反射调用方法|
|getTypes|获取对象集的所有类型|
|getBasicTypes|获取所有对象的基本类型|
|invokeMethodUseBasicType|使用基本类型调用方法|

**18. `com.zhazhapan.util.HttpUtils`类**

|方法名|简要说明|
|------|---------------|
|getCookie|通过名称获取Cookie|