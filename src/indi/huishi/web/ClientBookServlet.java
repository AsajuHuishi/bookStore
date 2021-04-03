package indi.huishi.web;

import indi.huishi.pojo.Book;
import indi.huishi.pojo.Page;
import indi.huishi.service.BookService;
import indi.huishi.service.impl.BookServiceImpl;
import indi.huishi.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ClientBookServlet extends BaseServlet{

    private BookService bookService = new BookServiceImpl();
    /**
     * 处理分页
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("前台分页程序");
        // 获取请求参数pageNo,pageSize 默认点击进去是第一页
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"),1);
        int pageSize =  Page.PAGE_SIZE;

        // 调用bookService.page(pageNo,pageSize)
        Page<Book> bookPage = bookService.page(pageNo,pageSize);
        bookPage.setUrl("client/bookServlet?action=page");
        System.out.println(bookPage);
        // 保存page对象到request域
        req.setAttribute("page",bookPage);
        // 请求转发到book_manager.jsp
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
    }

    /**
     * 处理价格区间
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("处理价格区间");
        // 获取请求参数pageNo,pageSize ,min,max
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"),1);
        int pageSize =  Page.PAGE_SIZE;
        int max = WebUtils.parseInt(req.getParameter("max"),Integer.MAX_VALUE);
        int min = WebUtils.parseInt(req.getParameter("min"),0);
        // 建立Page对象
        Page<Book> bookPage = bookService.pageByPrice(pageNo,pageSize,min,max);

        StringBuilder stringBuilder = new StringBuilder("client/bookServlet?action=pageByPrice");
        // 如果有最小价格 追加到分页条请求参数
        if(req.getParameter("min")!=null){
            stringBuilder.append("&min=").append(req.getParameter("min"));
        }
        // 如果有最大价格 追加到分页条请求参数
        if(req.getParameter("max")!=null){
            stringBuilder.append("&max=").append(req.getParameter("max"));
        }

        bookPage.setUrl(stringBuilder.toString());//同时要保存min和max 因为后面都会用到 否则点击分页条消失
        System.out.println(bookPage);
        // 保存page对象到request域
        req.setAttribute("page",bookPage);
        // 

        // 请求转发到book_manager.jsp
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
    }
}
