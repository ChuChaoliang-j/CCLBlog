package com.ccl.blog.mapper;

import com.ccl.blog.entity.Blog;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author CCL
 * @date 2019/9/4 15:35
 */
@Repository
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
     * @return
     * @param userId 用户的id
     */
    Integer countByUserId(@Param("userId")Long userId);

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
     * @param id
     * @param fistPage
     * @param size
     * @return
     */
    List<Blog> findAllBlogByPageUser(@Param("id") Integer id, @Param(value = "fistPage") Integer fistPage, @Param(value = "size") Integer size);
}