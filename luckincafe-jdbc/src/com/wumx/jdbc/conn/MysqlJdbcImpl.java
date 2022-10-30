package com.wumx.jdbc.conn;

/**
 * @author jmwu
 * @createTime 2022/9/20 13:13
 * @instruction
 */
public class MysqlJdbcImpl implements JdbcInterface {

    @Override
    public Object getConnection() {
        System.out.println("与mysql数据库建立连接");
        return null;
    }

    @Override
    public void crud() {
        System.out.println("操作完成");
    }

    @Override
    public void close() {
        System.out.println("断开与mysql的连接");
    }

}
