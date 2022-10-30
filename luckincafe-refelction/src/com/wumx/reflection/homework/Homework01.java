package com.wumx.reflection.homework;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author jmwu
 * @createTime 2022/9/19 14:56
 * @instruction
 */
public class Homework01 {

    public static void main(String[] args) throws NoSuchFieldException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        Homework01 hw = new Homework01();
        hw.practice();
    }

    public void practice() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        Class<?> cls = Class.forName("com.wumx.reflection.homework.PrivateTest");
        Object obj = cls.newInstance();
        Method method = cls.getMethod("getName");
        System.out.println(method.invoke(obj));
        Field field = cls.getDeclaredField("name");
        field.setAccessible(true);
        field.set(obj, "炭炭莓莓");
        System.out.println(method.invoke(obj));
    }

}

class PrivateTest {

    private String name = "hellokitty";

    public String getName() {
        return this.name;
    }

}
