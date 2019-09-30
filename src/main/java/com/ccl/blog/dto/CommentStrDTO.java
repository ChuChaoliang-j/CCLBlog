package com.ccl.blog.dto;

import com.ccl.blog.entity.User;
import lombok.Data;

import java.util.Date;

/**
 * @author CCL
 * @date 2019/9/16 19:13
 */
@Data
public class CommentStrDTO {
    private Integer id;

    private Integer blogId;

    private String content;

    private Integer type;

    private Date createTime;

    private Date updateTime;

    private Integer userId;

    private Integer like;

    private Integer parentId;

    private User user;

    private String strCreateTime;
}
