package com.fragile.blog_api;

import com.fragile.blog_api.config.AppConstant;
import com.fragile.blog_api.entities.Role;
import com.fragile.blog_api.repositories.RoleRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class BlogApiApplication implements CommandLineRunner {

    @Autowired
    private RoleRepo roleRepo;

    public static void main(String[] args) {
        SpringApplication.run(BlogApiApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            Role role1 = new Role();
            role1.setId(AppConstant.NORMAL_USER);
            role1.setName("NORMAL_USER");

            Role role2 = new Role();
            role2.setId(AppConstant.ADMIN_USER);
            role2.setName("ADMIN_USER");

            List<Role> roles = List.of(role1, role2);

            List<Role> result = this.roleRepo.saveAll(roles);

            result.forEach(role -> System.out.println(role.getName()));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
