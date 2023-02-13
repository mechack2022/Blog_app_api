package com.fragile.blog_api.controllers;


import com.fragile.blog_api.payloads.ApiResponse;
import com.fragile.blog_api.payloads.CommentDto;
import com.fragile.blog_api.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentController {

    CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto comment,@PathVariable Integer postId){
        return new ResponseEntity<>(commentService.createComment(comment, postId), HttpStatus.CREATED);
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable String commentId){
        return new  ResponseEntity<>(new ApiResponse("Comement deleted successfully", true), HttpStatus.OK);
    }


}
