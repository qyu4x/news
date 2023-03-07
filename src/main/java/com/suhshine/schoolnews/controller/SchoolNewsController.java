package com.suhshine.schoolnews.controller;

import com.suhshine.schoolnews.payload.request.SchoolNewsRequest;
import com.suhshine.schoolnews.payload.request.SchoolNewsUpdateRequest;
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

import java.util.List;

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

    @GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WebResponse<List<SchoolNewsResponse>>> findAll() {
        log.info("Get all data school news");
        List<SchoolNewsResponse> schoolNewsResponses = schoolNewsService.findAll();
        WebResponse<List<SchoolNewsResponse> > webResponse = new WebResponse<>(
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase(),
                schoolNewsResponses
        );

        return ResponseEntity.status(HttpStatus.OK).body(webResponse);

    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WebResponse<SchoolNewsResponse>> findById(@PathVariable("id") String id) {
        log.info("Get data school news by id");
        SchoolNewsResponse schoolNewsResponse = schoolNewsService.findById(id);
        WebResponse<SchoolNewsResponse> webResponse = new WebResponse<>(
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase(),
                schoolNewsResponse
        );

        return ResponseEntity.status(HttpStatus.OK).body(webResponse);

    }

    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WebResponse<String>> deleteById(@PathVariable("id") String id) {
        log.info("Delete data school news by id");
        schoolNewsService.deleteById(id);
        WebResponse<String> webResponse = new WebResponse<>(
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase(),
                String.format("successfully delete school news data with id %s", id)
        );

        return ResponseEntity.status(HttpStatus.OK).body(webResponse);

    }

    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WebResponse<SchoolNewsResponse>> updateById(@PathVariable("id") String id, @RequestBody SchoolNewsUpdateRequest schoolNewsUpdateRequest) {
        log.info("Update data school news by id");
        SchoolNewsResponse schoolNewsResponse = schoolNewsService.updateById(id, schoolNewsUpdateRequest);
        WebResponse<SchoolNewsResponse> webResponse = new WebResponse<>(
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase(),
                schoolNewsResponse
        );

        return ResponseEntity.status(HttpStatus.OK).body(webResponse);

    }
}
