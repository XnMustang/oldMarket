package com.wlrss.oldmarket.service.impl;

import com.wlrss.oldmarket.entity.Comment;
import com.wlrss.oldmarket.mapper.CommentMapper;
import com.wlrss.oldmarket.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
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
}
