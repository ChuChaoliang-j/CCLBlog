package com.ccl.blog.mapper;

import com.ccl.blog.entity.Blog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author CCL
 * @date 2019/9/12 15:19
 */

public interface BlogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Blog record);

    int insertSelective(Blog record);

    Blog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Blog record);

    int updateByPrimaryKey(Blog record);

    /**
     * 获取全部博客
     *
     * @return
     */
    List<Blog> findAllBlog();

    /**
     * 获取数据总数
     *
     * @return
     */
    Integer count();

    /**
     * 获取个人博客总数
     *
     * @param userId 用户的id
     * @return
     */
    Integer countByUserId(@Param("userId") Long userId);

    /**
     * 分页功能
     *
     * @param fistPage 起始页数
     * @param size     每页个数
     * @return
     */
    List<Blog> findAllBlogByPage(@Param(value = "fistPage") Integer fistPage, @Param(value = "size") Integer size);

    /**
     * 根据用户id挑选博客
     *
     * @param userId
     * @return
     */
    List<Blog> selectAllByUserId(@Param("userId") Long userId);

    /**
     * 分页个人博客中心
     *
     * @param id
     * @param fistPage
     * @param size
     * @return
     */
    List<Blog> findAllBlogByPageUser(@Param("id") Integer id, @Param(value = "fistPage") Integer fistPage, @Param(value = "size") Integer size);

    /**
     * 增加阅读数
     *
     * @param number 增加阅读数数量，加1
     * @param id     要增加的博客id
     * @return 变化的行数
     */
    Integer addBlogBrowse(@Param("number") Integer number, @Param("id") Integer id);

    /**
     * 增加点赞数
     * @param number
     * @param id
     * @return
     */
    Integer addBlogLike(@Param("number") Integer number, @Param("id") Integer id);

    /**
     * 根据标签查找相似的博客
     * @param tag
     * @return
     */
    List<Blog> findLikeBlog(@Param("tag") String tag, @Param("id") Integer id);

    String findOneLabelById(@Param("id")Integer id);


}