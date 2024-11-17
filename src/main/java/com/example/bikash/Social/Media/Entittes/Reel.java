package com.example.bikash.Social.Media.Entittes;


import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class Reel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String video;
    private LocalDateTime createdAt ;

    @ManyToMany
    private List<User> liked = new ArrayList<>();

    @OneToMany(mappedBy = "reel",cascade = CascadeType.ALL,orphanRemoval = true)
    private  List<ReelComment> comments = new ArrayList<>();

    @JsonBackReference
    @ManyToOne
    private  User user;


}




