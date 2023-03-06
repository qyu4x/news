package com.suhshine.schoolnews.controller;

import com.suhshine.schoolnews.entity.Comment;
import com.suhshine.schoolnews.payload.request.CommentRequest;
import com.suhshine.schoolnews.payload.request.SchoolNewsRequest;
import com.suhshine.schoolnews.payload.response.CommentResponse;
import com.suhshine.schoolnews.payload.response.SchoolNewsResponse;
import com.suhshine.schoolnews.payload.response.WebResponse;
import com.suhshine.schoolnews.service.CommentService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/comment")
public class CommentNewsController {

    private static final Logger log = LoggerFactory.getLogger(CommentNewsController.class);

    private final CommentService commentService;

    public CommentNewsController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WebResponse<CommentResponse>> save(@PathVariable("id") String id,  @Valid @RequestBody CommentRequest commentRequest) {
        log.info("Save data comment with news id : {}", id);
        CommentResponse commentResponse = commentService.save(id, commentRequest);
        WebResponse<CommentResponse> webResponse = new WebResponse<>(
                HttpStatus.CREATED.value(),
                HttpStatus.CREATED.getReasonPhrase(),
                commentResponse
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(webResponse);

    }
}
