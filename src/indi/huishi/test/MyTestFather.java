package indi.huishi.test;

import java.lang.reflect.Method;

public abstract class MyTestFather {
    public void father(String string){
        System.out.println("父类调用");
        try {
            Method declaredMethod = this.getClass().getDeclaredMethod(string);
            System.out.println(declaredMethod);
            declaredMethod.invoke(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
