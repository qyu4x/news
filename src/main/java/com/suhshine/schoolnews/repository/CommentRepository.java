package com.suhshine.schoolnews.repository;

import com.suhshine.schoolnews.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, String> {

    @Query(
            nativeQuery = true,
            value = "SELECT * FROM comment c WHERE c.fk_school_news_id = :id"
    )
    Optional<List<Comment>> findAllBySchoolNewsId(@Param("id") String id);

}
