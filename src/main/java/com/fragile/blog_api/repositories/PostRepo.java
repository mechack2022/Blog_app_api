package com.fragile.blog_api.repositories;

import com.fragile.blog_api.entities.Category;
import com.fragile.blog_api.entities.Post;
import com.fragile.blog_api.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Integer> {
    List<Post> findByUser(User user);
    List<Category> findByCategory(Category category);
}
