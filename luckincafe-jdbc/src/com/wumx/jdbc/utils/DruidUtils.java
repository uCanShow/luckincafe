package com.wumx.jdbc.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author jmwu
 * @createTime 2022/9/28 13:23
 * @instruction
 */
public class DruidUtils {

    private static DataSource ds;

    static {
        try {
            Properties prop = new Properties();
            prop.load(new FileInputStream("lukincafe-jdbc/src/druid.properties"));
            ds = DruidDataSourceFactory.createDataSource(prop);
        } catch (IOException e) {
//            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (Exception e) {
//            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() {
        Connection conn;
        try {
            conn = ds.getConnection();
        } catch (SQLException e) {
//            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return conn;
    }

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
