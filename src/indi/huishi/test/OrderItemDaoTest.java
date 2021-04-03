package indi.huishi.test;

import indi.huishi.dao.OrderItemDao;
import indi.huishi.dao.impl.OrderItemDaoImpl;
import indi.huishi.pojo.OrderItem;
import org.junit.Test;

import java.math.BigDecimal;

public class OrderItemDaoTest {

    OrderItemDao orderItemDao = new OrderItemDaoImpl();
    @Test
    public void saveOrderItem(){
        int i = orderItemDao.saveOrderItem(new OrderItem(39, "西游记", 3, new BigDecimal(30), new BigDecimal(90), "5383578357"));
        System.out.println(i);
    }
}
