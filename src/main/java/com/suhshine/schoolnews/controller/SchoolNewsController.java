package com.suhshine.schoolnews.controller;

import com.suhshine.schoolnews.payload.request.SchoolNewsRequest;
import com.suhshine.schoolnews.payload.response.SchoolNewsResponse;
import com.suhshine.schoolnews.payload.response.WebResponse;
import com.suhshine.schoolnews.service.SchoolNewsService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/news")
public class SchoolNewsController {

    private static final Logger log = LoggerFactory.getLogger(SchoolNewsController.class);

    private final SchoolNewsService schoolNewsService;

    public SchoolNewsController(SchoolNewsService schoolNewsService) {
        this.schoolNewsService = schoolNewsService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WebResponse<SchoolNewsResponse>> save(@Valid @RequestBody SchoolNewsRequest schoolNewsRequest) {
        log.info("Save data with title : {}", schoolNewsRequest.getTitle());
        SchoolNewsResponse schoolNewsResponse = schoolNewsService.save(schoolNewsRequest);
        WebResponse<SchoolNewsResponse> webResponse = new WebResponse<>(
                HttpStatus.CREATED.value(),
                HttpStatus.CREATED.getReasonPhrase(),
                schoolNewsResponse
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(webResponse);

    }
}