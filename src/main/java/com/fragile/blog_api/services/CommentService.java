package com.fragile.blog_api.services;

import com.fragile.blog_api.payloads.CommentDto;


import java.util.List;

public interface CommentService {
    CommentDto createComment(CommentDto commentDto, Integer postId );

    CommentDto updateComment(CommentDto commentDto, Integer commentId);

    CommentDto getCommentById(Integer commentId);

    void deleteComment(Integer commentId);

//    PostResponse getAllComment(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    //    get all post by category
//    List<PostDto> getAllPostsByCategory(Integer categoryId);

//    List<CommentDto> getAllPostsByUser(Integer userId);
//
//    List<CommentDto> searchPost(String keyword);



}
