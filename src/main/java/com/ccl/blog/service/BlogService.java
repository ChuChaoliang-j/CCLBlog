package com.ccl.blog.service;

import com.ccl.blog.dto.PageBlogDTO;
import com.ccl.blog.entity.Blog;

import javax.servlet.http.HttpServletRequest;

/**
 * @author CCL
 * @date 2019/9/4 15:35
 */

public interface BlogService {


    int deleteByPrimaryKey(Integer id);

    int insert(Blog record);

    int insertSelective(Blog record);

    Blog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Blog record);

    int updateByPrimaryKey(Blog record);

    /**
     * 获取用户的id
     *
     * @param request
     * @return
     */
    int acquireUserId(HttpServletRequest request);

    /**
     * 检测写博客是否为空
     *
     * @param title
     * @param content
     * @param label
     * @return
     */
    String detectionBlogDateIsNull(String title, String content, String label);

    /**
     * 多表查询，将blog表和user表关联起来
     * blog -> user 多对一关系
     *
     * @param page
     * @param size
     * @return
     */
    PageBlogDTO findAllBlogDTO(Integer page, Integer size);

    /**
     * 查询分页
     * 个人博客文章查询
     * @param id 用户id
     * @param page 起始页
     * @param size 每页个数
     * @return
     */
    PageBlogDTO findAllBlogDTO(Integer id, Integer page, Integer size);
}
