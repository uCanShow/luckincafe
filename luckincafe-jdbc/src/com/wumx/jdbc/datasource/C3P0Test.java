package com.wumx.jdbc.datasource;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.wumx.jdbc.utils.C3P0Utils;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.util.Properties;

/**
 * @author jmwu
 * @createTime 2022/9/26 08:30
 * @instruction
 */
@SuppressWarnings("all")
public class C3P0Test {

    public static void main(String[] args) throws Exception {
        C3P0Test cp = new C3P0Test();
//        cp.test0();
//        cp.test1();
        cp.test2();
    }

    public void test0() throws Exception {
        Properties prop = new Properties();
        prop.load(new FileInputStream(new File("src/mysql.properties")));
        String driver = prop.getProperty("driver");
        String url = prop.getProperty("url");
        String user = prop.getProperty("user");
        String password = prop.getProperty("password");

        ComboPooledDataSource ds = new ComboPooledDataSource();
        ds.setDriverClass(driver);
        ds.setJdbcUrl(url);
        ds.setUser(user);
        ds.setPassword(password);
        ds.setInitialPoolSize(10);
        ds.setAcquireIncrement(5);
        ds.setMaxIdleTime(5);
        ds.setMinPoolSize(5);
        ds.setMaxPoolSize(50);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 50000; i++) {
            Connection conn = ds.getConnection();
            conn.close();
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    public void test1() throws Exception {
        ComboPooledDataSource cpds = new ComboPooledDataSource("c3p0");
        long start = System.currentTimeMillis();
        for (int i = 0; i < 50000; i++) {
            Connection conn = cpds.getConnection();
            conn.close();
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    public void test2() {
        Connection conn = C3P0Utils.getConnection();
        System.out.println(conn);
        C3P0Utils.close(null, null, conn);
        System.out.println(conn);
    }

}
