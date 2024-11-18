package com.example.bikash.Social.Media.Repositories;

import com.example.bikash.Social.Media.Entittes.Story;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoryRepository extends JpaRepository<Story,Long> {

    public List<Story>findByUserId(Long userId);
}
