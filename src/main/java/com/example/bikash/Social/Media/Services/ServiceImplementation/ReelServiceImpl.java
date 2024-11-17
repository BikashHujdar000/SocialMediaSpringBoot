package com.example.bikash.Social.Media.Services.ServiceImplementation;

import com.example.bikash.Social.Media.Entittes.Reel;
import com.example.bikash.Social.Media.Entittes.User;
import com.example.bikash.Social.Media.Exceptions.ResourceNotFoundException;
import com.example.bikash.Social.Media.Exceptions.UnauthorizedTaskException;
import com.example.bikash.Social.Media.Repositories.ReelsRepository;
import com.example.bikash.Social.Media.Services.ReelsService;
import com.example.bikash.Social.Media.Utils.GetAuthenticatedUser;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ReelServiceImpl implements ReelsService {

    @Autowired
    private GetAuthenticatedUser currentUser ;


    @Autowired
    private ReelsRepository reelsRepository ;



    @Override
    public Reel creteReel(Reel reel) {

        User user = this.currentUser.getAuthenticatedUser();


    }

    @Override
    public void deleteReels(Long reelId) {

        User user = this.currentUser.getAuthenticatedUser();

        Reel reel = this.reelsRepository.findById(reelId).orElseThrow(()-> new ResourceNotFoundException("Reel","ReelId",reelId));

        if (user != reel.getUser())
        {
            throw  new UnauthorizedTaskException("You are not allowed to delete  this reel");
        }

        this.reelsRepository.delete(reel);
    }

    @Override
    public List<Reel> getAllReels() {


        List<Reel> reels = this.reelsRepository.findAll();

        return  reels;
    }

    @Override
    public List<Reel> findByUser() {

        User user = this.currentUser.getAuthenticatedUser();

        List<Reel> reels = this.reelsRepository.findByUser(user);

        return  reels;
    }
}
