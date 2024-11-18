package com.example.bikash.Social.Media.Controllers;

import com.example.bikash.Social.Media.Entittes.Story;
import com.example.bikash.Social.Media.Services.ServiceImplementation.StoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/stories")
public class StoryController {

    @Autowired
    private StoryServiceImpl storyService;


    @PostMapping("/create")
    public ResponseEntity<Story> createStory(@RequestBody Story story) {
        Story createdStory = storyService.createStory(story);
      return  new ResponseEntity<>(createdStory, HttpStatus.CREATED);
    }

    @GetMapping("/user/stories")
    public ResponseEntity<List<Story>> getStoriesByUser() {
        List<Story> userStories = storyService.findStoryByUserId();
        return  new ResponseEntity<>(userStories, HttpStatus.OK);
    }

}