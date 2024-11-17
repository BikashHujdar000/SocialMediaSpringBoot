package com.example.bikash.Social.Media.DTOS;

import com.example.bikash.Social.Media.Domain.USER_ROLE;
import com.example.bikash.Social.Media.Entittes.Post;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String gender;
    private String role = String.valueOf(USER_ROLE.USER);
    private List<Long> followers = new ArrayList<>();
    private List<Long> followings = new ArrayList<>();
    private  List<PostDto> savedPost = new ArrayList<>();


}
