package com.example.bikash.Social.Media.Repositories;

import com.example.bikash.Social.Media.Entittes.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository  extends JpaRepository<Post,Long> {

    // ig i snat to get all the post bu users then i  can get here

    @Query("select p from Post p where p.user.id =:userId")
    List<Post> findPostByUserId(Long userId);

}
