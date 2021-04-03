package indi.huishi.test;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class ThreadLocalTest {
    // 线程安全的map
//    public final static Map<String,Object> map = new ConcurrentHashMap<>();
    // 直接使用threadLocal：代替concurrenthashmap
    public static ThreadLocal<Object> threadLocal = new ThreadLocal<>();

    private static Random random = new Random();
    public static class Task implements Runnable{
        @Override
        public void run() {
            // 随机生成一个变量，以当前线程名为key保存到map中
            int i = random.nextInt(1000);//0-999
            String name = Thread.currentThread().getName();//当前线程名
            System.out.println("线程"+name+"生成随机数是"+i);
            // 保存
//            map.put(name, i);
            threadLocal.set(i);//只能关联一个数据
            // 睡觉
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            new OrderService().createOrder();
            // 在run方法结束之前，以当前线程获取数据，看是否可以取出
//            Object o = map.get(name);
            Object o = threadLocal.get();// 它直接能取出你存进去的值
            System.out.println("在线程"+name+"快结束时取出数据"+o);
        }
    }

    public static void main(String[] args) {
        // 开启三个线程
        for (int i = 0; i < 3; i++) {
            new Thread(new Task()).start();
        }
    }

}
