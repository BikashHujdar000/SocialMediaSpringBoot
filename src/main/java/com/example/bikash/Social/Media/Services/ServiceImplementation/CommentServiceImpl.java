package com.example.bikash.Social.Media.Services.ServiceImplementation;

import com.example.bikash.Social.Media.Entittes.Comment;
import com.example.bikash.Social.Media.Entittes.Post;
import com.example.bikash.Social.Media.Entittes.User;
import com.example.bikash.Social.Media.Exceptions.ResourceNotFoundException;
import com.example.bikash.Social.Media.Exceptions.UnauthorizedTaskException;
import com.example.bikash.Social.Media.Repositories.CommentRepository;
import com.example.bikash.Social.Media.Repositories.PostRepository;
import com.example.bikash.Social.Media.Services.CommentService;
import com.example.bikash.Social.Media.Utils.GetAuthenticatedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private GetAuthenticatedUser currentUser;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;


    @Override
    public Comment createComment(Comment comment, Long postID) {

        User user = this.currentUser.getAuthenticatedUser();
        Post post = this.postRepository.findById(postID).orElseThrow(() -> new ResourceNotFoundException("post", "postId", postID));

        Comment comment1 = new Comment();
        comment1.setComment(comment.getComment());
        comment1.setUser(user);
        comment1.setPost(post);
        comment1.setCreatedAt(LocalDateTime.now());
        Comment savedComment = this.commentRepository.save(comment1);
        return savedComment;

    }

    @Override
    public Comment getCommentById(Long commentId) {


        Comment comment = this.commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "CommentID", commentId));
        return comment;
    }

    @Override
    public Comment likeComment(Long commentId) {


        User user = this.currentUser.getAuthenticatedUser();
        Comment comment = this.commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "CommentID", commentId));
        if (comment.getLiked().contains(user)) {
            comment.getLiked().remove(user);
        } else {
            comment.getLiked().add(user);
        }
        return this.commentRepository.save(comment);
    }

    @Override
    public void DeleteComment(long commentId) {


        // i have to check that the user  created the commemnt only allowed to delete
        User user = this.currentUser.getAuthenticatedUser();
        Comment comment1 = this.commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "CommentID", commentId));

        if (user.getId() != comment1.getUser().getId()) {
            throw new UnauthorizedTaskException("You are not allowed to update  this operation");
        }
        this.commentRepository.delete(comment1);

    }

    @Override
    public Comment updateComment(Comment comment, Long commentId) {


        // i will have to make sure that only comment owner can update the comment
        User user = this.currentUser.getAuthenticatedUser();
        Comment comment1 = this.commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "CommentID", commentId));

        if (user.getId() != comment1.getUser().getId()) {
            throw new UnauthorizedTaskException("You are not allowed to update  this operation");
        }
        comment1.setComment(comment.getComment());
        Comment updatedComment = this.commentRepository.save(comment1);
        return updatedComment;
    }
}
