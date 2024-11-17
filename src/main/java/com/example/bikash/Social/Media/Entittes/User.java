package com.example.bikash.Social.Media.Entittes;


import com.example.bikash.Social.Media.Domain.USER_ROLE;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;

    @JsonIgnore
    private String password;
    private String gender;
    private String role = String.valueOf(USER_ROLE.USER);
    private List<Long> followers = new ArrayList<>();
    private List<Long> followings = new ArrayList<>();


    @JsonManagedReference
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)

    private  List<Reel>reels= new ArrayList<>();



    @JsonIgnore
    @OneToMany
    private  List<Post> savedPost = new ArrayList<>();


}
