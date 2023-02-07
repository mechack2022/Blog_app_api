package com.fragile.blog_api.services;

import com.fragile.blog_api.entities.Category;
import com.fragile.blog_api.payloads.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto createCategory(CategoryDto category);


    CategoryDto updateCategory(CategoryDto category, Integer id);


    List<CategoryDto> getAllCategories();


    void deleteCategory(Integer categoryId);

    public CategoryDto getSingleCategory(Integer categoryId);
}
