package com.fragile.blog_api.services.implementation;

import com.fragile.blog_api.entities.Category;
import com.fragile.blog_api.entities.Post;
import com.fragile.blog_api.entities.User;
import com.fragile.blog_api.exceptions.ResourceNotFoundException;
import com.fragile.blog_api.payloads.PostDto;
import com.fragile.blog_api.payloads.PostResponse;
import com.fragile.blog_api.repositories.CategoryRepo;
import com.fragile.blog_api.repositories.PostRepo;
import com.fragile.blog_api.repositories.UserRepo;
import com.fragile.blog_api.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", " Id ", userId));
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("category", "id ", categoryId));
        Post newPost = modelMapper.map(postDto, Post.class);
        newPost.setPostImage("defaultImg.png");
        newPost.setAddedDate(new Date());
        newPost.setCategory(category);
        newPost.setUser(user);

        return modelMapper.map(postRepo.save(newPost), PostDto.class);

    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post userPost = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("user", "userId", postId));
        userPost.setTitle(postDto.getTitle());
        userPost.setPostImage(postDto.getPostImage());
        userPost.setContent(postDto.getContent());

        return modelMapper.map(userPost, PostDto.class);

    }

    @Override
    public PostDto getPostById(Integer postId) {
        Post AllPostsById = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", " id ", postId));
        return modelMapper.map(AllPostsById, PostDto.class);
    }

    @Override
    public void deletePost(Integer postId) {
        Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", " id ", postId));
        postRepo.delete(post);

    }

    @Override
    public PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        Sort sort = (sortDir.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable p = PageRequest.of(pageNumber, pageSize, sort);
        Page<Post> pagePost = postRepo.findAll(p);
        List<Post> allPosts = pagePost.getContent();
//        List<Post> posts = postRepo.findAll();
        List<PostDto> postsInDto = allPosts.stream().map(post -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

        PostResponse response = new PostResponse();
        response.setContent(postsInDto);
        response.setPageSize(pagePost.getSize());
        response.setPageNumber(pagePost.getNumber());
        response.setTotalPages(pagePost.getTotalPages());
        response.setTotalElements(pagePost.getTotalElements());
        response.setLastPage(pagePost.isLast());

        return response;
    }

    @Override
    public List<PostDto> getAllPostsByCategory(Integer categoryId) {
        Category cat = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("category", " id", categoryId));

        List<Post> catPosts = postRepo.findByCategory(cat);
        return catPosts.stream().map(post -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> getAllPostsByUser(Integer userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", " id ", userId));
        List<Post> userPosts = postRepo.findByUser(user);

        return userPosts.stream().map(post -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> searchPost(String keyword) {
        List<Post> foundPost = postRepo.findByTitleContaining(keyword);
        return foundPost.stream().map((post) -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
    }
}
