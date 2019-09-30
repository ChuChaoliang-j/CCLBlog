package com.ccl.blog.controller;

import com.ccl.blog.dto.CommentDTO;
import com.ccl.blog.dto.CommentStrDTO;
import com.ccl.blog.entity.Comment;
import com.ccl.blog.entity.ResponseEntity;
import com.ccl.blog.entity.User;
import com.ccl.blog.enumerate.ResponseCode;
import com.ccl.blog.mapper.CommentMapper;
import com.ccl.blog.mapper.UserMapper;
import com.ccl.blog.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author CCL
 * @date 2019/9/14 17:28
 */
@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private UserMapper userMapper;

    /**
     * 返回所有的评论
     *
     * @param commentDTO
     * @param request
     * @return
     */
    @ResponseBody
    @PostMapping("/comment")
    public Object Comment(@RequestBody CommentDTO commentDTO, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        Comment comment = new Comment();
        comment.setBlogId(commentDTO.getBlogId());
        comment.setContent(commentDTO.getContent());
        comment.setType(commentDTO.getType());
        comment.setUserId(user.getId());
        commentService.insertSelective(comment);

        HttpSession session = request.getSession();
        /**
         * 1.根据博客id查询出所有的博客信息
         * 2.根据博客评论中的user_id查询出所有用户，并对应起来存到CommentStrDAO中
         */
        session.removeAttribute("comments");
        List<Comment> comments = commentMapper.findAllByBlogId(commentDTO.getBlogId());
        List<CommentStrDTO> commentStrDTOS = new ArrayList<>();
        for (Comment comment1 : comments) {
            CommentStrDTO commentStrDTO = new CommentStrDTO();
            BeanUtils.copyProperties(comment1, commentStrDTO);
            User user1 = userMapper.selectByPrimaryKey(comment1.getUserId());
            commentStrDTO.setUser(user1);
            commentStrDTOS.add(commentStrDTO);
        }
        session.setAttribute("comments", commentStrDTOS);

        return new ResponseEntity(ResponseCode.SUCCESS, user);
    }

    /**
     * 返回所有的子评论
     * 1.根据博客id查询到数据，得到此评论的用户id
     * 2.根据博客id获取此博客的全部评论（包括子评论和父评论）
     * 3.查询此用户是不是父id
     * 4.检测type字段（1，父评论 2，子评论）
     *
     * @param id
     * @return
     */
    @ResponseBody
    @GetMapping("/comment/{id}")
    public Object getSonComment(@PathVariable(name = "id") Integer id) {
        //获取父评论内容
        Comment parentComment = commentService.selectByPrimaryKey(id);
        //获取父id
        Integer parentId = parentComment.getUserId();
        //获取该评论的博客id
        Integer blogId = parentComment.getBlogId();
        //获取该博客的全部评论
        List<Comment> comments = commentMapper.findAllByBlogId(blogId);
        List<CommentStrDTO> commentStrDTOs = new ArrayList<>();
        for (Comment comment : comments) {
            if (comment.getParentId().equals(parentId) && comment.getType() == 2) {
                CommentStrDTO commentStrDTO = new CommentStrDTO();
                //格式化时间
                SimpleDateFormat formatTime = new SimpleDateFormat("yyyy-MM-dd");
                String createDate = formatTime.format(comment.getCreateTime());
                //1.根据user_id 查询User数据，保存到commentStrDTO中
                User user = userMapper.selectByPrimaryKey(comment.getUserId());
                BeanUtils.copyProperties(comment, commentStrDTO);
                commentStrDTO.setUser(user);
                commentStrDTO.setStrCreateTime(createDate);
                commentStrDTOs.add(commentStrDTO);
            }
        }
        return commentStrDTOs;
    }

    /**
     * 提交子评论
     * 1.将评论保存到数据库
     * -comment_id:自动增加    -blogId：前端获取
     * -content:前端获取       -type：设置为2为子评论
     * -createTime:生成当前时间 -updateTime:未知
     * -userId:前端获取        -like:未知
     * -parentId:前端获取      -strCreateTime:未知
     *
     * @return
     */
    @ResponseBody
    @PostMapping("/commentSon")
    public Object submitSonComment(@RequestBody Comment comment) {
        Comment sonComment = new Comment();
        sonComment.setBlogId(comment.getBlogId());
        sonComment.setUserId(comment.getUserId());
        sonComment.setContent(comment.getContent());
        sonComment.setType(2);
        sonComment.setCreateTime(new Date());
        sonComment.setParentId(comment.getParentId());
        commentMapper.insertSelective(sonComment);
        return new ResponseEntity(ResponseCode.SUCCESS, sonComment);
    }
}
