package com.ccl.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author CCL
 * @date 2019/9/9 23:37
 */
@Controller
public class TestController {
    @GetMapping("/test")
    public String test(HttpServletRequest request, HttpServletResponse response) {
        return "test";
    }

    @PostMapping("/blogtest")
    public String test01(String title, String label, String content) {
        System.out.println(title);
        System.out.println(label);
        System.out.println(content);
        return "/";
    }
}
