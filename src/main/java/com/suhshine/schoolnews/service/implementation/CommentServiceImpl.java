package com.suhshine.schoolnews.service.implementation;

import com.suhshine.schoolnews.entity.Comment;
import com.suhshine.schoolnews.entity.SchoolNews;
import com.suhshine.schoolnews.exception.DataNotFoundException;
import com.suhshine.schoolnews.payload.request.CommentRequest;
import com.suhshine.schoolnews.payload.request.CommentUpdateRequest;
import com.suhshine.schoolnews.payload.response.CommentResponse;
import com.suhshine.schoolnews.repository.CommentRepository;
import com.suhshine.schoolnews.repository.SchoolNewsRepository;
import com.suhshine.schoolnews.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class CommentServiceImpl implements CommentService {

    private static final Logger log = LoggerFactory.getLogger(CommentServiceImpl.class);

    private final CommentRepository commentRepository;


    private final SchoolNewsRepository schoolNewsRepository;

    public CommentServiceImpl(CommentRepository commentRepository, SchoolNewsRepository schoolNewsRepository) {
        this.commentRepository = commentRepository;
        this.schoolNewsRepository = schoolNewsRepository;
    }


    @Override
    public CommentResponse save(String id, CommentRequest commentRequest) {
        log.info("Save comment data to the database");

        SchoolNews schoolNews = schoolNewsRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("School news data not found"));

        Comment comment = new Comment();
        comment.setId(UUID.randomUUID().toString().replace("-", ""));
        comment.setEmail(commentRequest.getEmail());
        comment.setFullName(commentRequest.getFullName());
        comment.setContent(commentRequest.getContent());
        comment.setUrl(commentRequest.getUrl());
        comment.setSchoolNews(schoolNews);
        comment.setCreatedAt(OffsetDateTime.of(LocalDateTime.now(), ZoneOffset.of("+07:00")).toLocalDateTime());

        commentRepository.save(comment);
        log.info("Successfully save comment data to the database");

        CommentResponse commentResponse = new CommentResponse();
        commentResponse.setId(comment.getId());
        commentResponse.setEmail(comment.getEmail());
        commentResponse.setFullName(comment.getFullName());
        commentResponse.setContent(comment.getContent());
        commentResponse.setUrl(comment.getUrl());
        commentResponse.setUploadDate(comment.getCreatedAt().toLocalDate());
        commentResponse.setUploadHours(comment.getCreatedAt().toLocalTime());

        return commentResponse;
    }

    @Override
    public List<CommentResponse> findByNewsId(String id) {
        log.info("Get all comment from database");

        schoolNewsRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("School news data not found"));

        List<Comment> comments = commentRepository.findAllBySchoolNewsId(id)
                .orElse(Collections.emptyList());

        List<CommentResponse> commentResponses = new ArrayList<>();
        comments.stream().forEach(comment -> {
            CommentResponse commentResponse = new CommentResponse();

            commentResponse.setId(comment.getId());
            commentResponse.setEmail(comment.getEmail());
            commentResponse.setFullName(comment.getFullName());
            commentResponse.setContent(comment.getContent());
            commentResponse.setUrl(comment.getUrl());
            commentResponse.setUploadDate(comment.getCreatedAt().toLocalDate());
            commentResponse.setUploadHours(comment.getCreatedAt().toLocalTime());

            commentResponses.add(commentResponse);
        });
        log.info("Successfully got all the comments list");

        return commentResponses;
    }

    @Override
    public void deleteById(String id) {
        log.info("Delete comment data from the database");
        commentRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Comment data not found"));
        commentRepository.deleteById(id);
        log.info("Successfully deleted the comment data from the database");
    }

    @Override
    public CommentResponse updateById(String id, CommentUpdateRequest commentUpdateRequest) {
        log.info("Update comment data from database");

        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Comment data not found"));

        comment.setContent(commentUpdateRequest.getContent());
        comment.setUpdatedAt(OffsetDateTime.of(LocalDateTime.now(), ZoneOffset.of("+07:00")).toLocalDateTime());

        commentRepository.save(comment);
        log.info("Successfully update comment data");

        CommentResponse commentResponse = new CommentResponse();
        commentResponse.setId(comment.getId());
        commentResponse.setEmail(comment.getEmail());
        commentResponse.setFullName(comment.getFullName());
        commentResponse.setContent(comment.getContent());
        commentResponse.setUrl(comment.getUrl());
        commentResponse.setUploadDate(comment.getCreatedAt().toLocalDate());
        commentResponse.setUploadHours(comment.getCreatedAt().toLocalTime());

        return commentResponse;
    }
}
