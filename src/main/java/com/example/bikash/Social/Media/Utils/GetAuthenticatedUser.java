package com.example.bikash.Social.Media.Utils;

import com.example.bikash.Social.Media.Entittes.User;
import com.example.bikash.Social.Media.Exceptions.DuplicationItemException;
import com.example.bikash.Social.Media.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class GetAuthenticatedUser {

    @Autowired
    private  UserRepository userRepository;

    public User getAuthenticatedUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new DuplicationItemException("User", "email", email));
    }

}
