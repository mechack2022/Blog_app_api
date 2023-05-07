package com.fragile.blog_api.services;

import com.fragile.blog_api.entities.Post;
import com.fragile.blog_api.payloads.CategoryDto;
import com.fragile.blog_api.payloads.PostDto;
import com.fragile.blog_api.payloads.PostResponse;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

    PostDto updatePost(PostDto postDto, Integer postId);

    PostDto getPostById(Integer postId);

    void deletePost(Integer postId);

    PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    //    get all post by category
    List<PostDto> getAllPostsByCategory(Integer categoryId);

    List<PostDto> getAllPostsByUser(Integer userId);

    List<PostDto> searchPost(String keyword);


}
