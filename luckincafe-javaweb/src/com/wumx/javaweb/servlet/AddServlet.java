package com.wumx.javaweb.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * author wjm
 * createTime 2022/10/7 23:00
 * instruction xxx
 */
@SuppressWarnings("all")
@WebServlet(urlPatterns = "/add.do", initParams = {@WebInitParam(name = "initKey", value = "initValue")})
public class AddServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        String price = request.getParameter("price");
        String count = request.getParameter("count");
        String remark = request.getParameter("remark");
        System.out.println(name + "\t" + price + "\t" + count + "\t" + remark);
    }

    /*public void add(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        String name = request.getParameter("name");
        String price = request.getParameter("price");
        String count = request.getParameter("count");
        String remark = request.getParameter("remark");
        System.out.println(name + "\t" + price + "\t" + count + "\t" + remark);
    }*/

    public void add(String name, Integer price, Integer count, String remark, HttpServletRequest request) {
        System.out.println(name + "\t" + price + "\t" + count + "\t" + remark);
    }

    @Override
    public void init() throws ServletException {
        ServletConfig servletConfig = getServletConfig();
        String initvalue = servletConfig.getInitParameter("initKey");
        System.out.println(initvalue);
    }
}
