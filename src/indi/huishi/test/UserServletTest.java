package indi.huishi.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 采用反射方式 将表单提交<input type="hidden" name="action" value="regist"/>，获取value值，然后调用和value值对应的函数名
 */
public class UserServletTest {
    public void login(){
        System.out.println("login方法");
    }
    public void regist(){
        System.out.println("regist方法");
    }
    public void updateUser(){
        System.out.println("updateUser方法");
    }

    public void updatePassword(){
        System.out.println("updatePassword方法");
    }

    public static void main(String[] args) {
        String action = "regist";
        try {
            Method declaredMethod = UserServletTest.class.getDeclaredMethod(action);//只需要写功能了
            System.out.println(declaredMethod);//public void indi.huishi.test.UserServletTest.login()
            // 调用
            declaredMethod.invoke(new UserServletTest());//没有参数  regist方法
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
