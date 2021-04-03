package indi.huishi.utils;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.print.DocFlavor.STRING;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import sun.security.util.Password;

/**
 * 功能：封装数据库的连接与关闭。 使用德鲁伊数据库连接池
 * @author AsajuHuishi
 *
 */
public class JdbcUtils {
    private static DruidDataSource dataSource;
    private static ThreadLocal<Connection> conns = new ThreadLocal<>();
    static{
        try {
            Properties properties = new Properties();
//            properties.load(new FileInputStream("src\\jdbc.properties"));
            InputStream inputStream = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            properties.load(inputStream);
//            System.out.println(properties);
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    /**
     * 建立连接
     * @return
     * @throws Exception
     */
    public static Connection getConnection() throws Exception {
        Connection connection = conns.get();// 确保同一个连接
        if (connection==null){//如果ThreadLocal里面没有连接，就从数据库连接池获取连接
    //            System.out.println("获取连接");
            connection = dataSource.getConnection();
            // 保存到conns 也就是ThreadLocal对象
            conns.set(connection);
            // 手动管理
            connection.setAutoCommit(false);
        }
        return connection;
    }

    /**
     * 关闭
     * @param connection
     */
//    public static void close(Connection connection){//通用 因为使用DBUtils，不需要resultset和statement
//        try {
//            if (connection != null) {
//                connection.close();
//            }
//        } catch (Exception e) {
//            // TODO: handle exception
//            throw new RuntimeException(e);
//        }
//    }

    /**
     * 提交事务，关闭连接
     */
    public static void commitAndClose() throws SQLException {
        Connection connection = conns.get();
        if(connection!=null){// 如果这个连接使用过
            connection.commit();    //提交事务
            connection.close();     //关闭连接
        }
        // 一定要执行remove 否则会出错 因为Tomcat底层使用了线程池
        conns.remove();
    }

    /**
     * 回滚事务，关闭连接
     */
    public static void rollbackAndClose() {
        Connection connection = conns.get();
        if (connection != null) {// 如果这个连接使用过
            try {
                connection.rollback();    //回滚事务
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                connection.close();     //关闭连接
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        // 一定要执行remove 否则会出错 因为Tomcat底层使用了线程池
        conns.remove();
    }
}

