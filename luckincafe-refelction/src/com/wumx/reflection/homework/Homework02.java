package com.wumx.reflection.homework;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author jmwu
 * @createTime 2022/9/19 15:05
 * @instruction
 */
public class Homework02 {

    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Homework02 hw = new Homework02();
        hw.practice();
    }

    public void practice() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> cls = Class.forName("java.io.File");
        Constructor<?>[] constructors = cls.getDeclaredConstructors();
        for (Constructor<?> constructor : constructors) {
            System.out.println(constructor.getName());
        }
        Constructor<?> useConstructor = cls.getDeclaredConstructor(String.class);
        Object obj = useConstructor.newInstance("/Users/jmwu/myNew.txt");
        Method method = cls.getMethod("createNewFile");
        method.invoke(obj);
    }

}
