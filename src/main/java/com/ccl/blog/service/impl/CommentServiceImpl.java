package com.ccl.blog.service.impl;

import com.ccl.blog.dto.CommentStrDTO;
import com.ccl.blog.entity.Comment;
import com.ccl.blog.entity.User;
import com.ccl.blog.mapper.CommentMapper;
import com.ccl.blog.mapper.UserMapper;
import com.ccl.blog.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author CCL
 * @date 2019/9/14 12:01
 */

@Service
public class CommentServiceImpl implements CommentService {

    @Resource
    private CommentMapper commentMapper;

    @Resource
    private UserMapper userMapper;

    @Override
    public int deleteByPrimaryKey(Integer commentId) {
        return commentMapper.deleteByPrimaryKey(commentId);
    }

    @Override
    public int insert(Comment record) {
        return commentMapper.insert(record);
    }

    @Override
    public int insertSelective(Comment record) {
        return commentMapper.insertSelective(record);
    }

    @Override
    public Comment selectByPrimaryKey(Integer commentId) {
        return commentMapper.selectByPrimaryKey(commentId);
    }

    @Override
    public int updateByPrimaryKeySelective(Comment record) {
        return commentMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Comment record) {
        return commentMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<CommentStrDTO> findAllComment(Integer id) {
        //根据博客id将所有的评论查出
        List<Comment> comments = commentMapper.findAllByBlogId(id);
        //若为空返回空列表
        if (comments.size() == 0) {
            return new ArrayList<>();
        }
        //查询评论此博客的所有用户id
        List<Integer> ids = (List<Integer>) commentMapper.findOneUserIdByBlogId(id).stream().distinct().collect(Collectors.toList());
        //ids为空返回空列表
        if (ids.size() == 0) {
            return new ArrayList<>();
        }
        //获取评论人并将其转换成map
        List<User> users = userMapper.findAllByIdInOrderById(ids);
        Map<Integer, User> userMap = users.stream().collect(Collectors.toMap(user -> user.getId(), user -> user));
        //转换comment为CommentStrDTO
        List<CommentStrDTO> commentStrDTOS = comments.stream().map(comment -> {
            CommentStrDTO commentStrDTO = new CommentStrDTO();
            BeanUtils.copyProperties(comment, commentStrDTO);
            commentStrDTO.setUser(userMap.get(comment.getUserId()));
            return commentStrDTO;
        }).collect(Collectors.toList());
        return commentStrDTOS;
    }

}





