package com.example.bikash.Social.Media.DTOS;

import com.example.bikash.Social.Media.Entittes.Comment;
import com.example.bikash.Social.Media.Entittes.User;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {


    private Long id;
    private String caption;
    private String image;
    private String video;
    private LocalDateTime createdAt;
    private List<CommentDto> comments = new ArrayList<>();
    List<UserDto> liked = new ArrayList<>();

    private UserDto userDto;


}
