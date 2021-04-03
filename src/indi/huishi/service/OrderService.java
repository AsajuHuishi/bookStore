package indi.huishi.service;

import indi.huishi.pojo.*;

import java.util.List;

public interface OrderService {
    // 根据购物车和用户id 生成订单
    public String createOrder(Cart cart, Integer id);

    // 查询所有订单
    public List<Order> showAllOrders();

    // 查看所有订单分页
    Page<Order> page(int pageNo, int pageSize);

    // 查看订单详情（所有订单项）
    List<OrderItem> showOrderDetail(String orderId);

    // 修改订单状态
    int updateStatus(String orderId, int status);

    // 查看我的订单(分页)
    Page<Order> pageForMyOrder(int userId, int pageNo, int pageSize);
}
