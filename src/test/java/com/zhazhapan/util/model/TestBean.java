package com.zhazhapan.util.model;

import com.zhazhapan.modules.constant.ValueConsts;
import com.zhazhapan.util.annotation.FieldChecking;

/**
 * @author pantao
 * @since 2018/7/17
 */
@FieldChecking(code = 200, message = "测试通过", status = ValueConsts.SUCCESS)
public class TestBean {

    /**
     * 此字段不进行验证
     */
    public boolean none;

    @FieldChecking(message = "{}必须大于0", expression = "val!=null&&val>0")
    public Integer id;

    /**
     * 使用默认设置
     */
    @FieldChecking()
    public String defaultChecking;

    @FieldChecking(message = "用户名长度必须是4~16位的字符串", expression = "val!=null&&val.length()>3&&val.length()<17")
    public String username;

    /**
     * 正则匹配（用英文冒号“:”开头，匹配时开头的英文冒号“:”会被忽略）
     */
    @FieldChecking(message = "邮箱不合法", expression = ":^[a-z]+@[a-z]+\\.[a-z]+$")
    public String email;

    @FieldChecking(message = "年龄必须0且小于150", expression = "val!=null&&val>0&&val<150")
    public Integer age;
}
