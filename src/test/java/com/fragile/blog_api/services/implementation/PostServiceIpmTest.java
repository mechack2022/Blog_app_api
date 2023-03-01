package com.fragile.blog_api.services.implementation;

import com.fragile.blog_api.entities.Category;
import com.fragile.blog_api.entities.Post;
import com.fragile.blog_api.entities.User;
import com.fragile.blog_api.payloads.CategoryDto;
import com.fragile.blog_api.payloads.PostDto;
import com.fragile.blog_api.repositories.CategoryRepo;
import com.fragile.blog_api.repositories.PostRepo;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;


import com.fragile.blog_api.exceptions.ResourceNotFoundException;
import com.fragile.blog_api.payloads.UserDto;
import com.fragile.blog_api.repositories.UserRepo;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class PostServiceIpmTest {

    @Mock
    private PostRepo postRepo;

    @Mock
    private UserRepo userRepo;

    @Mock
    private CategoryRepo categoryRepo;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private PostServiceIpm postService;

    @Test
    public void testCreatePost() {
        // Arrange
        User user = new User();
        Category category = new Category();
        PostDto postDto = new PostDto();
        postDto.setTitle("Test Post");
        postDto.setContent("This is a test post.");
        postDto.setPostImage("test.png");
        postDto.setCategory(new CategoryDto());
        postDto.setUser(new UserDto());
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setPostImage(postDto.getPostImage());
        post.setUser(user);
        post.setCategory(category);
        when(userRepo.findById(1)).thenReturn(Optional.of(user));
        when(categoryRepo.findById(2)).thenReturn(Optional.of(category));
        when(modelMapper.map(postDto, Post.class)).thenReturn(post);
        when(postRepo.save(post)).thenReturn(post);

        // Act
        PostDto createdPostDto = postService.createPost(postDto, 1, 2);

        // Assert
//        assertNotNull(createdPostDto);
        assertEquals(postDto.getTitle(), createdPostDto.getTitle());
        assertEquals(postDto.getContent(), createdPostDto.getContent());
        assertEquals(postDto.getPostImage(), createdPostDto.getPostImage());
    }

    @Test
    public void testGetPostById() {
        // Arrange
        Post post = new Post();
        post.setPostId(1);
        post.setTitle("Test Post");
        post.setContent("This is a test post.");
        post.setPostImage("test.png");
        when(postRepo.findById(1)).thenReturn(Optional.of(post));
        when(modelMapper.map(post, PostDto.class)).thenReturn(new PostDto());

        // Act
        PostDto postDto = postService.getPostById(1);

        // Assert
        assertNotNull(postDto);
        assertEquals(post.getPostId(), postDto.getPostId());
        assertEquals(post.getTitle(), postDto.getTitle());
        assertEquals(post.getContent(), postDto.getContent());
        assertEquals(post.getPostImage(), postDto.getPostImage());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testGetPostByIdWhenPostNotFound() {
        // Arrange
        when(postRepo.findById(1)).thenReturn(Optional.empty());

        // Act
        postService.getPostById(1);

        // Assert (exception expected)
    }

    @Test
    public void testDeletePost() {
        // Arrange
        Post post = new Post();
        post.setPostId(1);
        when(postRepo.findById(1)).thenReturn(Optional.of(post));

        // Act
        postService.deletePost(1);

        // Assert
        verify(postRepo, times(1)).delete(post);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testDeletePostWhenPostNotFound() {
        // Arrange
        when(postRepo.findById(1)).thenReturn(Optional.empty());

        // Act
        postService.deletePost(1);

        // Assert (exception expected)
    }

    // Add more test cases as needed for the other methods in the PostServiceIpm class
}
