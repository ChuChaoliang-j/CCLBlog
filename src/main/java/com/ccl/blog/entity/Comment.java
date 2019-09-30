package com.ccl.blog.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author CCL
 * @date 2019/9/21 18:19
 */
@Data
public class Comment {
    private Integer id;

    private Integer blogId;

    private String content;

    private Integer type;

    private Date createTime;

    private Date updateTime;

    private Integer userId;

    private Integer like;

    private Integer parentId;

    private String strCreateTime;
}