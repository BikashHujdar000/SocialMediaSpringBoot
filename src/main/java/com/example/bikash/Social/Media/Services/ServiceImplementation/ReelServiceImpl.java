package com.example.bikash.Social.Media.Services.ServiceImplementation;

import com.example.bikash.Social.Media.Entittes.Reel;
import com.example.bikash.Social.Media.Entittes.User;
import com.example.bikash.Social.Media.Exceptions.ResourceNotFoundException;
import com.example.bikash.Social.Media.Exceptions.UnauthorizedTaskException;
import com.example.bikash.Social.Media.Repositories.ReelsRepository;
import com.example.bikash.Social.Media.Services.ReelsService;
import com.example.bikash.Social.Media.Utils.GetAuthenticatedUser;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;

@Service
public class ReelServiceImpl implements ReelsService {

    @Autowired
    private GetAuthenticatedUser currentUser;


    @Autowired
    private ReelsRepository reelsRepository;


    @Override
    public Reel creteReel(Reel reel) {

        User user = this.currentUser.getAuthenticatedUser();
        Reel reel1 = new Reel();
        reel1.setUser(user);
        reel1.setCreatedAt(LocalDateTime.now());
        reel1.setTitle(reel.getTitle());
        reel1.setVideo(reel.getVideo());

        Reel savedReel = this.reelsRepository.save(reel1);

        return savedReel;

    }


    @Override
    public List<Reel> getAllReels() {


        List<Reel> reels = this.reelsRepository.findAll();

        return reels;
    }

    @Override
    public List<Reel> findByUser() {

        User user = this.currentUser.getAuthenticatedUser();

        List<Reel> reels = this.reelsRepository.findByUser(user);

        return reels;
    }

    @Override
    public Reel likeReel(Long reelId) {
        User user = this.currentUser.getAuthenticatedUser();
        Reel reel = this.reelsRepository.findById(reelId).orElseThrow(() -> new ResourceNotFoundException("Reel", "ReelId", reelId));
        if (reel.getLiked().contains(user)) {
            reel.getLiked().remove(user);
        } else {

            reel.getLiked().add(user);

        }

    return this.reelsRepository.save(reel);
    }
}
