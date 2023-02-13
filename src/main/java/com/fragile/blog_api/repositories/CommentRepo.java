package com.fragile.blog_api.repositories;

import com.fragile.blog_api.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment, Integer> {

}
