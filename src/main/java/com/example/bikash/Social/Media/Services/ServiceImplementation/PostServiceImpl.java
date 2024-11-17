package com.example.bikash.Social.Media.Services.ServiceImplementation;

import com.example.bikash.Social.Media.Entittes.Post;
import com.example.bikash.Social.Media.Entittes.User;
import com.example.bikash.Social.Media.Exceptions.DuplicationItemException;
import com.example.bikash.Social.Media.Exceptions.ResourceNotFoundException;
import com.example.bikash.Social.Media.Exceptions.UnauthorizedTaskException;
import com.example.bikash.Social.Media.Repositories.PostRepository;
import com.example.bikash.Social.Media.Repositories.UserRepository;
import com.example.bikash.Social.Media.Services.PostService;
import com.example.bikash.Social.Media.Utils.GetAuthenticatedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private GetAuthenticatedUser getAuthenticatedUser;

    @Override
    public Post createPost(Post post) {

        User user = this.getAuthenticatedUser.getAuthenticatedUser();
        Post newPost = new Post();

        newPost.setCaption(post.getCaption());
        newPost.setImage(post.getImage());
        newPost.setVideo(post.getVideo());
        newPost.setCreatedAt(LocalDateTime.now());
        newPost.setUser(user);

        Post savePost = this.postRepository.save(newPost);

        return savePost;
    }

    @Override
    public Post getPostById(Long postId) {


        Post post = this.postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post", "postId", postId));
        return post;
    }

    @Override
    public Post updataPost(Post post, Long postId) {

        Post postFromDatabase = this.postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post", "postId", postId));


        postFromDatabase.setCaption(post.getCaption());
        postFromDatabase.setImage(post.getImage());
        postFromDatabase.setVideo(post.getVideo());
        postFromDatabase.setCreatedAt(LocalDateTime.now());

        Post updatedPost = this.postRepository.save(postFromDatabase);
        return updatedPost;


    }

    @Override
    public void deletePost(Long postId) {

        Post post = this.postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post", "postId", postId));

        this.postRepository.delete(post);

    }

    @Override
    public List<Post> getAllPost() {
        List<Post> posts = this.postRepository.findAll();
        return posts;
    }

    @Override
    public List<Post> postsByUser() {
        User user = this.getAuthenticatedUser.getAuthenticatedUser();

        List<Post> posts = this.postRepository.findPostByUserId(user.getId());

        return posts;
    }

    @Override
    public Post savedPost(Long postId) {
        User user = this.getAuthenticatedUser.getAuthenticatedUser();
        Post post = this.postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post", "postId", postId));

        if (!user.getSavedPost().contains(post)) {
            user.getSavedPost().add(post);
            userRepository.save(user);
            return post;
        } else {
            throw new UnauthorizedTaskException(
                    "You have already saved this post");
        }
    }

    @Override
    public Post likePost(Long postId) {
        User user = this.getAuthenticatedUser.getAuthenticatedUser();

        Post post = this.postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "PostId", postId));

        if (post.getLiked().contains(user)) {
            post.getLiked().remove(user);
            Post newPost = postRepository.save(post);
            return newPost;

        } else {
            post.getLiked().add(user);

            Post savedPost = this.postRepository.save(post);
            return savedPost;

        }

    }
}
