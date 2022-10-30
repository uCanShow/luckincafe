package com.wumx.jdbc.resultset;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * @author jmwu
 * @createTime 2022/9/21 13:27
 * @instruction
 */
public class ResultSetTest {

    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
        ResultSetTest rst = new ResultSetTest();
        rst.selectMyUser();
    }

    public void selectMyUser() throws IOException, ClassNotFoundException, SQLException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(new File("src/mysql.properties")));
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, user, password);
        String sql = "SELECT * FROM MY_USER";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        System.out.println(resultSet);
        resultSet.close();
        statement.close();
        connection.close();
    }

}
