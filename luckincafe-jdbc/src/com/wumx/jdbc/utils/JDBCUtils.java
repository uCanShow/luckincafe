package com.wumx.jdbc.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * @author jmwu
 * @createTime 2022/9/22 16:03
 * @instruction
 */
public class JDBCUtils {

    private static String driver;

    private static String url;

    private static String user;

    private static String password;

    /**
     * 获取连接参数
     */
    static {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream(new File("src/com/wujm/jdbc/mysql.properties")));
            driver = properties.getProperty("driver");
            url = properties.getProperty("url");
            user = properties.getProperty("user");
            password = properties.getProperty("password");
        } catch (IOException e) {
//            e.printStackTrace();
            //1、将编译异常转成运行异常
            //2、这时调用者可以选择捕获异常，也可以选择默认处理该异常，比较方便
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取Connection连接
     * @return
     */
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
//            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 关闭连接
     * @param resultSet ResultSet
     * @param statement Statement
     * @param connection Connection
     */
    public static void close(ResultSet resultSet, Statement statement, Connection connection) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
//            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
