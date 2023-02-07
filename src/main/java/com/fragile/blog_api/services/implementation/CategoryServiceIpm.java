package com.fragile.blog_api.services.implementation;

import com.fragile.blog_api.entities.Category;
import com.fragile.blog_api.exceptions.ResourceNotFoundException;
import com.fragile.blog_api.payloads.CategoryDto;
import com.fragile.blog_api.repositories.CategoryRepo;
import com.fragile.blog_api.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceIpm implements CategoryService {

    private final CategoryRepo categoryRepo;


    ModelMapper  modelMapper;

    @Autowired
    public CategoryServiceIpm(CategoryRepo categoryRepo, ModelMapper modelMapper) {
        this.categoryRepo = categoryRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public CategoryDto createCategory(CategoryDto category) {
        Category addedCategory =  modelMapper.map(category, Category.class);
        Category savedCategory = categoryRepo.save(addedCategory);
        return modelMapper.map(savedCategory, CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer id) {
     Category categoryToUpdate =  categoryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("category", "id ", id));

     categoryToUpdate.setCategoryDescription(categoryDto.getCategoryDescription());
     categoryToUpdate.setCategoryTitle(categoryDto.getCategoryTitle());

      Category savedCategory = categoryRepo.save(categoryToUpdate);
        return  modelMapper.map(savedCategory, CategoryDto.class);
    }


    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categoryDtoList  = categoryRepo.findAll();
       return categoryDtoList.stream().map(category -> modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());

    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Category found =  categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("category", "id ",categoryId));
         categoryRepo.delete(found);
    }

    public CategoryDto getSingleCategory(Integer categoryId) {
        Category found = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("category", "id ",categoryId));
        return  modelMapper.map(found, CategoryDto.class);
    }

}
