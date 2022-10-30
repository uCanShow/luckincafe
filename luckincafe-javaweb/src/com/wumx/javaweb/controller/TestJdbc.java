package com.wumx.javaweb.controller;

import com.wumx.common.dao.MyUserDAO;
import com.wumx.common.domain.MyUser;

import java.util.List;

/**
 * @author jmwu
 * @createTime 2022/10/14 14:15
 * @instruction
 */
public class TestJdbc {

    public static void main(String[] args){
        MyUserDAO myUserDAO = new MyUserDAO();
        String sql = "SELECT * FROM MY_USER A WHERE A.USER_ID IN (?, ?)";
        List<MyUser> myUserList = myUserDAO.queryList(sql, MyUser.class, 1, 2);
        for (MyUser myUser : myUserList) {
            System.out.println(myUser);
        }
    }

}
