package com.example.bikash.Social.Media.Controllers;

import com.example.bikash.Social.Media.Entittes.Post;
import com.example.bikash.Social.Media.Responses.APiResponse;
import com.example.bikash.Social.Media.Services.ServiceImplementation.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostServiceImpl postService;


    // 1.  create posts
    @PostMapping("/create")
    public ResponseEntity<Post> createPost(@RequestBody Post post) {

        Post createdPost = this.postService.createPost(post);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);

    }

    // 2.getpostBYId

    @GetMapping("/{postId}")
    public ResponseEntity<Post> getPostById(@PathVariable Long postId) {

        Post post = this.postService.getPostById(postId);
        return new ResponseEntity<>(post, HttpStatus.OK);

    }

    //3. updatePost
    @PutMapping("/update/{postId}")
    public ResponseEntity<Post> updatePost(@RequestBody Post post, @PathVariable Long postId) {

        Post updatedPost = this.postService.updataPost(post, postId);
        return new ResponseEntity<>(updatedPost, HttpStatus.CREATED);

    }

    //4.delete post
    @DeleteMapping("/delete/{postId}")
    public ResponseEntity<APiResponse> deletePost(@PathVariable Long postId) {

        this.postService.deletePost(postId);

        APiResponse response = new APiResponse("Deleted SuccessFully",true);
        return new ResponseEntity<>(response,HttpStatus.ACCEPTED);

    }

    //5. Get all post

    @GetMapping("/all")
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = this.postService.getAllPost();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }


// 6. get posts pf users

    @GetMapping("/user")
    public ResponseEntity<List<Post>> getPostsByUser() {

        List<Post> posts = this.postService.postsByUser();

        return new ResponseEntity<>(posts,HttpStatus.OK);
    }

    // 7. savePost by users
    @PutMapping ("/save/{postId}/{userId}")
    public ResponseEntity<Post> savePost(@PathVariable Long postId) {

        Post savedPost = this.postService.savedPost(postId );
        return new ResponseEntity<>(savedPost, HttpStatus.OK);

    }

    //8 . like post by user
    @PutMapping("/like/{postId}/{userId}")
    public ResponseEntity<Post> likePost(@PathVariable Long postId, @PathVariable Long userId) {

        Post updatedPost = this.postService.likePost(postId);
        return new ResponseEntity<>(updatedPost, HttpStatus.OK);
    }


}
