package com.example.bikash.Social.Media.Repositories;

import com.example.bikash.Social.Media.Entittes.Reel;
import com.example.bikash.Social.Media.Entittes.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReelsRepository  extends JpaRepository<Reel,Long> {

    List<Reel> findByUser(User user);

}
