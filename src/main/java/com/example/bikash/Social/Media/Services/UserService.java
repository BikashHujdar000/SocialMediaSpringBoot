package com.example.bikash.Social.Media.Services;

import com.example.bikash.Social.Media.Entittes.User;

import java.util.List;

public interface  UserService {

     User registerUser(User user);

     User getUserById(Long userId);

     User updateUser(User user , Long userId);

     void deleteUser(Long userId);

     List<User>  getALlUsers();

     User  followUser(Long userId1, Long userId2);

     List<User> searchUsers(String query);




}
