package indi.huishi.test;

import indi.huishi.dao.OrderDao;
import indi.huishi.dao.impl.OrderDaoImpl;
import indi.huishi.pojo.Order;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDaoTest {
    OrderDao orderDao = new OrderDaoImpl();

    @Test
    public void saveOrder(){
        Order order = new Order("5383578357",new Date(),new BigDecimal(144),0,2);
        int i = orderDao.saveOrder(order);
        System.out.println(i);
    }

    @Test
    public void queryOrder(){
        List<Order> orders = orderDao.queryOrders();
        System.out.println(orders);
    }

//    public int saveOrder(Order order);
//
//    /**
//     * 查看全部订单（管理员）
//     */
//    public List<Order> queryOrders();
//
//    /**
//     * 修改某个订单状态（管理员）
//     * @param orderId
//     * @param status
//     * @return
//     */
//    public int updateOrderStatus(int orderId, int status);
//
//    /**
//     * 用户根据id查看订单信息（用户）
//     * @param userId
//     * @return
//     */
//    public Order queryByUserId(int userId);
}
