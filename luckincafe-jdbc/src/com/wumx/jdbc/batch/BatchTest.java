package com.wumx.jdbc.batch;

import com.wumx.jdbc.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

/**
 * @author jmwu
 * @createTime 2022/9/23 13:02
 * @instruction
 */
@SuppressWarnings("all")
public class BatchTest {

    public static void main(String[] args) throws Exception {
        BatchTest bt = new BatchTest();
//        bt.noBatch();
        bt.batch();
    }

    public void noBatch() throws Exception {
        Connection connection = JDBCUtils.getConnection();
        String sql = "INSERT INTO MY_USER VALUES (null, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 5000; i++) {
            preparedStatement.setString(1, "0" + (i + 3));
            preparedStatement.setString(2, "普通用户" + "0" + (i + 3));
            preparedStatement.setInt(3, 30);
            preparedStatement.setString(4, "1xxxxxxxxxx");
            preparedStatement.setString(5, "xxx...@xxx.com");
            preparedStatement.setInt(6, 1);
            preparedStatement.setString(7, "admin");
            preparedStatement.setDate(8, new Date(2022, 9, 23));
            preparedStatement.executeUpdate();
        }
        long end = System.currentTimeMillis();
        System.out.println("传统方式耗时：" + (end - start) + " ms");
        JDBCUtils.close(null, preparedStatement, connection);
    }

    public void batch() throws Exception {
        Connection connection = JDBCUtils.getConnection();
        String sql = "INSERT INTO MY_USER VALUES (null, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 5000; i++) {
            preparedStatement.setString(1, "0" + (i + 3));
            preparedStatement.setString(2, "普通用户" + "0" + (i + 3));
            preparedStatement.setInt(3, 30);
            preparedStatement.setString(4, "1xxxxxxxxxx");
            preparedStatement.setString(5, "xxx...@xxx.com");
            preparedStatement.setInt(6, 1);
            preparedStatement.setString(7, "admin");
            preparedStatement.setDate(8, new Date(2022, 9, 23));
            preparedStatement.addBatch();
            if ((i + 1) % 1000 ==  0) {
                preparedStatement.executeBatch();
                preparedStatement.clearBatch();
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("批处理方式耗时：" + (end - start) + " ms");
        JDBCUtils.close(null, preparedStatement, connection);
    }

}
