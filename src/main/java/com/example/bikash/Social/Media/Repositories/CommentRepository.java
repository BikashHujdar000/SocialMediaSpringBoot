package com.example.bikash.Social.Media.Repositories;

import com.example.bikash.Social.Media.Entittes.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {

}
