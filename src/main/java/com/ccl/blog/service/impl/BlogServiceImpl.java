package com.ccl.blog.service.impl;

import com.ccl.blog.dto.BlogDTO;
import com.ccl.blog.dto.PageBlogDTO;
import com.ccl.blog.entity.Blog;
import com.ccl.blog.entity.User;
import com.ccl.blog.mapper.BlogMapper;
import com.ccl.blog.mapper.UserMapper;
import com.ccl.blog.service.BlogService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @author CCL
 * @date 2019/9/4 15:35
 */

@Service
public class BlogServiceImpl implements BlogService {

    @Resource
    private BlogMapper blogMapper;

    @Resource
    private UserMapper userMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return blogMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Blog record) {
        return blogMapper.insert(record);
    }

    @Override
    public int insertSelective(Blog record) {
        return blogMapper.insertSelective(record);
    }

    @Override
    public Blog selectByPrimaryKey(Integer id) {
        return blogMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Blog record) {
        return blogMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Blog record) {
        return blogMapper.updateByPrimaryKey(record);
    }

    @Override
    public int acquireUserId(HttpServletRequest request) {
        HttpSession session = request.getSession();
        int id = 0;
        Object userStr = session.getAttribute("user");
        if (userStr != null) {
            User user = (User) userStr;
            id = user.getId();
        }
        return id;
    }

    @Override
    public String detectionBlogDateIsNull(String title, String content, String label) {
        if ("".equals(title)) {
            return "博客标题不能为空！！";
        }
        if ("".equals(content)) {
            return "文章内容不能为空！！";
        }
        if ("".equals(label)) {
            return "文章标签不能为空！！(文章标签在最底部 ！(￣_,￣ ))";
        }
        return "";
    }

    @Override
    public PageBlogDTO findAllBlogDTO(Integer page, Integer size) {
        if (page <= 0) {
            page = 1;
        }
        Integer count = blogMapper.count();
        Integer endPage = count % size == 0 ? count / size : count / size + 1;
        if (page > endPage) {
            page = endPage;
        }
        Integer firstPage = page * size - size;
        List<BlogDTO> blogDTOS = new ArrayList<>();
        List<Blog> blogs = blogMapper.findAllBlogByPage(firstPage, size);
        PageBlogDTO pageBlogDTO = new PageBlogDTO();
        pageBlogDTO.setPage(page, size, count);
        for (Blog blog : blogs) {
            BlogDTO blogDTO = new BlogDTO();
            User user = userMapper.selectByPrimaryKey(blog.getUserId().intValue());
            BeanUtils.copyProperties(blog, blogDTO);
            blogDTO.setUser(user);
            blogDTOS.add(blogDTO);
        }
        pageBlogDTO.setBlogs(blogDTOS);
        return pageBlogDTO;
    }

    @Override
    public PageBlogDTO findAllBlogDTO(Integer id, Integer page, Integer size) {
        if (page <= 0) {
            page = 1;
        }
        Integer count = blogMapper.countByUserId(id.longValue());
        Integer endPage = count % size == 0 ? count / size : count / size + 1;
        if (page > endPage) {
            page = endPage;
        }
        Integer firstPage = page * size - size;
        List<BlogDTO> blogDTOList = new ArrayList<>();
        List<Blog> blogList = blogMapper.findAllBlogByPageUser(id, firstPage, size);
        PageBlogDTO pageBlogDTO = new PageBlogDTO();
        pageBlogDTO.setPage(page, size, count);
        if (blogList != null) {
            for (Blog blog : blogList) {
                BlogDTO blogDTO = new BlogDTO();
                User user = userMapper.selectByPrimaryKey(blog.getUserId().intValue());
                BeanUtils.copyProperties(blog, blogDTO);
                blogDTO.setUser(user);
                blogDTOList.add(blogDTO);
            }
        }
        pageBlogDTO.setBlogs(blogDTOList);
        return pageBlogDTO;
    }
}
