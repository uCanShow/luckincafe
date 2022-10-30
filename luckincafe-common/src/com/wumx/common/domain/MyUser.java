package com.wumx.common.domain;

import lombok.Data;

import java.util.Date;

/**
 * @author jmwu
 * @createTime 2022/10/14 15:10
 * @instruction
 */
@Data
public class MyUser {

    private Integer USER_ID;

    private String USER_CODE;

    private String USER_NAME;

    private Short USER_AGE;

    private String USER_TEL;

    private String USER_EMAIL;

    private Integer CREATE_ID;

    private String CREATE_NAME;

//    private Date CREATE_DATE;

}
