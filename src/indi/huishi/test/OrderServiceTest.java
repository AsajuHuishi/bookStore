package indi.huishi.test;

import indi.huishi.pojo.Cart;
import indi.huishi.pojo.CartItem;
import indi.huishi.service.OrderService;
import indi.huishi.service.impl.OrderServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

public class OrderServiceTest {
    private OrderService orderService = new OrderServiceImpl();

    @Test
    public void createOrder(){
        // 生成一个购物车
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"Java从入门到放弃",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1,"Java从入门到放弃",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2,"数据结构",1,new BigDecimal(500),new BigDecimal(500)));
        Integer userId = 1;
        String order = orderService.createOrder(cart, userId);
        System.out.println("订单号"+order);
    }
}
