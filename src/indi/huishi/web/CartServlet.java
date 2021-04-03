package indi.huishi.web;

import com.google.gson.Gson;
import indi.huishi.pojo.Book;
import indi.huishi.pojo.Cart;
import indi.huishi.pojo.CartItem;
import indi.huishi.service.BookService;
import indi.huishi.service.impl.BookServiceImpl;
import indi.huishi.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CartServlet extends BaseServlet{
    private BookService bookService = new BookServiceImpl();
    /**
     * 加入购物车
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println(req.getParameter("id"));
        System.out.println("加入购物车");
        // 根据id调用bookService.queryBookById得到图书的信息
        // 把图书信息转为cartItems
        // 调用cart.addItem(cartItems)
        // 重定向商品列表页面
        Integer id = WebUtils.parseInt(req.getParameter("id"),0);
        Book book = bookService.queryBookById(id);
        CartItem cartItem = new CartItem(book.getId(), book.getName(),1,book.getPrice(),book.getPrice());
//        Cart cart = new Cart();// 注意 不能每次请求都新建一个购物车！放在session里面，如果没有才创建cart
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart==null){
            cart = new Cart();
            req.getSession().setAttribute("cart",cart);
        }

        cart.addItem(cartItem);

        // 保存最后一个加入购物车的商品名称
        req.getSession().setAttribute("lastName",cartItem.getName());
//        System.out.println(cartItem);
//        System.out.println(cart);
        // 跳回原来的当前页面，得把地址发过来，这样就可以重定向到原来页面
        // 使用Referer获得请求时的地址 哪来的跳哪去
//        System.out.println(req.getHeader("Referer"));//http://localhost:8080/07_book/pages/test/dzcp/index.jsp
        resp.sendRedirect(req.getHeader("Referer"));
    }

    /**
     * 使用ajax方法添加购物车
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void addItemAjax(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取请求
        String id = req.getParameter("id");
        // 获取购物车
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart==null){
            cart = new Cart();
            req.getSession().setAttribute("cart",cart);
        }
        // 根据书的id生成一个商品项对象
        // 首先得根据书的id 查找对应的记录
        Book book = bookService.queryBookById(WebUtils.parseInt(id, 0));
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        // 将商品项添加到购物车
        cart.addItem(cartItem);
        // 返回商品数量和最后购买的商品名称
        Map<String, Object> map = new HashMap<>();
        map.put("total",cart.getTotalCount());
        map.put("lastName",cartItem.getName());
        System.out.println("map:"+map);
        // 转化为JSon
        resp.getWriter().write(new Gson().toJson(map));
    }

    /**
     * 删除商品项
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("删除商品项");
        int id = WebUtils.parseInt(req.getParameter("id"),0);
        // 获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart!=null){
            // 删除商品项
            cart.deleteItem(id);
            resp.sendRedirect(req.getHeader("Referer"));
        }

    }

    /**
     * 清空购物车
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("清空购物车");
        // 获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        cart.clear();
        resp.sendRedirect(req.getHeader("Referer"));
    }


    /**
     * 修改商品项数量
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("修改商品项数量");
        // 获取id和count请求参数
        int id = WebUtils.parseInt(req.getParameter("id"),0);
        int count = WebUtils.parseInt(req.getParameter("count"), 1);
        // 获取cart对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        // 修改了商品项的数量
        if (cart!=null){
            cart.updateCount(id, count);
        }
        // 修改完成，重定向到原来页面
        resp.sendRedirect(req.getHeader("Referer"));
    }
}
