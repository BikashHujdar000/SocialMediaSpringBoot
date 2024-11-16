package com.example.bikash.Social.Media.Services.ServiceImplementation;

import com.example.bikash.Social.Media.Entittes.User;
import com.example.bikash.Social.Media.Exceptions.DuplicationItemException;
import com.example.bikash.Social.Media.Exceptions.ResourceNotFoundException;
import com.example.bikash.Social.Media.Repositories.UserRepository;
import com.example.bikash.Social.Media.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public User registerUser(User user) {

        Optional<User> existingUserOpt = userRepository.findByEmail(user.getEmail());

        if (existingUserOpt.isPresent()) {

            throw new DuplicationItemException("Email", "EmailId", user.getEmail());

        }
        // Save the new user if it doesn't exist
        this.userRepository.save(user);
        return user;
    }



    @Override
    public User getUserById(Long userId) {

        User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user", "userId", userId));
        return user;
    }

    @Override
    public User updateUser(User user, Long userId) {

        User newUser = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user", "userId", userId));
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setEmail(user.getEmail());
        newUser.setGender(user.getGender());

        this.userRepository.save(newUser);
        return newUser;

    }

    @Override
    public void deleteUser(Long userId) {

        User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user", "userId", userId));
        this.userRepository.delete(user);

    }

    @Override
    public List<User> getALlUsers() {

        List<User> users = this.userRepository.findAll();
        return users;
    }

    @Override
    public User followUser(Long userId1, Long userId2) {

        User user1 = this.userRepository.findById(userId1).orElseThrow(() -> new ResourceNotFoundException("user", "userId", userId1));

        User user2 = this.userRepository.findById(userId2).orElseThrow(() -> new ResourceNotFoundException("user", "userId", userId2));

        user1.getFollowings().add(user2.getId());

        user2.getFollowers().add(user1.getId());


        this.userRepository.save(user1);
        this.userRepository.save(user2);

        return user1;

    }

    @Override
    public List<User> searchUsers(String query) {
        if (query == null || query.trim().isEmpty()) {
            return Collections.emptyList();
        }

        // Otherwise, call the repository method to search for users
        return this.userRepository.searchUsers(query);


    }
}

