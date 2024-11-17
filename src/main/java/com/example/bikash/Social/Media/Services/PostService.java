package com.example.bikash.Social.Media.Services;

import com.example.bikash.Social.Media.Entittes.Post;

import java.util.List;

public interface PostService {

    Post createPost(Post post);

    Post getPostById(Long postId);

    Post updataPost(Post post, Long postId);

    void deletePost(Long postId);

    List<Post> getAllPost();

    List<Post> postsByUser();

    Post savedPost(Long postId);

    Post likePost(Long postId);

}
