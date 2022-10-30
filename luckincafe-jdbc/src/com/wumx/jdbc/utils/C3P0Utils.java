package com.wumx.jdbc.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author jmwu
 * @createTime 2022/9/28 13:12
 * @instruction
 */
public class C3P0Utils {

    private static ComboPooledDataSource cpds = new ComboPooledDataSource("c3p0");

    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = cpds.getConnection();
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
