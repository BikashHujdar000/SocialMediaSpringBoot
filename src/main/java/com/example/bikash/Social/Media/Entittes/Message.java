package com.example.bikash.Social.Media.Entittes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;


    private  String image;

    private LocalDateTime createdAt;

    @ManyToOne
    private  User user;


    @JsonIgnore
    @ManyToOne
    private  Chat chat;

}
