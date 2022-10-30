package com.wumx.reflection.study;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * @author jmwu
 * @createTime 2022/9/9 13:27
 * @instruction
 */
public class Reflection {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Reflection ref = new Reflection();
//        ref.test1();
        ref.test2();
//        ref.test3();
    }

    public void test1() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {//传统方式
//        Cat cat = new Cat();
//        cat.sayHi();

        //利用反射
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/com/wujm/reflection.properties"));
        String classFullPath = properties.getProperty("classFullPath");
        String methodName = properties.getProperty("methodName");
        System.out.println("类的全路径：" + classFullPath);
        System.out.println("方法的全路径：" + methodName);

        Class cls = Class.forName(classFullPath);
        Object object = cls.newInstance();
        Method method = cls.getMethod("miaoMiao");
        method.invoke(object);
    }

    public void test2() throws InstantiationException, IllegalAccessException, NoSuchMethodException {
        Class cls = Cat.class;
        Object obj = cls.newInstance();
        Method mtd = cls.getMethod("sayHi");
        Class<?> mCls = mtd.getDeclaringClass();
        System.out.println(mCls.getName());
    }

    public void test3() {
        String a = "test";
        String b = "test";
        String c = "tes" + "t";
        System.out.println(a.hashCode());
        System.out.println(b.hashCode());
        System.out.println(c.hashCode());
    }

}
