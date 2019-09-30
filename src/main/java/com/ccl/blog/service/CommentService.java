package com.ccl.blog.service;

import com.ccl.blog.dto.CommentStrDTO;
import com.ccl.blog.entity.Comment;

import java.util.List;

/**
 * @author CCL
 * @date 2019/9/14 12:01
 */

public interface CommentService {


    int deleteByPrimaryKey(Integer commentId);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Integer commentId);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);

    /**
     * 博客 id
     * 1.根据博客id 通过查询可以得到此博客的全部评论数据
     * 2.根据评论数据的信息中的用户id 查询该用户的信息，并保存在CommentStrDTO.user中
     * 3.将信息返回前端展示
     *
     * @param id
     * @return
     */
    List<CommentStrDTO> findAllComment(Integer id);
}





