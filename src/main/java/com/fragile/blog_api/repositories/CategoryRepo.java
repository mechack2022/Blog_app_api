package com.fragile.blog_api.repositories;

import com.fragile.blog_api.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo  extends JpaRepository<Category, Integer> {
}
