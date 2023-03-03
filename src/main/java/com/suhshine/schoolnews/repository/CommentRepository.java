package com.suhshine.schoolnews.repository;

import com.suhshine.schoolnews.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, String> {

}
