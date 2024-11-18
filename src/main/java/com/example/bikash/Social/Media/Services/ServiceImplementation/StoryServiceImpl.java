package com.example.bikash.Social.Media.Services.ServiceImplementation;

import com.example.bikash.Social.Media.Entittes.Story;
import com.example.bikash.Social.Media.Entittes.User;
import com.example.bikash.Social.Media.Repositories.StoryRepository;
import com.example.bikash.Social.Media.Services.StoryService;
import com.example.bikash.Social.Media.Utils.GetAuthenticatedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class StoryServiceImpl implements StoryService {

    @Autowired
    private StoryRepository storyRepository;

    @Autowired
    private GetAuthenticatedUser currentUser;


    @Override
    public Story createStory(Story story) {

        User user = this.currentUser.getAuthenticatedUser();

        Story story1  = new Story();
        story1.setCreatedAt(LocalDateTime.now());
        story1.setImage(story.getImage());
        story1.setCaption(story.getCaption());
        story1.setUser(user);

       return  this.storyRepository.save(story1);

    }

    @Override
    public List<Story> findStoryByUserId() {

        User user = this.currentUser.getAuthenticatedUser();

       List<Story> stories =  this.storyRepository.findByUserId(user.getId());

       return  stories;
    }
}
