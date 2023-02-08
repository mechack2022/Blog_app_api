package com.fragile.blog_api.services.implementation;

import com.fragile.blog_api.entities.Category;
import com.fragile.blog_api.entities.Post;
import com.fragile.blog_api.entities.User;
import com.fragile.blog_api.exceptions.ResourceNotFoundException;
import com.fragile.blog_api.payloads.PostDto;
import com.fragile.blog_api.repositories.CategoryRepo;
import com.fragile.blog_api.repositories.PostRepo;
import com.fragile.blog_api.repositories.UserRepo;
import com.fragile.blog_api.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PostServiceIpm implements PostService {

    PostRepo postRepo;

    UserRepo userRepo;

    CategoryRepo categoryRepo;
    ModelMapper modelMapper;


    @Autowired
    public PostServiceIpm(PostRepo postRepo, UserRepo userRepo, CategoryRepo categoryRepo, ModelMapper modelMapper) {
        this.postRepo = postRepo;
        this.userRepo = userRepo;
        this.categoryRepo = categoryRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId ) {
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", " Id ", userId));

        Category category =  categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("category", "id ",categoryId));

        Post newPost = modelMapper.map(postDto, Post.class);
        newPost.setPostImage("defaultImg.png");
        newPost.setAddedDate(new Date());
        newPost.setCategory(category);
        newPost.setUser(user);

        return modelMapper.map(postRepo.save(newPost), PostDto.class);

    }

    @Override
    public Post updatePost(PostDto postDto, Integer postId) {
        return null;
    }

    @Override
    public Post getPostById(Integer postId) {
        return null;
    }

    @Override
    public void deletePost(Integer postId) {

    }

    @Override
    public List<Post> getAllPosts() {
        return null;
    }

    @Override
    public List<Post> getAllPostsByCategory(Integer categoryId) {
        return null;
    }

    @Override
    public List<Post> getAllPostsByUser(Integer userId) {
        return null;
    }

    @Override
    public List<Post> searchPost(String keyword) {
        return null;
    }
}
