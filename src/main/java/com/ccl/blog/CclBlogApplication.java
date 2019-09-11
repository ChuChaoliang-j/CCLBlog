package com.ccl.blog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author CCL
 */
@SpringBootApplication
@MapperScan(value = "com.ccl.blog.mapper")
public class CclBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(CclBlogApplication.class, args);
    }

}
