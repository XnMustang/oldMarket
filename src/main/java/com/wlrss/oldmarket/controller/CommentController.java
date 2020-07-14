package com.wlrss.oldmarket.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wlrss.oldmarket.entity.Comment;
import com.wlrss.oldmarket.log.MyLog;
import com.wlrss.oldmarket.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    CommentService commentService;

    @RequestMapping("/commentList")
    @ResponseBody
    public String listAllComment(Integer limit,Integer page){
        PageHelper.startPage(page,limit);
        List<Comment> list=commentService.listAllComment();
        PageInfo<Comment> pi=new PageInfo<>(list);
        String st="{\n" +
                "  \"code\": 0,\n" +
                "  \"msg\": \"\",\n" +
                "  \"count\": "+pi.getTotal()+",\n" +
                "  \"data\":"+JSON.toJSON(list)+"}";
        return st;
    }

    @RequestMapping("/deleteById")
    @ResponseBody
    @MyLog("被管理员删除了一条评论")
    public void deleteById(Integer id){
        System.out.println("删除的ID"+id);
        commentService.deleteCommentById(id);
    }
    @RequestMapping("/searchComment")
    @ResponseBody
    public String searchComment(@RequestBody Comment comment){
        System.out.println("ccccccccccccccccccccccccccccc"+comment);
        String str="{\n" +
                "  \"code\": 0,\n" +
                "  \"msg\": \"\",\n" +
                "  \"count\": 1000,\n" +
                "  \"data\":"+JSON.toJSON(commentService.searchCommenttable(comment))+"}";

        return str;
    }

}
