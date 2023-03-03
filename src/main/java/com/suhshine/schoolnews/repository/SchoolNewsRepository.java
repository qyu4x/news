package com.suhshine.schoolnews.repository;

import com.suhshine.schoolnews.entity.SchoolNews;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolNewsRepository extends JpaRepository<SchoolNews, String> {

}
