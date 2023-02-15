package com.fragile.blog_api.entities;

import com.fragile.blog_api.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    // PLEASE CHANGE YOUR DATABASE CONFIGURATION BACK TO jdbc:mysql://localhost:3306/blog_api_db
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name ="user_name" , nullable = false, length = 100)
    private String name;

    @Column( nullable = false )
    private String email;

    private String password;

    private String about;

    @OneToMany(mappedBy="user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Post> posts = new ArrayList<Post>();



}
