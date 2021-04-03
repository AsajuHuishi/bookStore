package indi.huishi.test;

import indi.huishi.pojo.Cart;
import indi.huishi.pojo.CartItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * 测试购物车方法
 */
public class CartTest {

    @Test
    public void addItem() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"Java从入门到放弃",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1,"Java从入门到放弃",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2,"数据结构",1,new BigDecimal(500),new BigDecimal(500)));
        System.out.println(cart.toString());
    }

    @Test
    public void deleteItem() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"Java从入门到放弃",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1,"Java从入门到放弃",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2,"数据结构",1,new BigDecimal(500),new BigDecimal(500)));
        cart.deleteItem(1);
        System.out.println(cart);
    }

    @Test
    public void clear() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"Java从入门到放弃",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1,"Java从入门到放弃",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2,"数据结构",1,new BigDecimal(500),new BigDecimal(500)));
        cart.clear();
        System.out.println(cart);//Cart{totalCount=0, totalPrice=0, items={}}
    }

    @Test
    public void updateCount() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"Java从入门到放弃",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1,"Java从入门到放弃",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2,"数据结构",1,new BigDecimal(500),new BigDecimal(500)));
        cart.updateCount(1,6);
        System.out.println(cart);

    }
}