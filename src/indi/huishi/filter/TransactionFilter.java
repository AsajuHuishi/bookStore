package indi.huishi.filter;

import indi.huishi.utils.JdbcUtils;

import javax.servlet.*;
import java.io.IOException;

public class TransactionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    // 为所有业务添加事务
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            //拦截所有Servlet，从而实现拦截所有service方法，实现事务管理
            filterChain.doFilter(servletRequest, servletResponse);
            JdbcUtils.commitAndClose();//提交事务
        }catch (Exception e){
            JdbcUtils.rollbackAndClose();//回滚事务
            e.printStackTrace();
            throw new RuntimeException(e);//把异常抛给Tomcat服务器展示页面
        }
    }

    @Override
    public void destroy() {

    }
}
