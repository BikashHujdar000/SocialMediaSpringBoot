package com.example.bikash.Social.Media.Entittes;

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
public class Chat {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private  String  chatImage;

    private  String chatName;



    @ManyToMany
    private List<User> users = new ArrayList<>();


    private LocalDateTime createdAt;



}
