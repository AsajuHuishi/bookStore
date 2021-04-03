package indi.huishi.i18n;

import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.ResourceBundle;


public class I18nTest {
    @Test
    public void testLocale(){
        // 默认语言国家信息
        System.out.println(Locale.getDefault());//zh_CN
        System.out.println(Locale.CHINA);//zh_CN
    }

    @Test
    public void testI18n(){
        Locale us = Locale.US;
        // 通过locale对象和指定basename读取响应配置文件
        ResourceBundle bundle = ResourceBundle.getBundle("i18n", us);
        String username = bundle.getString("username");
        String password = bundle.getString("password");
        System.out.println("用户名"+username);
        System.out.println("密码"+password);
        Locale china = Locale.CHINA;
        // 通过locale对象和指定basename读取响应配置文件
        ResourceBundle bundle1 = ResourceBundle.getBundle("i18n", china);
        String username1 = new String(bundle1.getString("username").getBytes(StandardCharsets.ISO_8859_1),StandardCharsets.UTF_8);
        String password1 = new String(bundle1.getString("password").getBytes(StandardCharsets.ISO_8859_1),StandardCharsets.UTF_8);
        System.out.println("用户名"+username1);
        System.out.println("密码"+password1);
        //用户名username
        //密码password
        //用户名用户名
        //密码密码
    }
}
