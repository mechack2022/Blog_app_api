package com.fragile.blog_api.repositories;

import com.fragile.blog_api.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {
}
