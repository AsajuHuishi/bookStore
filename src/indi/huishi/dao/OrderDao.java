package indi.huishi.dao;

import indi.huishi.pojo.Book;
import indi.huishi.pojo.Order;

import java.util.List;

public interface OrderDao {
    /**
     * 保存订单
     * @param order
     */
    public int saveOrder(Order order);

    /**
     * 查看全部订单（管理员）
     */
    public List<Order> queryOrders();

    /**
     * 修改某个订单状态（管理员）
     * @param orderId
     * @param status
     * @return
     */
    public int updateOrderStatus(String orderId, int status);

    /**
     * 用户根据id查看订单信息（用户）,一个用户可以有多个订单
     * @param userId
     * @return
     */
    public List<Order> queryByUserIdForPageItems(int userId, int begin, int pageSize);

    /**
     * 查看所有条目数
     * @return
     */
    public int queryForPageTotalCount();

    /**
     * 查看当前页信息
     * @param begin
     * @param pageSize
     * @return
     */
    List<Order> queryForPageItems(Integer begin, int pageSize);
}
