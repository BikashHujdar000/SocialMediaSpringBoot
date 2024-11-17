package com.example.bikash.Social.Media.Services;

import com.example.bikash.Social.Media.Entittes.Comment;

public interface CommentService  {

    Comment createComment(Comment comment , Long postID);

    Comment getCommentById(Long commentId);

    Comment likeComment(Long commentId);

    void  DeleteComment(long commentId);

    Comment updateComment(Comment comment , Long commentId);

}
