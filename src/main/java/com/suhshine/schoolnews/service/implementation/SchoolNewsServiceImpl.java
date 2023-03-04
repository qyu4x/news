package com.suhshine.schoolnews.service.implementation;

import com.suhshine.schoolnews.entity.SchoolNews;
import com.suhshine.schoolnews.payload.request.SchoolNewsRequest;
import com.suhshine.schoolnews.payload.response.SchoolNewsResponse;
import com.suhshine.schoolnews.repository.SchoolNewsRepository;
import com.suhshine.schoolnews.service.SchoolNewsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.UUID;

@Service
public class SchoolNewsServiceImpl implements SchoolNewsService {

    private static final Logger log = LoggerFactory.getLogger(SchoolNewsServiceImpl.class);


    private SchoolNewsRepository schoolNewsRepository;

    public SchoolNewsServiceImpl(SchoolNewsRepository schoolNewsRepository) {
        this.schoolNewsRepository = schoolNewsRepository;
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
        return null;
    }

    @Override
    public SchoolNewsResponse findById(String id) {
        return null;
    }

    @Override
    public void deleteById(String id) {

    }
}
