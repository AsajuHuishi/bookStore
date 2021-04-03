package indi.huishi.test;

import org.junit.Test;

import java.sql.Timestamp;
import java.util.Date;

public class MyTest {

    public static void main(String[] args) {
//        Boolean y = false;
//        String x = "asd"+ y;
//        System.out.println(x);

//        System.out.println('a'+1+"hello");//98hello
//
//        short a = 5;
//        int i = a + '2';
//        float f = 3+3;

//        System.out.println(0x123==0b100100011);
//        System.out.println(0x100==0b100000000);

//        System.out.println(3510/1000*1000);//3000
//        short s = 3;
////        s = s+2;
//        s += 2;
//        int i = 1;
//        i *= 0.1;
//        System.out.println(i);//0
//        i++;
//        System.out.println(i);//1
//        int m = 2;
//        int n = 3;
//        n *= m++;
//        System.out.println("m=" + m);//3
//        System.out.println("n=" + n);//6

//        int n = 10;
//        n += (n++) + (++n);
//        System.out.println(n);//32
//        int n = 10;
//        n += (++n) + (n++);
//        System.out.println(n);//32
//        int x = 1,y = 1;
//        if(x++==1 || ++y==1){
//            x =7;
//        }
//        System.out.println("x="+x+",y="+y);
//
//          boolean x=true;
//          boolean y=false;
//          short z=42;
//    //if(y == true)
//          if((z++==42)&&(y=true))z++;
//          System.out.println(z);//44
//          if((x=false) || (++z==45)) z++;
//
//          System. out.println("z="+z);//46
//            int n = 10;
//            n += (n++);
//            System.out.println(n);//20
//        n = 10;
//        n = n + (n++);
//        System.out.println(n);//20
//
//        int k = 2;
//        int b = k++;
//        System.out.println(k);
        int x = 4;
        int y = 1;
        if (x > 2) {
            if (y > 2)
                System.out.println(x + y);
            System.out.println("atguigu");
        } else
            System.out.println("x is " + x);

    }

    @Test
    public void test(){
        System.out.println(System.currentTimeMillis());

    }

    @Test
    public void test2(){
//        DateTime dateTime = new DateTime();
//        System.out.println(new Date());
//        System.out.println(new java.sql.Date(2011,5,1));
        long time = new Date().getTime();
        System.out.println(time);//milliSeconsd
        Timestamp timestamp = new Timestamp(time);
        System.out.println(timestamp);
    }


}
