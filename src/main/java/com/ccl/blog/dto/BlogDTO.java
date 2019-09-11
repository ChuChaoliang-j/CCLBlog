package com.ccl.blog.dto;

import com.ccl.blog.entity.User;
import lombok.Data;

import java.util.Date;

/**
 * @author CCL
 * @date 2019/9/7 10:34
 */
@Data
public class BlogDTO {
    private Integer id;

    private String title;

    private String label;

    private Date time;

    private Long userId;

    private Long browse;

    private Long like;

    private String blogContent;

    private User user;
}
