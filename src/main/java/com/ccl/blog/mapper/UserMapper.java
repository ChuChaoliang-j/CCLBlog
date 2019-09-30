package com.ccl.blog.mapper;

import com.ccl.blog.entity.User;import org.apache.ibatis.annotations.Param;import java.util.Collection;import java.util.List;

/**
 * @author CCL
 * @date 2019/9/19 13:20
 */

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * 根据账号查询用户信息
     *
     * @param account
     * @return
     */
    User selectOneByAccount(@Param("account") String account);

    /**
     * 获取全部账号
     *
     * @return
     */
    List<String> findAccount();

    /**
     * 获取全部数据
     *
     * @return
     */
    List<User> findAllUser();

    /**
     *
     * @param idCollection
     * @return
     */
    List<User> findAllByIdInOrderById(@Param("idCollection") Collection<Integer> idCollection);
}