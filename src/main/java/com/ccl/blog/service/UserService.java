package com.ccl.blog.service;

import com.ccl.blog.entity.User;

import java.util.List;

/**
 * @author CCL
 * @date 2019/9/3 13:32
 */

public interface UserService {

    /**
     * 根据主键删除数据
     *
     * @param userId
     * @return
     */
    int deleteByPrimaryKey(Integer userId);

    /**
     * 插入数据
     *
     * @param record
     * @return
     */
    int insert(User record);

    /**
     * 选择插入数据
     *
     * @param record
     * @return
     */
    int insertSelective(User record);

    /**
     * 根据主键挑选数据
     *
     * @param userId
     * @return
     */
    User selectByPrimaryKey(Integer userId);

    /**
     * 部分更新数据
     *
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(User record);

    /**
     * 根据主键更新数据
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(User record);

    /**
     * 根据账号查询数据
     * @param account
     * @return
     */
    User selectOneByAccount(String account);
}




