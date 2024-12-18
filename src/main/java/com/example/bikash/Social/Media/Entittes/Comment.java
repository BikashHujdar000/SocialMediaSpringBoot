package com.example.bikash.Social.Media.Entittes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comment;

    private LocalDateTime createdAt;

    @ManyToMany
    private List<User> liked = new ArrayList<>();

    @ManyToOne
    private User user;

    @ManyToOne()
    @JsonIgnore
    private Post post;


}
