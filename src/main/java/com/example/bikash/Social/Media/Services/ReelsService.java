package com.example.bikash.Social.Media.Services;

import com.example.bikash.Social.Media.Entittes.Reel;
import com.example.bikash.Social.Media.Entittes.User;

import java.util.List;

public interface ReelsService {

    Reel creteReel(Reel reel);
// delete reels

 void delete( Long reelId);

    List<Reel> getAllReels();

    List<Reel>findByUser();

    Reel likeReel(Long reelId) ;


}
