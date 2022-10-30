package com.wumx.jdbc.conn;

/**
 * @author jmwu
 * @createTime 2022/9/20 13:12
 * @instruction
 */
public interface JdbcInterface {

    Object getConnection();

    void crud();

    void close();

}
