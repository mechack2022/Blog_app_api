package com.fragile.blog_api.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name ="comments")
public class Comment {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int commentId;
    private String content;
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinColumn(name = "post_id")
    private Post post;


}
