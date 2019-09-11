package com.ccl.blog.controller;

import com.ccl.blog.dto.PageBlogDTO;
import com.ccl.blog.entity.User;
import com.ccl.blog.mapper.BlogMapper;
import com.ccl.blog.service.BlogService;
import com.ccl.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

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
    private BlogMapper blogMapper;
    @Autowired
    private BlogService blogService;
    @Autowired
    private UserService userService;

    /**
     * 1.从session中取出user用户
     * 2.在数据库中查找改用户的所有博客
     *
     * @param request
     * @param model
     * @return
     */
    @GetMapping("/user/{id}")
    public String goUserBlog(HttpServletRequest request, Model model,
                             @RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "5") Integer size,
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
}
