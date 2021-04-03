package indi.huishi.test;

public class OrderService {
    public void createOrder(){
        String name = Thread.currentThread().getName();
//        System.out.println("OrderService当前线程"+name+"数据是"+ThreadLocalTest.map.get(name));
        System.out.println("OrderService当前线程"+name+"数据是"+ThreadLocalTest.threadLocal.get());
    }
}
