package com.suhshine.schoolnews.service;

import com.suhshine.schoolnews.payload.request.SchoolNewsRequest;
import com.suhshine.schoolnews.payload.response.SchoolNewsResponse;

import java.util.List;

public interface SchoolNewsService {

    SchoolNewsResponse save(SchoolNewsRequest schoolNewsRequest);
    List<SchoolNewsResponse> getAll();
    SchoolNewsResponse findById(String id);
    void deleteById(String id);

}
