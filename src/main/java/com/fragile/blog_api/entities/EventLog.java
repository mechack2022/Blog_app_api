package com.fragile.blog_api.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "event_logs")
public class EventLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "event_name")
    private String eventName;

    @Column(name = "event_data")
    private String eventData;

    @Column(name = "event_time")
    private LocalDateTime eventTime;

    private Integer userId;
//    @OneToMany
//    @JoinColumn(name="user_id")
//    private List<User> user;


}

