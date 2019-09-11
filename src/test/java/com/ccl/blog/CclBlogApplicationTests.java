package com.ccl.blog;

import java.util.List;

import com.ccl.blog.dto.BlogDTO;
import com.ccl.blog.service.LoginService;
import com.ccl.blog.service.impl.BlogServiceImpl;
import com.ccl.blog.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CclBlogApplicationTests {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private LoginService loginService;

    @Autowired
    private BlogServiceImpl blogService;

    @Test
    public void contextLoads() {
    }

}
