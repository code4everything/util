package com.zhazhapan.modules.constant;

/**
 * <a href="https://zh.wikipedia.org/zh-hans/HTTP%E5%A4%B4%E5%AD%97%E6%AE%B5">参考</a>
 *
 * @author pantao
 * @since 2018/6/12
 */
public class HttpHeaders {

    /**
     * 主要用于标识 Ajax 及可扩展标记语言请求
     */
    public static final String X_REQUESTED_WITH = "X-Requested-With";

    /**
     * 能够接受的回应内容类型
     */
    public static final String ACCEPT = "Accept";

    /**
     * 能够接受的编码方式列表
     */
    public static final String ACCEPT_ENCODING = "Accept-Encoding";

    /**
     * 能够接受的回应内容的自然语言列表
     */
    public static final String ACCEPT_LANGUAGE = "Accept-Language";

    /**
     * 该浏览器想要优先使用的连接类型
     */
    public static final String CONNECTION = "Connection";

    /**
     * 以八位字节数组（8位的字节）表示的请求体的长度
     */
    public static final String CONTENT_LENGTH = "Content-Length";

    /**
     * 请求体的多媒体类型
     */
    public static final String CONTENT_TYPE = "Content-Type";

    /**
     * 超文本传输协议Cookie
     */
    public static final String COOKIE = "Cookie";

    /**
     * 服务器的域名(用于虚拟主机)，以及服务器所监听的传输控制协议端口号
     */
    public static final String HOST = "Host";

    /**
     * 发起一个针对 跨来源资源共享 的请求
     */
    public static final String ORIGIN = "Origin";

    /**
     * 表示浏览器所访问的前一个页面，正是那个页面上的某个链接将浏览器带到了当前所请求的这个页面
     */
    public static final String REFERER = "Referer";

    /**
     * 浏览器的浏览器身份标识字符串
     */
    public static final String USER_AGENT = "User-Agent";

    /**
     * 能够接受的字符集
     */
    public static final String ACCEPT_CHARSET = "Accept-Charset";

    /**
     * 能够接受的按照时间来表示的版本
     */
    public static final String ACCEPT_DATETIME = "Accept-Datetime";

    /**
     * 用于超文本传输协议的认证的认证信息
     */
    public static final String AUTHORIZATION = "Authorization";

    /**
     * 用来指定在这次的请求/响应链中的所有缓存机制 都必须 遵守的指令
     */
    public static final String CACHE_CONTROL = "Cache-Control";

    /**
     * 请求体的内容的二进制 MD5 散列值，以 Base64 编码的结果
     */
    public static final String CONTENT_MD5 = "Content-MD5";

    /**
     * 发送该消息的日期和时间
     */
    public static final String DATE = "Date";

    /**
     * 表明客户端要求服务器做出特定的行为
     */
    public static final String EXPECT = "Expect";

    /**
     * 发起此请求的用户的邮件地址
     */
    public static final String FROM = "From";

    /**
     * 仅当客户端提供的实体与服务器上对应的实体相匹配时，才进行对应的操作。主要作用时，用作像 PUT 这样的方法中，仅当从用户上次更新某个资源以来，该资源未被修改的情况下，才更新该资源
     */
    public static final String IF_MATCH = "If-Match";

    /**
     * 允许在对应的内容未被修改的情况下返回304未修改
     */
    public static final String IF_MODIFIED_SINCE = "If-Modified-Since";

    /**
     * 允许在对应的内容未被修改的情况下返回304未修改
     */
    public static final String IF_NONE_MATCH = "If-None-Match";

    /**
     * 如果该实体未被修改过，则向我发送我所缺少的那一个或多个部分；否则，发送整个新的实体
     */
    public static final String IF_RANGE = "If-Range";

    /**
     * 仅当该实体自某个特定时间已来未被修改的情况下，才发送回应
     */
    public static final String IF_UNMODIFIED_SINCE = "If-Unmodified-Since";

    /**
     * 限制该消息可被代理及网关转发的次数
     */
    public static final String MAX_FORWARDS = "Max-Forwards";

    /**
     * 与具体的实现相关，这些字段可能在请求/回应链中的任何时候产生多种效果
     */
    public static final String PRAGMA = "Pragma";

    /**
     * 用来向代理进行认证的认证信息
     */
    public static final String PROXY_AUTHORIZATION = "Proxy-Authorization";

    /**
     * 仅请求某个实体的一部分
     */
    public static final String RANGE = "Range";

    /**
     * 浏览器预期接受的传输编码方式：可使用回应协议头 Transfer-Encoding 字段中的值；另外还可用"trailers"（与"分块 "传输方式相关）这个值来表明浏览器希望在最后一个尺寸为0的块之后还接收到一些额外的字段
     */
    public static final String TE = "TE";

    /**
     * 要求服务器升级到另一个协议
     */
    public static final String UPGRADE = "Upgrade";

    /**
     * 向服务器告知，这个请求是由哪些代理发出的
     */
    public static final String VIA = "Via";

    /**
     * 一个一般性的警告，告知，在实体内容体中可能存在错误
     */
    public static final String WARNING = "Warning";

    /**
     * 请求某个网页应用程序停止跟踪某个用户
     */
    public static final String DNT = "dnt";

    /**
     * 一个事实标准 ，用于标识某个通过超文本传输协议代理或负载均衡连接到某个网页服务器的客户端的原始互联网地址
     */
    public static final String X_FORWARDED_FOR = "X-Forwarded-For";

    /**
     * 一个事实标准 ，用于识别客户端原本发出的 Host 请求头部
     */
    public static final String X_FORWARDED_HOST = "X-Forwarded-Host";

    /**
     * 一个事实标准，用于标识某个超文本传输协议请求最初所使用的协议
     */
    public static final String X_FORWARDED_PROTO = "X-Forwarded-Proto";

    /**
     * 被微软的服务器和负载均衡器所使用的非标准头部字段
     */
    public static final String FRONT_END_HTTPS = "Front-End-Https";

    /**
     * 请求某个网页应用程序使用该协议头字段中指定的方法（一般是PUT或DELETE）来覆盖掉在请求中所指定的方法（一般是POST）。当某个浏览器或防火墙阻止直接发送PUT 或DELETE
     * 方法时（注意，这可能是因为软件中的某个漏洞，因而需要修复，也可能是因为某个配置选项就是如此要求的，因而不应当设法绕过），可使用这种方式
     */
    public static final String X_HTTP_METHOD_OVERRIDE = "X-Http-Method-Override";

    /**
     * 使服务器更容易解读AT&T设备User-Agent字段中常见的设备型号、固件信息
     */
    public static final String X_ATT_DEVICEID = "X-ATT-DeviceId";

    /**
     * 链接到互联网上的一个XML文件，其完整、仔细地描述了正在连接的设备
     */
    public static final String X_WAP_PROFILE = "X-Wap-Profile";

    /**
     * 该字段源于早期超文本传输协议版本实现中的错误
     */
    public static final String PROXY_CONNECTION = "Proxy-Connection";

    /**
     * 用于防止跨站请求伪造
     */
    public static final String X_CSRF_TOKEN = "X-Csrf-Token";
}