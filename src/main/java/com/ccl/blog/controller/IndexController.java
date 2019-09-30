package com.ccl.blog.controller;

import com.ccl.blog.dto.PageBlogDTO;
import com.ccl.blog.mapper.BlogMapper;
import com.ccl.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author CCL
 * @date 2019/9/2 12:15
 */
@Controller
public class IndexController {

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private BlogService blogService;

    /**
     * 分页！！！到主页
     *
     * @param page  设置分页起始 固定从1开始
     * @param size  每一页个数，可变 默认5
     * @param model
     * @return
     */
    @GetMapping("/")
    public String goIndex(@RequestParam(value = "page", defaultValue = "1") Integer page,
                          @RequestParam(value = "size", defaultValue = "10") Integer size,
                          Model model) {
        PageBlogDTO pageBlogDTO = blogService.findAllBlogDTO(page, size);
        model.addAttribute("page", pageBlogDTO);
        return "index";
    }
}
