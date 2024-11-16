package com.example.bikash.Social.Media.Controllers;

import com.example.bikash.Social.Media.Entittes.User;
import com.example.bikash.Social.Media.Responses.APiResponse;
import com.example.bikash.Social.Media.Services.ServiceImplementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    //1. crate users

    @PostMapping("/register")
    public ResponseEntity<User> RegisterUser(@RequestBody User user) {
        User createdUser = this.userServiceImpl.registerUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    //2. geUser By ID

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable("userId") Long userId) {

        User user = this.userServiceImpl.getUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    //    3.  update User
    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable("userId") Long userId) {

        User updatreUser = this.userServiceImpl.updateUser(user, userId);
        return new ResponseEntity<>(updatreUser, HttpStatus.CREATED);

    }

    //    4. delete users
    @DeleteMapping("/{userId}")
    public ResponseEntity<APiResponse> deleteUser(@PathVariable("userId") Long userId) {

        this.userServiceImpl.getUserById(userId);
        APiResponse response = new APiResponse("User Deleated SucessFully", true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    //5.getALlusers

    @GetMapping("")
    public ResponseEntity<List<User>> getUsers() {

        List<User> users = this.userServiceImpl.getALlUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }


    //  6. follow user


    @GetMapping("follow/{userId1}/{userId2}")
    public ResponseEntity<User> followUser(@PathVariable("userId1") Long userId1, @PathVariable("userId2") Long userId2) {
        User user = this.userServiceImpl.followUser(userId1, userId2);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    // 7. find  users buy   query


    @GetMapping("/search")
    public ResponseEntity<List<User>> searchUsers(@RequestParam("query") String query) {
        List<User> users = this.userServiceImpl.searchUsers(query);
        return new ResponseEntity<>(users,HttpStatus.OK);
    }


}
