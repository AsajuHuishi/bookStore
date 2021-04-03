package indi.huishi.dao.impl;

import indi.huishi.dao.OrderItemDao;
import indi.huishi.pojo.OrderItem;

import java.util.List;

public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {
    @Override
    public int saveOrderItem(OrderItem orderItem) {
//        System.out.println("OrderServlet当前线程"+Thread.currentThread().getName());
        String sql = "insert into t_order_item(`name`,`count`,`price`,`total_price`,`order_id`) values(?,?,?,?,?)";
        return update(sql, orderItem.getName(), orderItem.getCount(), orderItem.getPrice(),orderItem.getTotalPrice(),orderItem.getOrderId());
    }

    @Override
    public List<OrderItem> queryOrderItemByOrderId(String orderId) {
        String sql = "select id,name,count,price,total_price as totalPrice ,order_id as orderId from t_order_item where order_id=?";
        return queryForMulti(OrderItem.class, sql, orderId);
    }
}
