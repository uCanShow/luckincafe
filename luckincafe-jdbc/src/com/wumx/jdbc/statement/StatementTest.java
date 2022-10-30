package com.wumx.jdbc.statement;

import java.io.File;
import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @author jmwu
 * @createTime 2022/9/22 12:48
 * @instruction
 */
public class StatementTest {

    public static void main(String[] args) throws Exception {
        StatementTest st = new StatementTest();
//        st.test01();
//        st.test02();
    }

    public void test01() throws Exception {
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
        while (resultSet.next()) {
            int id = resultSet.getByte(1);
            String name = resultSet.getString(3);
            System.out.println(id + "\t" + name);
        }
        resultSet.close();
        statement.close();
        connection.close();
    }

    public void test02() throws Exception {
        Properties properties = new Properties();
        properties.load(new FileInputStream(new File("src/mysql.properties")));
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, user, password);
        String sql = "SELECT * FROM MY_USER WHERE USER_ID = ? AND USER_NAME = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, 1);
        preparedStatement.setString(2, "admin");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getByte(1);
            String name = resultSet.getString(3);
            System.out.println(id + "\t" + name);
        }
//        String updateSql = "INSERT INTO MY_USER (USER_ID, USER_CODE, USER_NAME) VALUE (?, ?, ?)";
//        PreparedStatement preparedStatement1 = connection.prepareStatement(updateSql);
//        preparedStatement1.setInt(1, 3);
//        preparedStatement1.setInt(2, 3);
//        preparedStatement1.setString(3, "jdbc");
//        int i = preparedStatement1.executeUpdate();
//        System.out.println(i);
        String dltSql = "DELETE FROM MY_USER WHERE USER_ID = ?";
        PreparedStatement preparedStatement2 = connection.prepareStatement(dltSql);
        preparedStatement2.setInt(1, 3);
        int dltNum = preparedStatement2.executeUpdate();
        System.out.println(dltNum);
        resultSet.close();
        preparedStatement.close();
        preparedStatement2.close();
        connection.close();
    }

}
