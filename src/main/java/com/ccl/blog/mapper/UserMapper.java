package com.ccl.blog.mapper;
import com.ccl.blog.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author CCL
 * @date 2019/9/3 13:47
 */
@Repository
public interface UserMapper {
    /**
     * 根据主键删除
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入数据
     * @param record
     * @return
     */
    int insert(User record);

    /**
     * 部分插入数据
     * @param record
     * @return
     */
    int insertSelective(User record);

    /**
     * 根据主键获得数据
     * @param id
     * @return
     */
    User selectByPrimaryKey(Integer id);

    /**
     * 根据主键部分更新数据
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(User record);

    /**
     * 更新全部数据
     * @param record
     * @return
     */
    int updateByPrimaryKey(User record);

    /**
     * 根据账号查询用户信息
     * @param account
     * @return
     */
    User selectOneByAccount(@Param("account")String account);

    /**
     * 获取全部账号
     * @return
     */
    List<String> findAccount();

    /**
     * 获取全部数据
     * @return
     */
    List<User> findAllUser();
}