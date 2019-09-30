package com.ccl.blog.controller;

import com.ccl.blog.dto.BlogDTO;
import com.ccl.blog.dto.CommentStrDTO;
import com.ccl.blog.entity.Blog;
import com.ccl.blog.entity.User;
import com.ccl.blog.mapper.BlogMapper;
import com.ccl.blog.mapper.CommentMapper;
import com.ccl.blog.service.BlogService;
import com.ccl.blog.service.CommentService;
import com.ccl.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @author CCL
 * @date 2019/9/4 12:11
 */
@Controller
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private CommentService commentService;

    @Autowired
    private BlogMapper blogMapper;

    /**
     * 来到写博客页面
     *
     * @return
     */
    @GetMapping("/write")
    public String writeBlog(HttpServletRequest request) {
        request.getSession().setAttribute("token", UUID.randomUUID());
        request.getSession().removeAttribute("write_msg");
        return "blog/write";
    }

    @GetMapping("/editor")
    public String editor() {
        return "blog/editor";
    }

    /**
     * 增加阅读数
     * 点赞有问题 阅读数也有问题！！！！待解决
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/look/{id}")
    public String lookBlog(@PathVariable(value = "id") Integer id, Model model) {
        Integer allComment = commentMapper.countByBlogId(id);
        blogService.addBlogLike(1, id);
        blogService.addBlogBrowse(1, id);
        //查找问题相似的博客
        String label = blogMapper.findOneLabelById(id);
        List<BlogDTO> blogs = blogService.findLikeBlog(label, id);
        //根据博客id查询
        Blog blog = blogService.selectByPrimaryKey(id);
        User user = userService.selectByPrimaryKey(blog.getUserId().intValue());
        HashMap<String, Object> blogMap = new HashMap<>(10);
        List<CommentStrDTO> commentStrDTOS = commentService.findAllComment(id);
        blogMap.put("comments", commentStrDTOS);
        blogMap.put("blog", blog);
        blogMap.put("user", user);
        blogMap.put("allComment", allComment);
        blogMap.put("blogs", blogs);
        model.addAllAttributes(blogMap);
        return "look/look";
    }

    /**
     * 1.判断文章标题、文章内容、文章标签是否为空（前端判断后端未写）
     * 2.从前端接收blog的标题，内容，标签，用户id，token（判断是否是重复提交）（json数据）
     * 3.用户进入写博客页面创建一个利用UUID创建一个token保存到session中，然后提交时提交到后端，检验token是否相等，然后删除session中的token
     * 4.保存到数据库中，跳转到用户博客管理页面
     *
     * @param blog
     * @param request
     * @return
     */
    @ResponseBody
    @PostMapping("/write")
    public String writeService(@RequestBody Blog blog, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String token = "token";
        String writeToken = "";
        Enumeration<String> sessions = session.getAttributeNames();
        List<String> names = new ArrayList<>();
        while (sessions.hasMoreElements()) {
            String name = sessions.nextElement();
            names.add(name);
        }
        if (names.contains(token)) {
            writeToken = session.getAttribute("token").toString();
            session.removeAttribute("token");
        }
        if (writeToken.isEmpty()) {
            return "failure";
        }
        if (writeToken.equals(blog.getToken())) {
            blog.setTime(new Date());
            blogMapper.insertSelective(blog);
            return "success";
        }
        return "failure";
    }
}
