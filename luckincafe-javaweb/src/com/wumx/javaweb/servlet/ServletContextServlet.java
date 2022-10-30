package com.wumx.javaweb.servlet;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

/**
 * author wjm
 * createTime 2022/10/27 0:08
 * instruction xxx
 */
public class ServletContextServlet extends GenericServlet {

    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        ServletContext servletContext = this.getServletContext();
        writer.print("<br>");
        writer.print(servletContext);
        writer.print("<br>");
        writer.print(servletContext.getInitParameter("key1"));
        Enumeration<String> initParameterNames = servletContext.getInitParameterNames();
        while (initParameterNames.hasMoreElements()) {
            String element = initParameterNames.nextElement();
            writer.print("<br>");
            writer.print(servletContext.getInitParameter(element));
        }
        writer.print("<br>");
        writer.print(servletContext.getContextPath());
        writer.print("<br>");
        writer.print(servletContext.getRealPath("index.html"));
        writer.print("<br>");
        writer.print(servletContext.getRealPath("common/login.html"));
        servletContext.log("记录一下");
    }

}
