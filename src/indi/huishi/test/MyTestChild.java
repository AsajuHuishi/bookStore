package indi.huishi.test;

import java.lang.reflect.Method;

public class MyTestChild extends MyTestFather{
    public void fun1(){
        System.out.println("子类fun1调用");
    }

    public static void main(String[] args) throws NoSuchMethodException {
        new MyTestChild().father("fun1");
    }
}
