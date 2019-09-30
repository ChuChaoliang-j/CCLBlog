package com.ccl.blog.controller;

import com.ccl.blog.dto.PageBlogDTO;
import com.ccl.blog.entity.ResponseEntity;
import com.ccl.blog.entity.User;
import com.ccl.blog.enumerate.ResponseCode;
import com.ccl.blog.mapper.UserMapper;
import com.ccl.blog.service.BlogService;
import com.ccl.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * @author CCL
 * @date 2019/9/8 10:28
 * 个人博客
 */
@Controller
public class UserBlogController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;

    /**
     * 1.从session中取出user用户
     * 2.在数据库中查找改用户的所有博客
     * 3.有问题！！！！！！！！！！！！！！！！！！！！！！！
     *
     * @param request
     * @param model
     * @return
     */
    @GetMapping("/user/{id}")
    public String goUserBlog(HttpServletRequest request, Model model,
                             @RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size,
                             @PathVariable(value = "id") Integer id) {
        HttpSession session = request.getSession();
        session.setAttribute("user_id", id);
        User user = userService.selectByPrimaryKey(id);
        PageBlogDTO pageBlogDTO = blogService.findAllBlogDTO(user.getId(), page, size);
        HashMap<String, Object> blogMap = new HashMap<>(10);
        blogMap.put("page", pageBlogDTO);
        blogMap.put("user", user);
        model.addAllAttributes(blogMap);
        return "user/user";
    }

    @GetMapping("/user")
    public String userBlog(HttpServletRequest request, Model model,
                           @RequestParam(value = "page", defaultValue = "1") Integer page,
                           @RequestParam(value = "size", defaultValue = "10") Integer size) {
        User user = (User) request.getSession().getAttribute("user");
        PageBlogDTO blogs = blogService.findAllBlogDTO(user.getId(), page, size);
        HashMap<String, Object> blogMap = new HashMap<>(10);
        blogMap.put("page", blogs);
        blogMap.put("user", user);
        model.addAllAttributes(blogMap);
        return "user/user";
    }

    /**
     * 删除博客 根据id删除博客 删除后放入回收站,删除博客
     * 回收站后续加入
     *
     * @return
     */
    @DeleteMapping("/deleteBlog/{id}")
    public String deleteBlog(@PathVariable(value = "id") Integer id) {
        blogService.deleteByPrimaryKey(id);
        return "redirect:/user";
    }

    /**
     * 来到个人资料展示页面
     *
     * @return
     */
    @GetMapping("/userData")
    public String userData(HttpServletRequest request, Model model) {
        User u = (User) request.getSession().getAttribute("user");
        User user = userMapper.selectByPrimaryKey(u.getId());
        model.addAttribute("user", user);
        return "user/userData";
    }

    /**
     * 更新个人资料
     * 1.获取到个人资料
     * 2.解析成为User对象
     * 3.数据库更新
     * @return
     */
    @ResponseBody
    @PostMapping("/userData")
    public Object updateData(@RequestBody User user, HttpServletRequest request, Model model) {
        userMapper.updateByPrimaryKeySelective(user);
        User getUser = (User)request.getSession().getAttribute("user");
        User sUser = userMapper.selectByPrimaryKey(getUser.getId());
        model.addAttribute("user", user);
        return new ResponseEntity(ResponseCode.SUCCESS, user);
    }
}
