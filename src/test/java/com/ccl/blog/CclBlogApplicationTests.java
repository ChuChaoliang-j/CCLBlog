package com.ccl.blog;

import com.ccl.blog.dto.CommentStrDTO;
import com.ccl.blog.entity.Comment;
import com.ccl.blog.entity.User;
import com.ccl.blog.mapper.BlogMapper;
import com.ccl.blog.mapper.CommentMapper;
import com.ccl.blog.mapper.UserMapper;
import com.ccl.blog.service.LoginService;
import com.ccl.blog.service.impl.BlogServiceImpl;
import com.ccl.blog.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CclBlogApplicationTests {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private LoginService loginService;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private BlogServiceImpl blogService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BlogMapper blogMapper;

    @Test
    public void contextLoads() {
        List<Comment> comments = commentMapper.findAllByBlogId(9);
        List<Integer> ids = (List<Integer>) commentMapper.findOneUserIdByBlogId(9).stream().distinct().collect(Collectors.toList());
        List<User> users = userMapper.findAllByIdInOrderById(ids);
        Map<Integer, User> userMap = users.stream().collect(Collectors.toMap(user -> user.getId(), user -> user));

        List<CommentStrDTO> commentStrDTOS = comments.stream().map(comment -> {
                CommentStrDTO commentStrDTO = new CommentStrDTO();
                BeanUtils.copyProperties(comment, commentStrDTO);
                commentStrDTO.setUser(userMap.get(comment.getUserId()));
                return commentStrDTO;
        }).collect(Collectors.toList());
    }

    @Test
    public void test01() {
    }
}
