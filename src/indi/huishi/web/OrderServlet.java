package indi.huishi.web;

import com.google.gson.Gson;
import indi.huishi.pojo.*;
import indi.huishi.service.OrderService;
import indi.huishi.service.impl.OrderServiceImpl;
import indi.huishi.utils.JdbcUtils;
import indi.huishi.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OrderServlet extends BaseServlet{
    OrderService orderService = new OrderServiceImpl();
    /**
     * 去结账
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("结账");
        // cart和用户user都在session里
        User user = (User) req.getSession().getAttribute("user");
        // 如果User==null没有登录,转到登录页面
        if(user==null){
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
            return;
        }
//        System.out.println("OrderServlet当前线程"+Thread.currentThread().getName());
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if(cart!=null){
            // 使用事务
            String orderId = null;
            orderId = orderService.createOrder(cart, user.getId());
            req.getSession().setAttribute("orderId",orderId);
            resp.sendRedirect(req.getContextPath()+"/pages/cart/checkout.jsp");
        }
    }

    /**
     * 查询所有订单(未使用)
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void showAllOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("查询所有订单");
        // 获得日期 金额 详情（订单项） 发货状态
        List<Order> orders = orderService.showAllOrders();
        req.setAttribute("orders",orders);
        // 请求转发
        req.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(req,resp);
    }

    /**
     * 查询所有订单--分页
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void showAllOrdersByPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("查询所有订单分页");
        // 获取当前页数和每页的条目数量
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = 4;
        // 生成当前页的对象
        Page<Order> page = orderService.page(pageNo, pageSize);
        // 将请求跳转页面（点击下一页跳回到本页）的地址写入url
        page.setUrl("orderServlet?action=showAllOrdersByPage");
        // 保存到请求域
        req.setAttribute("pageOrder",page);
        // 请求转发
        req.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(req,resp);
    }

    /**
     * 查看订单详情
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void showOrderDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("查看订单详情...");
        // 获取订单id
        String orderId = req.getParameter("orderId");
        // 获取所有订单项
        List<OrderItem> orderItems = orderService.showOrderDetail(orderId);
        // 保存到请求域
        req.setAttribute("orderItems", orderItems);
        // 请求转发
        req.getRequestDispatcher("/pages/manager/order_manager_detail.jsp").forward(req,resp);
    }

    /**
     * 修改发货状态,发货
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void sendOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("修改发货状态...");
        // 获取订单id，当前状态
        String orderId = req.getParameter("orderId");
        String status = req.getParameter("status");
        int stat = WebUtils.parseInt(status, -1);
        // 结果
        if (stat!=0){
            resp.getWriter().write(new Gson().toJson("已发货"));
        } else{
            stat = 1;
            int res = orderService.updateStatus(orderId, stat);
            if (res!=0){
                System.out.println("发货成功");
                resp.getWriter().write(new Gson().toJson("发货成功 待签收"));
            }else{
                resp.getWriter().write(new Gson().toJson("发货失败"));
            }
        }
    }


    /**
     * 查看我的订单（分页）
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void showMyOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("查看我的订单");
        // 获取用户id 保存在Session域对象
        User user = (User)req.getSession().getAttribute("user");
        Integer userId = user.getId();
        // 获取当前页和每页条目数
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"),1);
        int pageSize = 4;
        // 分页查找该用户的订单
        Page<Order> orderPage = orderService.pageForMyOrder(userId, pageNo, pageSize);
        System.out.println(orderPage);
        // 将其保存到请求域
        req.setAttribute("pageOrder", orderPage);
        // 跳转到页面
        req.getRequestDispatcher("/pages/order/order.jsp").forward(req,resp);
    }

    /**
     * 修改订单状态：用户签收
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void receiveOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("用户签收");
        // 和商家发货一样，本质上还是获取订单id和发货状态
        String orderId = req.getParameter("orderId");
        // 获取发货状态status 正常应该是1(已发货才能签收)
        int status = WebUtils.parseInt(req.getParameter("status"), -1);
        if(status!=1){
            resp.getWriter().write(new Gson().toJson("状态异常，签收失败"));
        }else{
            status = status + 1;
            // 修改
            int result = orderService.updateStatus(orderId, status);
            if (result!=0){
                resp.getWriter().write(new Gson().toJson("已签收"));
            }else{
                resp.getWriter().write(new Gson().toJson("签收失败"));
            }
        }
    }
}
