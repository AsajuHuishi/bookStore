package indi.huishi.web;

import indi.huishi.pojo.Book;
import indi.huishi.pojo.Page;
import indi.huishi.service.BookService;
import indi.huishi.service.UserService;
import indi.huishi.service.impl.BookServiceImpl;
import indi.huishi.service.impl.UserServiceImpl;
import indi.huishi.utils.WebUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class BookServlet extends BaseServlet {

    private BookService bookService = new BookServiceImpl();
    // 获取图书的list，将结果回传到book_manager.jsp
    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book> bookList = bookService.queryBooks();
        // 保存到request域
        req.setAttribute("booklist",bookList);
        // 请求转发
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }

    /*
    处理分页
     */
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取请求参数pageNo,pageSize 默认点击进去是第一页
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"),1);
        int pageSize =  Page.PAGE_SIZE;

        // 调用bookService.page(pageNo,pageSize)
        Page<Book> bookPage = bookService.page(pageNo,pageSize);
        bookPage.setUrl("manager/bookServlet?action=page");
        // 保存page对象到request域
        req.setAttribute("page",bookPage);
        // 请求转发到book_manager.jsp
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }
    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getParameterMap());
        Book book = WebUtils.copyParamToBean(req.getParameterMap(),new Book());
        bookService.addBook(book);
        String pageNo = req.getParameter("pageNo"); //把删除时的当前页记录下来，跳转的时候直接跳回到此页 不需要转成整数了
        System.out.println("shuchu "+pageNo);
        // 跳回去显示因为上个页面是list显示所有书籍，所以资源路径就是这个
        // 请求转发刷新会多次执行增加操作
//        req.getRequestDispatcher("/manager/bookServlet?action=list").forward(req,resp);
        // 重定向
//        System.out.println(req.getContextPath()+"/manager/bookServlet?action=list");
//        resp.sendRedirect(req.getContextPath()+"/manager/bookServlet?action=list");
        resp.sendRedirect(req.getContextPath()+"/manager/bookServlet?action=page&pageNo="+pageNo);
    }

    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String pageNo = req.getParameter("pageNo"); //把删除时的当前页记录下来，跳转的时候直接跳回到此页 不需要转成整数了
        bookService.deleteBookById(Integer.parseInt(id));// 在后端删除改行数据
        // 重定向到显示页面
        resp.sendRedirect(req.getContextPath()+"/manager/bookServlet?action=page&pageNo="+pageNo);

    }

    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 按id修改
        // 将修改的内容注入bean
        String pageNo = req.getParameter("pageNo"); //把删除时的当前页记录下来，跳转的时候直接跳回到此页 不需要转成整数了
//        System.out.println("shuchu "+pageNo);
        Book book = WebUtils.copyParamToBean(req.getParameterMap(),new Book());
        bookService.updateBook(book);
        // 重定向到显示页面
        resp.sendRedirect(req.getContextPath()+"/manager/bookServlet?action=page&pageNo="+pageNo);
    }

    protected void queryBookById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String pageNo = req.getParameter("pageNo"); //把删除时的当前页记录下来，跳转的时候直接跳回到此页 不需要转成整数了
        // 按id查找book
        Book book = bookService.queryBookById(Integer.parseInt(id));
        req.setAttribute("book",book);
        req.setAttribute("flag","update");//区分增加还是请求的标志
        // 请求转发到编辑页面，默认显示book以待修改
        req.getRequestDispatcher("/pages/manager/book_edit.jsp?pageNo="+pageNo).forward(req,resp);
    }
}
