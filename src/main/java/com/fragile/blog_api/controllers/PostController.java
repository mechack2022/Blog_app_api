package com.fragile.blog_api.controllers;

import com.fragile.blog_api.config.AppConstant;
import com.fragile.blog_api.payloads.ApiResponse;
import com.fragile.blog_api.payloads.PostDto;
import com.fragile.blog_api.payloads.PostResponse;
import com.fragile.blog_api.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId, @PathVariable Integer categoryId) {
        PostDto createdPost = postService.createPost(postDto, userId, categoryId);
        return new ResponseEntity<PostDto>(createdPost, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId) {
        List<PostDto> userPostList = postService.getAllPostsByUser(userId);
        return new ResponseEntity<>(userPostList, HttpStatus.OK);
    }


    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId) {
        List<PostDto> categoryPostList = postService.getAllPostsByCategory(categoryId);
        return new ResponseEntity<>(categoryPostList, HttpStatus.OK);
    }

    //get post by id
    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId) {
        return new ResponseEntity<>(postService.getPostById(postId), HttpStatus.OK);
    }

    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPosts(
            @RequestParam(value = "pageNumber", defaultValue = AppConstant.PAGE_NUMBER , required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstant.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstant.SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstant.SORT_DIR, required = false) String sortDir) {
        return new ResponseEntity<>(postService.getAllPosts(pageNumber, pageSize, sortBy, sortDir), HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId) {
        postService.deletePost(postId);
        return new ResponseEntity<>(new ApiResponse("post deleted successfully", true), HttpStatus.OK);
    }

    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto post, @PathVariable Integer postId) {
        PostDto updatedPost = postService.updatePost(post, postId);
        return new ResponseEntity<>(updatedPost, HttpStatus.CREATED);
    }

    @GetMapping("/posts/search/{keywords}")
    public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable String keywords) {
        return new ResponseEntity<>(postService.searchPost(keywords), HttpStatus.OK);
    }


}
