package indi.huishi.utils;

import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class WebUtils {
    // 将Map注入bean
    public static <T> T copyParamToBean(Map map, T bean){
        try {
            System.out.println("注入之前"+bean);
            // 把Map注入
            BeanUtils.populate(bean, map);
            System.out.println("注入之后"+bean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }

    /*
    将字符串转为int
     */
    public static int parseInt(String string,int defaultValue){
        try {
            System.out.println("输入的string"+string);
//            if(string!=null) {
            return Integer.parseInt(string);
//            } else{
//                System.out.println("null");
//                return defaultValue;
//            }
        } catch (Exception e) {
//            e.printStackTrace();
        }
        return defaultValue;
    }

}
