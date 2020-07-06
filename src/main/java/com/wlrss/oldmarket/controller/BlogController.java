package com.wlrss.oldmarket.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wlrss.oldmarket.entity.Blog;
import com.wlrss.oldmarket.entity.BlogType;
import com.wlrss.oldmarket.mapper.BlogMapper;
import com.wlrss.oldmarket.mapper.BlogTypeMapper;
import com.wlrss.oldmarket.mapper.OrderDetailMapper;
import com.wlrss.oldmarket.mapper.UserMapper;
import jdk.nashorn.internal.ir.BlockStatement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author jamesBond
 * @createTime 2020/7/4/004 15:14
 */
@Controller
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    OrderDetailMapper orderDetailMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    BlogMapper blogMapper;

    @Autowired
    BlogTypeMapper blogTypeMapper;


    @RequestMapping("/add")
    public String addBlog(HttpSession session, String title, String type) {

        String email = (String) session.getAttribute("email");
        int userId = orderDetailMapper.findUserIdByEmail(email);

        Blog blog = new Blog();
        blog.setTitle(title);
        blog.setUserid(userId);

        BlogType blogType= new BlogType();


        return null;

    }

    /**
     * 查找blog种类
     *
     * @param model
     * @return
     */
    @RequestMapping("/findBlogType")
    public String findBlogType(Model model) {
        List<BlogType> blogTypes = blogTypeMapper.selectList(null);
        System.out.println(blogTypes);
        model.addAttribute("blogTypeList", blogTypes);
        return "blog-single";
    }
}
