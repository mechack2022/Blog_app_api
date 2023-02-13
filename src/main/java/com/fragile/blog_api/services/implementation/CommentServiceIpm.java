package com.fragile.blog_api.services.implementation;

import com.fragile.blog_api.entities.Comment;
import com.fragile.blog_api.entities.Post;
import com.fragile.blog_api.exceptions.ResourceNotFoundException;
import com.fragile.blog_api.payloads.CommentDto;
import com.fragile.blog_api.repositories.CommentRepo;
import com.fragile.blog_api.repositories.PostRepo;
import com.fragile.blog_api.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceIpm implements CommentService {


    private final CommentRepo commentRepo;
    private final PostRepo postRepo;

    @Autowired
    private  ModelMapper modelMapper;

    public CommentServiceIpm(CommentRepo commentRepo, PostRepo postRepo) {
        this.commentRepo = commentRepo;
        this.postRepo = postRepo;
    }

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {
        Post commentPost = postRepo.findById(postId).orElseThrow(() ->new ResourceNotFoundException( "Post", "postId", postId ));
        Comment newComment = modelMapper.map(commentDto, Comment.class);
        newComment.setPost(commentPost);
        Comment savedComment = commentRepo.save(newComment);

        return modelMapper.map(savedComment, CommentDto.class);
    }

    @Override
    public CommentDto updateComment(CommentDto commentDto, Integer commentId) {
        return null;
    }

    @Override
    public CommentDto getCommentById(Integer commentId) {
        return null;
    }

    @Override
    public void deleteComment(Integer commentId) {
     Comment found = commentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("comment", "commentId",commentId));
     commentRepo.delete(found);

    }
}
