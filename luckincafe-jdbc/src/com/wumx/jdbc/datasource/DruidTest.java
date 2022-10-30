package com.wumx.jdbc.datasource;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.wumx.jdbc.utils.DruidUtils;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Properties;

/**
 * @author jmwu
 * @createTime 2022/9/28 13:23
 * @instruction
 */
public class DruidTest {

    public static void main(String[] args) throws Exception {
        DruidTest dt = new DruidTest();
        Calendar cal = Calendar.getInstance();
        System.out.println(cal.getTimeInMillis());
        System.out.println(cal.getTime().getTime());
//        dt.test0();
//        dt.test1();
    }

    public void test0() throws Exception {
        Properties prop = new Properties();
        prop.load(new FileInputStream("src/druid.properties"));
        DataSource dds = DruidDataSourceFactory.createDataSource(prop);
        System.out.println(dds.getClass());
        Connection conn = dds.getConnection();
        String sql = "SELECT * FROM MY_USER A WHERE A.USER_ID = ?";
        PreparedStatement ppSt = conn.prepareStatement(sql);
        ppSt.setInt(1, 1);
        ResultSet rst = ppSt.executeQuery();
        while (rst.next()) {
            int userId = rst.getInt("USER_ID");
            String userCode = rst.getString("USER_CODE");
            String userName = rst.getString("USER_NAME");
            Short userAge = rst.getShort("USER_AGE");
            String userTel = rst.getString("USER_TEL");
            String userEmail = rst.getString("USER_EMAIL");
            System.out.println(userId + "\t" + userCode + "\t" + userName + "\t" + userAge + "\t" + userTel + "\t" + userEmail);
        }
        rst.close();
        ppSt.close();
        conn.close();
    }

    public void test1() throws Exception {
        Connection conn = DruidUtils.getConnection();
        String sql = "SELECT * FROM MY_USER A WHERE A.USER_ID = ?";
        PreparedStatement ppSt = conn.prepareStatement(sql);
        ppSt.setInt(1, 2);
        ResultSet rst = ppSt.executeQuery();
        while (rst.next()) {
            int userId = rst.getInt("USER_ID");
            String userCode = rst.getString("USER_CODE");
            String userName = rst.getString("USER_NAME");
            Short userAge = rst.getShort("USER_AGE");
            String userTel = rst.getString("USER_TEL");
            String userEmail = rst.getString("USER_EMAIL");
            System.out.println(userId + "\t" + userCode + "\t" + userName + "\t" + userAge + "\t" + userTel + "\t" + userEmail);
        }
        DruidUtils.close(rst, ppSt, conn);
    }

}
