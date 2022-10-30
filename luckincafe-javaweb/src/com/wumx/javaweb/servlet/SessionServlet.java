package com.wumx.javaweb.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * author wjm
 * createTime 2022/10/10 22:48
 * instruction xxx
 */
public class SessionServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.getRequestDispatcher("lifeCycle").forward(req, resp);
        resp.sendRedirect("lifeCycle");
        System.out.println("SessionServlet正在服务");
    }
}
