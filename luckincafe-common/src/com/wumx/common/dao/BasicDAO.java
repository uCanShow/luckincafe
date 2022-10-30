package com.wumx.common.dao;

import com.wumx.jdbc.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author jmwu
 * @createTime 2022/10/14 15:35
 * @instruction
 */
public class BasicDAO<T> {

    private QueryRunner qr = new QueryRunner();

    public int update(String sql, Object... params) {
        Connection conn = null;
        try {
            conn = DruidUtils.getConnection();
            return qr.update(conn, sql, params);
        } catch (SQLException e) {
//            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            DruidUtils.close(null, null, conn);
        }
    }

    public List<T> queryList(String sql, Class<T> clazz, Object... params) {
        Connection conn = null;
        try {
            conn = DruidUtils.getConnection();
            return qr.query(conn, sql, new BeanListHandler<>(clazz), params);
        } catch (SQLException e) {
//            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            DruidUtils.close(null, null, conn);
        }
    }

    public T querySingle(String sql, Class<T> clazz, Object... params) {
        Connection conn = null;
        try {
            conn = DruidUtils.getConnection();
            return qr.query(conn, sql, new BeanHandler<>(clazz), params);
        } catch (SQLException e) {
//            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            DruidUtils.close(null, null, conn);
        }
    }

    public Object queryScaler(String sql, Object... params) {
        Connection conn = null;
        try {
            conn = DruidUtils.getConnection();
            return qr.query(conn, sql, new ScalarHandler<>(), params);
        } catch (SQLException e) {
//            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            DruidUtils.close(null, null, conn);
        }
    }

}
