package com.wlrss.oldmarket.service;

import com.wlrss.oldmarket.entity.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> listAllComment();

    void deleteCommentById(Integer id);

    List<Comment> searchCommenttable(Comment comment);
}
