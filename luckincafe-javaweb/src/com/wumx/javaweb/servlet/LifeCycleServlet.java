package com.wumx.javaweb.servlet;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

/**
 * author wjm
 * createTime 2022/10/9 23:24
 * instruction xxx
 */
public class LifeCycleServlet extends HttpServlet {

    public LifeCycleServlet() {
        System.out.println("正在实例化。。。");
    }

    @Override
    public void init() throws ServletException {
        System.out.println("正在初始化。。。");
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        System.out.println("LifeCycleServlet正在服务。。。");
    }

    @Override
    public void destroy() {
        System.out.println("正在销毁。。。");
    }

}
