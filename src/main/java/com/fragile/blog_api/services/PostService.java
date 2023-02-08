package com.fragile.blog_api.services;

import com.fragile.blog_api.entities.Post;
import com.fragile.blog_api.payloads.CategoryDto;
import com.fragile.blog_api.payloads.PostDto;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto, Integer userId , Integer categoryId );

    Post updatePost(PostDto postDto, Integer postId);

    Post getPostById(Integer postId);

    void deletePost(Integer postId);

    List<Post> getAllPosts();

//    get all post by category
    List<Post> getAllPostsByCategory(Integer categoryId);

    List<Post> getAllPostsByUser(Integer userId);

    List<Post> searchPost(String keyword);



}
