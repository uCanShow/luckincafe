package com.wumx.javaweb.servlet;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

/**
 * author wjm
 * createTime 2022/10/26 23:20
 * instruction xxx
 */
public class ServletConfigServlet extends GenericServlet {

    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        ServletConfig servletConfig = this.getServletConfig();
        writer.print(servletConfig);
        writer.print("<br>");
        writer.print(servletConfig.getServletName());
        writer.print("<br>");
        writer.print(servletConfig.getInitParameter("driver"));
        Enumeration<String> initParameterNames = servletConfig.getInitParameterNames();
        while (initParameterNames.hasMoreElements()) {
            String element = initParameterNames.nextElement();
            writer.print("<br>");
            writer.print(servletConfig.getInitParameter(element));
        }
        ServletContext servletContext = servletConfig.getServletContext();
        writer.print("<br>");
        writer.print(servletContext);
    }

}
