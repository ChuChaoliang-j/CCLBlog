package com.ccl.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author CCL
 * @date 2019/9/2 12:15
 */
@Controller
public class IndexController {

    @GetMapping("/")
    public String goIndex() {
        return "index";
    }
}
