package com.ccl.blog.mapper;

import com.ccl.blog.entity.Comment;import org.apache.ibatis.annotations.Param;import java.util.List;

/**
 * @author CCL
 * @date 2019/9/21 18:19
 */

public interface CommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);

    Integer countByBlogId(@Param("blogId") Integer blogId);

    List<Comment> findAllByBlogId(@Param("blogId") Integer blogId);

    List<Integer> findOneUserIdByBlogId(@Param("blogId") Integer blogId);



}