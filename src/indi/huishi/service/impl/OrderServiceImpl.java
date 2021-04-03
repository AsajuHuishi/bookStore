package indi.huishi.service.impl;

import indi.huishi.dao.BookDao;
import indi.huishi.dao.OrderDao;
import indi.huishi.dao.OrderItemDao;
import indi.huishi.dao.impl.BaseDao;
import indi.huishi.dao.impl.BookDaoImpl;
import indi.huishi.dao.impl.OrderDaoImpl;
import indi.huishi.dao.impl.OrderItemDaoImpl;
import indi.huishi.pojo.*;
import indi.huishi.service.OrderService;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();
    private BookDao bookDao = new BookDaoImpl();

    /**
     * 根据购物车生成订单和订单项
     * @param cart
     * @param userId
     * @return  订单号
     */
    @Override
    public String createOrder(Cart cart, Integer userId) {
//        System.out.println("OrderServlet当前线程"+Thread.currentThread().getName());
        // 首先生成订单
        String orderId = System.currentTimeMillis()+""+userId;
        Order order = new Order(orderId, new Date(), cart.getTotalPrice(), 0, userId);

        // 以下三个Dao操作需要事务，放在事务里面
        /*如何确保使用同一个connection对象？ 使用ThreadLocal对象。
        threadLocal确保所有操作使用同一个连接对象，所有操作都必须在同一个线程中。
        * */
        /*
        * 获取连接
        * try{
        *   conn.setAutoCommit(false);
        *   jdbc操作 三个Dao操作
        *   conn.commit() 提交事务
        * }catch{
        *   conn.rollback() 回滚
        * }finally{
        *   JDBCUtils.close(conn)
        * }
        * */
        // 1.保存订单
        int i = orderDao.saveOrder(order);
        System.out.println(i>0?"保存订单成功":"保存订单失败");
//        int x = 12/0;
        // 遍历购物车中的商品项Map集合 分别保存到订单项
        Map<Integer, CartItem> items = cart.getItems();
        for(Map.Entry<Integer,CartItem>entry: items.entrySet()){
            Integer id = entry.getValue().getId();
            String name = entry.getValue().getName();
            Integer count = entry.getValue().getCount();
            BigDecimal price = entry.getValue().getPrice();
            BigDecimal totalPrice = entry.getValue().getTotalPrice();
            OrderItem orderItem = new OrderItem(id,name,count,price,totalPrice,orderId);//共用订单id
            // 2.保存订单项
            int i1 = orderItemDao.saveOrderItem(orderItem);
            System.out.println(i1>0?"保存订单项成功":"保存订单项失败");
            // 3.更新库存和销量
            Book book = bookDao.queryBookById(id);
            book.setSales(book.getSales() + count);// 卖出的增加了
            book.setStock(book.getStock() - count);// 库存减少了
            bookDao.updateBook(book);
        }
        // 清空购物车
        cart.clear();
        // 返回订单id
        return  orderId;

    }

    /**
     * 查看所有订单
     */
    public List<Order> showAllOrders(){
        List<Order> orders = orderDao.queryOrders();
//        System.out.println(orders);
        return orders;
    }
    /**
     * 查看所有订单(分页)
     */
    @Override
    public Page<Order> page(int pageNo, int pageSize) {
        Page<Order> page = new Page<Order>();
        //设置5属性

        // 每页数量
        page.setPageSize(pageSize);
        // 总记录数
        Integer pageTotalCount = orderDao.queryForPageTotalCount();
        page.setPageTotalCount(pageTotalCount);
        // 总页码
        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0){
            pageTotal++;
        }
        page.setPageTotal(pageTotal);
        // 当前页
        page.setPageNo(pageNo);

        // 当前页数据
        Integer begin = (pageNo-1)*pageSize;//开始索引
        List<Order> items = orderDao.queryForPageItems(begin,pageSize);
        page.setItems(items);

        return page;
    }


    @Override
    public List<OrderItem> showOrderDetail(String orderId) {
        return orderItemDao.queryOrderItemByOrderId(orderId);
    }

    @Override
    public int updateStatus(String orderId, int status) {
        return orderDao.updateOrderStatus(orderId, status);
    }

    @Override
    public Page<Order> pageForMyOrder(int userId, int pageNo, int pageSize) {
        Page<Order> page = new Page<Order>();
        //设置5属性

        // 每页数量
        page.setPageSize(pageSize);
        // 总记录数
        Integer pageTotalCount = orderDao.queryForPageTotalCount();
        page.setPageTotalCount(pageTotalCount);
        // 总页码
        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0){
            pageTotal++;
        }
        page.setPageTotal(pageTotal);
        // 当前页
        page.setPageNo(pageNo);

        // 当前页数据
        Integer begin = (pageNo-1)*pageSize;//开始索引
        List<Order> items = orderDao.queryByUserIdForPageItems(userId, begin, pageSize);
        page.setItems(items);

        return page;
    }
}
