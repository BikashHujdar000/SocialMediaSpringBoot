package com.example.bikash.Social.Media.Controllers;

import com.example.bikash.Social.Media.Entittes.Reel;
import com.example.bikash.Social.Media.Responses.APiResponse;
import com.example.bikash.Social.Media.Services.ReelsService;
import com.example.bikash.Social.Media.Services.ServiceImplementation.ReelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reels")
public class ReelController {

    @Autowired
private  ReelServiceImpl reelService;

    // create  reel
    @PostMapping("/create")
    public ResponseEntity<Reel> createReel(@RequestBody Reel reel) {
        Reel createdReel = this.reelService.creteReel(reel);
        return new ResponseEntity<>(createdReel, HttpStatus.CREATED);
    }




    // get all reels
    @GetMapping("/all")
    public ResponseEntity<List<Reel>> getAllReels() {
        List<Reel> reels = this.reelService.getAllReels();
        return new ResponseEntity<>(reels, HttpStatus.OK);
    }


    @GetMapping("/user")
    public ResponseEntity<List<Reel>> getReelsByUser() {
        List<Reel> userReels = this.reelService.findByUser();
        return new ResponseEntity<>(userReels, HttpStatus.OK);
    }

    // Like or unlike a reel
    @PutMapping("/like/{reelId}")
    public ResponseEntity<Reel> likeReel(@PathVariable Long reelId) {
        Reel likedReel = this.reelService.likeReel(reelId);
        return new ResponseEntity<>(likedReel, HttpStatus.OK);
    }
}

