package com.example.bikash.Social.Media.Controllers;

import com.example.bikash.Social.Media.Entittes.Comment;
import com.example.bikash.Social.Media.Responses.APiResponse;
import com.example.bikash.Social.Media.Services.ServiceImplementation.CommentServiceImpl;
import jakarta.persistence.PostRemove;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("comments")
public class CommentController {

    @Autowired
    private CommentServiceImpl commentService;

    // create comment

    @PostMapping("/create/post/{postId}")
    public ResponseEntity<Comment> createComment(@RequestBody Comment comment , @PathVariable("postId") Long postId)
    {

      Comment createdComment =    this.commentService.createComment(comment,postId);

        return  new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }

    @PutMapping("/like/{commentId}")
    public  ResponseEntity<Comment> LikeComment(@PathVariable ("commentId") Long commentId)
    {
        Comment likedComment =  this.commentService.likeComment(commentId);

        return  new ResponseEntity<>(likedComment,HttpStatus.OK);
    }



    // Update a comment
    @PutMapping("/update/{commentId}")
    public ResponseEntity<Comment> updateComment(@RequestBody Comment comment, @PathVariable("commentId") Long commentId) {
        Comment updatedComment = this.commentService.updateComment(comment, commentId);
        return new ResponseEntity<>(updatedComment, HttpStatus.OK);
    }

    // Delete a comment
    @DeleteMapping("/delete/{commentId}")
    public ResponseEntity<APiResponse> deleteComment(@PathVariable("commentId") Long commentId) {
        this.commentService.DeleteComment(commentId);
        APiResponse response = new APiResponse("Deleted Successfully",true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
