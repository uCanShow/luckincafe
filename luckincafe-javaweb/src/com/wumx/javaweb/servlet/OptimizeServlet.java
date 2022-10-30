package com.wumx.javaweb.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * author wjm
 * createTime 2022/10/12 0:09
 * instruction xxx
 */
@WebServlet(value = "/optimize")
public class OptimizeServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String operateType = req.getParameter("operateType");
        if ("".equals(operateType)) {
            operateType = "index";
        }

        Method[] methods = this.getClass().getDeclaredMethods();
        for (Method method : methods) {
            String methodName = method.getName();
            if (methodName.equals(operateType)) {
                try {
                    method.invoke(this, req, resp);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }

        switch (operateType) {
            case "add":
                add(req, resp);
                break;
            default:
                throw new RuntimeException();
        }

    }

    private void add(HttpServletRequest req, HttpServletResponse resp) {

    }
}
