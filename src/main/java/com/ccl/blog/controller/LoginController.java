package com.ccl.blog.controller;

import com.ccl.blog.entity.User;
import com.ccl.blog.service.LoginService;
import com.ccl.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * @author CCL
 * @date 2019/9/3 9:50
 */
@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private UserService userService;

    /**
     * 来到登陆页面
     *
     * @return
     */
    @GetMapping("/login")
    public String login() {
        return "login/login";
    }

    /**
     * 来到注册页面
     *
     * @return
     */
    @GetMapping("/register")
    public String register() {
        return "login/register";
    }

    /**
     * 检测登陆
     * 1.登陆成功跳转到主页面 查询user并将其保存到session中
     * 2.登陆失败跳转到登陆页面
     *
     * @param user
     * @param model
     * @param request
     * @return
     */
    @PostMapping("/login")
    public String verifyLogin(User user, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        //删除注册账号时转发存入的user；
        if (session.getAttribute("u") != null) {
            session.removeAttribute("u");
        }
        boolean bLogin = loginService.verifyLogin(user);
        if (bLogin) {
            User u = userService.selectOneByAccount(user.getAccount());
            session.setAttribute("user", u);
            return "redirect:/";
        } else {
            String message = loginService.getLoginErrorMsg(user);
            model.addAttribute("msg", message);
            return "login/login";
        }
    }

    /**
     * 退出登陆 删除session中的user
     *
     * @return
     */
    @GetMapping("/quitLogin")
    public String quitLogin(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        return "redirect:/";
    }

    @GetMapping("/registerSuccess")
    public String registerSuccess() {
        return "login/registerSuccess";
    }

    /**
     * 实现注册功能
     * 1.生成5位数随机数账号（不能是0开头）
     * 2.获取到注册的信息，一同保存到数据库中
     * 3.跳转到注册成功页面，并提示用户注册成功
     * 4.登陆
     * 注意：#利用重定向到注册成功页面防止表单重复提交，导致账号重复注册！！！
     * #防止用户未输入全部数据就提交。
     * 有问题
     *
     * @return
     */
    @PostMapping("/register")
    public String realizeRegister(User user, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Boolean resBoolean = loginService.verifyRegister(user);
        if (resBoolean) {
            //生成账号
            String account = loginService.generateAccount();
            //设置账号
            user.setAccount(account);
            //设置账号的状态0封号，1正常 目前未添加这个功能
            user.setStatus("1");
            //将数据保存到数据库
            userService.insertSelective(user);
            //将user存放到session
            session.setAttribute("u", user);
            return "redirect:registerSuccess";
        } else {
            String regMsg = "请输入全部信息！！";
            HashMap<String, Object> regHashMap = new HashMap<>();
            regHashMap.put("reg_msg", regMsg);
            regHashMap.put("name", user.getName());
            regHashMap.put("password", user.getPassword());
            regHashMap.put("email", user.getEmail());
            regHashMap.put("gender", user.getGender());
            model.addAllAttributes(regHashMap);
            return "login/register";
        }
    }
}
