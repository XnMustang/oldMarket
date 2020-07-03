package com.wlrss.oldmarket.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wlrss.oldmarket.entity.Comment;
import com.wlrss.oldmarket.mapper.CommentMapper;
import com.wlrss.oldmarket.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
@Service
@RequestMapping("/comment")
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentMapper commentMapper;


    @Override
    public List<Comment> listAllComment() {
        return commentMapper.selectList(null);
    }

    @Override
    public void deleteCommentById(Integer id) {
        commentMapper.deleteById(id);
    }

    @Override
    public List<Comment> searchCommenttable(Comment comment) {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();

        if (comment.getSelluserid()!=null){
            queryWrapper.eq("selluserid",comment.getSelluserid());
        }
        if (comment.getBuyuserid()!=null){
            queryWrapper.eq("buyuserid",comment.getBuyuserid());
        }
        if (comment.getGoodsid()!=null){
            queryWrapper.eq("goodsid",comment.getGoodsid());
        }

        return commentMapper.selectList(queryWrapper);
    }


}
