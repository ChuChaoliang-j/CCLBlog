package com.ccl.blog.service;

import com.ccl.blog.entity.User;

/**
 * @author CCL
 * @date 2019/9/3 13:12
 */
public interface LoginService {
    /**
     * 验证登录 ,验证成功true 失败false
     *
     *      * 账号检测
     *      * 1.通过账号查询数据库，对比密码是否正确(正确返回true，错误返回false)
     *      * 2.如果密码正确，跳转到主页
     *      * 3.如果密码不正确，跳转到登陆页面，提示账号或密码输入错误
     *      * 4.密码登陆正确后，还需继续检测是否账号处于封禁状态
     *      * 注意：
     *      * 1.场景一：输入的账号不存在
     *      * 2.场景二：未输入账号
     *      * 3.场景三：账号存在密码不正确
     *      * 4.场景四：账号密码正确--success
     *
     * @param user
     * @return
     */
    boolean verifyLogin(User user);

    /**
     * 生成用户账号
     * @return
     */
    String generateAccount();

    /**
     * 获取登录错误信息
     * 1.请输入账号
     * 2.请输入密码
     * 3.账号不能存在
     * 4.密码错误
     * @param user
     * @return
     */
    String getLoginErrorMsg(User user);

    /**
     * 检测注册的时候是否时全部输入（name,password,email,gender）
     * 如果全部输入返回true否则返回false，再次跳转到登陆页面，提示！
     * @param user
     * @return
     */
    Boolean verifyRegister(User user);
}
