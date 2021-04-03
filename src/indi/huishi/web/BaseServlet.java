package indi.huishi.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

public abstract class BaseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        //        if (req.getParameter("action").equals("login")) {
//            login(req, resp);
//        } else if (req.getParameter("action").equals("regist")) {
//            regist(req, resp);
//        }
        // 使用反射优化大量 else if 代码
        try {//只需要写功能了
            Method declaredMethod = this.getClass().getDeclaredMethod(req.getParameter("action"),HttpServletRequest.class,HttpServletResponse.class);
            System.out.println(declaredMethod);//protected void indi.huishi.web.UserServlet.login(javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse) throws javax.servlet.ServletException,java.io.IOException

            // 调用
            declaredMethod.invoke(this, req, resp);//没有参数  regist方法
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);//抛异常给transactionFilter
        }
    }
}
