package com.example.bikash.Social.Media.Services;

import com.example.bikash.Social.Media.Entittes.Story;

import java.util.List;

public interface StoryService {

    Story createStory(Story story);

    List<Story>findStoryByUserId();

}
