package com.wumx.javaweb.servlet;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

/**
 * author wjm
 * createTime 2022/10/12 23:08
 * instruction xxx
 */
//@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {

    private Map<String, Object> beanMap = new HashMap<>();

    public DispatcherServlet() {
        try {
            InputStream resource = this.getClass().getClassLoader().getResourceAsStream("applicationContext.xml");
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(resource);
            NodeList nodeList = document.getElementsByTagName("bean");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String beanId = element.getAttribute("id");
                    String className = element.getAttribute("class");
                    Object obj = Class.forName(className).newInstance();
                    beanMap.put(beanId, obj);
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String servletPath = request.getServletPath();
        System.out.println(servletPath);
        servletPath = servletPath.substring(1);
        int lastDoIndex = servletPath.lastIndexOf(".do");
        servletPath = servletPath.substring(0, lastDoIndex);

        String operate = request.getParameter("operate");
        if ("".equals(operate)) {
            operate = "index";
        }

        Object obj = beanMap.get(servletPath);
        try {
            Method[] methods = obj.getClass().getDeclaredMethods();
            for (Method method : methods) {
                if (operate.equals(method.getName())) {
                    Parameter[] parameters = method.getParameters();
                    Object[] paramsValues = new Object[parameters.length];
                    for (int i = 0; i < parameters.length; i++) {
                        Parameter parameter = parameters[i];
                        String paramName = parameter.getName();
                        if ("request".equals(paramName)) {
                            paramsValues[i] = request;
                        } else if ("response".equals(paramName)) {
                            paramsValues[i] = response;
                        } else if ("session".equals(paramName)) {
                            paramsValues[i] = request.getSession();
                        } else {
                            String paramsValue =  request.getParameter(paramName);
                            Object object = null;
                            if (paramsValue != null) {
                                if ("java.lang.Integer".equals(parameter.getType().getName())) {
                                    object = Integer.parseInt(paramsValue);
                                } else {
                                    object = paramsValue;
                                }
                            }
                            paramsValues[i] = object;
                        }
                    }
                    method.setAccessible(true);
                    Object resultObj = method.invoke(obj, paramsValues);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
