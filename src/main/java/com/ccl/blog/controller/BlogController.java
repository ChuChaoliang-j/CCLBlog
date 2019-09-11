package com.ccl.blog.controller;

import com.ccl.blog.entity.Blog;
import com.ccl.blog.entity.User;
import com.ccl.blog.service.BlogService;
import com.ccl.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

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

    /**
     * 来到写博客页面
     *
     * @return
     */
    @GetMapping("/write")
    public String writeBlog(HttpServletRequest request) {
        request.getSession().removeAttribute("write_msg");
        return "blog/write";
    }

    @GetMapping("/editor")
    public String editor() {
        return "blog/editor";
    }

    @GetMapping("/look/{id}")
    public String lookBlog(@PathVariable(value = "id") Integer id, Model model) {
        Blog blog = blogService.selectByPrimaryKey(id);
        User user = userService.selectByPrimaryKey(blog.getUserId().intValue());
        HashMap<String, Object> blogMap = new HashMap<>(10);
        blogMap.put("blog", blog);
        blogMap.put("user", user);
        model.addAllAttributes(blogMap);
        return "look/look";
    }

    /**
     * 博客提交处理
     * 1.获取文章标题，文章内容，文章标签；
     * 2.id 自增 title标题：前端获取 content内容：前端获取 label标签：前端获取
     * time创建时间：自动生成  blog_userId用户id：session中获取(获取到id)
     * browse浏览量：默认为0  blog_like点赞量：默认为0
     * 3.将数据保存到数据库中
     * 4.转到主页面
     * 注意：（title，content，label如果有一个未输入提示错误，请重新输入）
     *
     * @param title
     * @param content
     * @param label
     * @param request
     * @return
     */
    @PostMapping("/write")
    public String writeService(@RequestParam(value = "title", required = false) String title,
                               @RequestParam(value = "content", required = false) String content,
                               @RequestParam(value = "label", required = false) String label,
                               @RequestParam(value = "contentHtml", required = false) String contentHtml,
                               HttpServletRequest request, Model model) {
        System.out.println(contentHtml);
        HttpSession session = request.getSession();
        String msg = blogService.detectionBlogDateIsNull(title, content, label);

        if (!"".equals(msg)) {
            HashMap<String, String> blogMap = new HashMap<>(10);
            blogMap.put("title", title);
            blogMap.put("content", content);
            blogMap.put("label", label);
            model.addAllAttributes(blogMap);
            session.setAttribute("write_msg", msg);
            return "blog/write";
        } else {
            long userId = blogService.acquireUserId(request);
            Blog blog = new Blog();
            blog.setTitle(title);
            blog.setLabel(label);
            blog.setUserId(userId);
            blog.setBlogContent(content);
            blogService.insertSelective(blog);
            return "redirect:/";
        }
    }
}
