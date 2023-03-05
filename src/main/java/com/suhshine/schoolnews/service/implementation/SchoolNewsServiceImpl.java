package com.suhshine.schoolnews.service.implementation;

import com.suhshine.schoolnews.entity.Comment;
import com.suhshine.schoolnews.entity.SchoolNews;
import com.suhshine.schoolnews.payload.request.SchoolNewsRequest;
import com.suhshine.schoolnews.payload.response.CommentResponse;
import com.suhshine.schoolnews.payload.response.SchoolNewsResponse;
import com.suhshine.schoolnews.repository.CommentRepository;
import com.suhshine.schoolnews.repository.SchoolNewsRepository;
import com.suhshine.schoolnews.service.SchoolNewsService;
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
public class SchoolNewsServiceImpl implements SchoolNewsService {

    private static final Logger log = LoggerFactory.getLogger(SchoolNewsServiceImpl.class);


    private SchoolNewsRepository schoolNewsRepository;

    private CommentRepository commentRepository;

    public SchoolNewsServiceImpl(SchoolNewsRepository schoolNewsRepository, CommentRepository commentRepository) {
        this.schoolNewsRepository = schoolNewsRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public SchoolNewsResponse save(SchoolNewsRequest schoolNewsRequest) {
        log.info("Save news data to the database");

        SchoolNews schoolNews = new SchoolNews();
        schoolNews.setId(UUID.randomUUID().toString().replace("-", ""));
        schoolNews.setTitle(schoolNewsRequest.getTitle());
        schoolNews.setImageUrl(schoolNewsRequest.getImageUrl());
        schoolNews.setContent(schoolNewsRequest.getContent());
        schoolNews.setAuthor(schoolNewsRequest.getAuthor());
        schoolNews.setCreatedAt(OffsetDateTime.of(LocalDateTime.now(), ZoneOffset.of("+07:00")).toLocalDateTime());

        schoolNewsRepository.save(schoolNews);

        SchoolNewsResponse schoolNewsResponse = new SchoolNewsResponse();
        schoolNewsResponse.setId(schoolNews.getId());
        schoolNewsResponse.setTitle(schoolNews.getTitle());
        schoolNewsResponse.setImageUrl(schoolNews.getImageUrl());
        schoolNewsResponse.setContent(schoolNews.getContent());
        schoolNewsResponse.setAuthor(schoolNews.getAuthor());
        schoolNewsResponse.setUploadDate(schoolNews.getCreatedAt().toLocalDate());
        schoolNewsResponse.setUploadHours(schoolNews.getCreatedAt().toLocalTime());

        log.info("Successfully save news data to the database");
        return schoolNewsResponse;
    }

    @Override
    public List<SchoolNewsResponse> getAll() {
        log.info("Get all data school news");
        List<SchoolNews> schoolNews = schoolNewsRepository.findAll();

        List<SchoolNewsResponse> schoolNewsResponses = new ArrayList<>();
        schoolNews.stream().forEach(news -> {
            SchoolNewsResponse schoolNewsResponse = new SchoolNewsResponse();

            log.info("Get all data comment of news");
            List<Comment> comments = commentRepository.findAllBySchoolNewsId(news.getId())
                    .orElse(Collections.emptyList());

            List<CommentResponse> commentResponses = new ArrayList<>();
            comments.stream().forEach(comment -> {
                CommentResponse commentResponse = new CommentResponse();

                commentResponse.setId(comment.getId());
                commentResponse.setFullName(comment.getFullName());
                commentResponse.setEmail(comment.getEmail());
                commentResponse.setContent(comment.getContent());
                commentResponse.setUploadDate(comment.getCreatedAt().toLocalDate());
                commentResponse.setUploadHours(comment.getCreatedAt().toLocalTime());

                commentResponses.add(commentResponse);
            });
            log.info("Successfully get all data comment from news");

            schoolNewsResponse.setId(news.getId());
            schoolNewsResponse.setTitle(news.getTitle());
            schoolNewsResponse.setAuthor(news.getAuthor());
            schoolNewsResponse.setContent(news.getContent());
            schoolNewsResponse.setImageUrl(news.getImageUrl());
            schoolNewsResponse.setComments(commentResponses);
            schoolNewsResponse.setUploadDate(news.getCreatedAt().toLocalDate());
            schoolNewsResponse.setUploadHours(news.getCreatedAt().toLocalTime());

            schoolNewsResponses.add(schoolNewsResponse);
        });

        log.info("Successfully get all data school news");
        return schoolNewsResponses;
    }

    @Override
    public SchoolNewsResponse findById(String id) {
        return null;
    }

    @Override
    public void deleteById(String id) {

    }
}