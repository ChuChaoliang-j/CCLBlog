package com.ccl.blog.service.impl;

import com.ccl.blog.entity.User;
import com.ccl.blog.mapper.UserMapper;
import com.ccl.blog.service.LoginService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;

/**
 * @author CCL
 * @date 2019/9/3 13:12
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private UserMapper userMapper;

    @Override
    public boolean verifyLogin(User user) {
        List<String> accounts = userMapper.findAccount();
        String account = user.getAccount();
        if (!accounts.contains(account)) {
            return false;
        } else {
            User u = userMapper.selectOneByAccount(user.getAccount());
            if (user.getPassword().equals(u.getPassword())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String generateAccount() {
        String account = "";
        Random random = new Random();
        int amount = 20;
        for (int i = 0; i < amount; i++) {
            int number = random.nextInt(10);
            if (number == 0 || account.length() >= 5) {
                continue;
            } else {
                account += number;
            }
        }
        return account;
    }

    @Override
    public String getLoginErrorMsg(User user) {
        List<String> accounts = userMapper.findAccount();
        User u = userMapper.selectOneByAccount(user.getAccount());
        //请输入账号
        if ("".equals(user.getAccount())) {
            return "请输入账号！";
        }
        if ("".equals(user.getPassword())) {
            return "请输入密码！";
        }
        if (!accounts.contains(user.getAccount())) {
            return "账号不存在，请重新输入！";
        }
        if (!user.getPassword().equals(u.getPassword())) {
            return "密码不正确！";
        }
        return "";
    }

    /**
     * 在此处还可以检验账号密码邮箱的格式
     * 姓名：***
     * 密码：不超过16位
     * 邮箱：*****@****
     * 性别：
     *
     * @param user
     * @return
     */
    @Override
    public Boolean verifyRegister(User user) {
        if (!"".equals(user.getName()) && !"".equals(user.getPassword())
                && !"".equals(user.getEmail()) && !"".equals(user.getGender())) {
            return true;
        }
        return false;
    }
}
