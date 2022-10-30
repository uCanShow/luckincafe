package com.wumx.jdbc.conn;

/**
 * @author jmwu
 * @createTime 2022/9/20 13:17
 * @instruction
 */
public class JdbcTest {

    private JdbcInterface jdbcInterface1;

    private JdbcInterface jdbcInterface2;

    public JdbcTest() {
        this.jdbcInterface1 = new MysqlJdbcImpl();
        this.jdbcInterface2 = new OracleJdbcImpl();
    }

    public static void main(String[] args) {
        JdbcTest jdbcTest = new JdbcTest();
        jdbcTest.jdbcInterface1.getConnection();
        jdbcTest.jdbcInterface1.crud();
        jdbcTest.jdbcInterface1.close();
        jdbcTest.jdbcInterface2.getConnection();
        jdbcTest.jdbcInterface2.crud();
        jdbcTest.jdbcInterface2.close();
    }

    public JdbcInterface getJdbcInterface1() {
        return jdbcInterface1;
    }

    public void setJdbcInterface1(JdbcInterface jdbcInterface1) {
        this.jdbcInterface1 = jdbcInterface1;
    }

    public JdbcInterface getJdbcInterface2() {
        return jdbcInterface2;
    }

    public void setJdbcInterface2(JdbcInterface jdbcInterface2) {
        this.jdbcInterface2 = jdbcInterface2;
    }

}
