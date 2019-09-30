package com.ccl.blog.dto;

import lombok.Data;

/**
 * @author CCL
 * @date 2019/9/14 12:16
 */
@Data
public class CommentDTO {
    private Integer blogId;
    private String content;
    private Integer type;
}
