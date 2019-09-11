package com.ccl.blog.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author  CCL
 * @date  2019/9/4 15:35
 */
@Data
public class Blog {
    private Integer id;

    private String title;

    private String label;

    private Date time;

    private Long userId;

    private Long browse;

    private Long like;

    private String blogContent;
}