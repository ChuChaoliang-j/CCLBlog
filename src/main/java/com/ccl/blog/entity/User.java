package com.ccl.blog.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author CCL
 * @date 2019/9/19 13:20
 */
@Data
public class User {
    private Integer id;

    private String name;

    private String account;

    private String password;

    private Date date;

    private String status;

    private String email;

    private String gender;

    private Date birthday;

    private String region;

    private String intro;

    private String vocation;

    private String nickname;

    private String position;
}