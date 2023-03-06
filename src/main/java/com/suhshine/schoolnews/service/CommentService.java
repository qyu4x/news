package com.suhshine.schoolnews.service;

import com.suhshine.schoolnews.entity.Comment;
import com.suhshine.schoolnews.payload.request.CommentRequest;
import com.suhshine.schoolnews.payload.response.CommentResponse;

import java.util.List;

public interface CommentService {

    CommentResponse save(String id, CommentRequest commentRequest);
    List<CommentResponse> findByNewsId(String id);
    void deleteById(String id);
}
