package indi.huishi.dao;

import indi.huishi.pojo.OrderItem;

import java.util.List;

public interface OrderItemDao {
    public int saveOrderItem(OrderItem orderItem);

    public List<OrderItem> queryOrderItemByOrderId(String id);
}
