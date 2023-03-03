package com.suhshine.schoolnews.service;

import com.suhshine.schoolnews.entity.Comment;
import com.suhshine.schoolnews.payload.response.CommentResponse;

import java.util.List;

public interface CommentService {

    CommentResponse save(String id);
    List<Comment> findById(String id);
    void deleteById(String id);
}
