package indi.huishi.dao.impl;

import indi.huishi.dao.OrderDao;
import indi.huishi.pojo.Book;
import indi.huishi.pojo.Order;

import java.util.List;

public class OrderDaoImpl extends BaseDao implements OrderDao {
    @Override
    public int saveOrder(Order order) {
        String sql = "insert into t_order(order_id, create_time, price, status, user_id) values(?,?,?,?,?)";
        return update(sql,order.getOrderId(),order.getCreateTime(),order.getPrice(),order.getStatus(),order.getUserId());
    }

    @Override
    public List<Order> queryOrders() {
        String sql = "select order_id as orderId, create_time as createTime, price, status, user_id as userId from t_order";
        return queryForMulti(Order.class, sql);
    }

    @Override
    public int updateOrderStatus(String orderId, int status) {
        String sql = "update t_order set status=? where order_id=?";
        return update(sql, status, orderId);
    }

    @Override
    public List<Order> queryByUserIdForPageItems(int userId, int begin, int pageSize) {
        String sql = "select order_id as orderId, create_time as createTime, price, status, user_id as userId from t_order where user_id=? order by create_time limit ?,?";
        return queryForMulti(Order.class, sql, userId, begin, pageSize);
    }

    /*
    以下用于分页显示
     */
    @Override
    public int queryForPageTotalCount() {
        String sql = "select count(*) from t_order";
        Number number = (Number) queryForSingleValue(sql);
        return number.intValue() ;
    }

    @Override
    public List<Order> queryForPageItems(Integer begin, int pageSize) {
        String sql = "select order_id as orderId, create_time as createTime, price, status, user_id as userId from t_order" +
                " order by create_time limit ?,?";
        return queryForMulti(Order.class, sql, begin, pageSize);
    }
}
