package com.example.bikash.Social.Media.Repositories;

import com.example.bikash.Social.Media.Entittes.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    // method for query

    @Query("select u from User u where u.firstName LIKE %:query%  OR u.lastName LIKE %:query%")
    public List<User> searchUsers(@Param("query") String query);




    // method for finding by email
    Optional <User>  findByEmail(String email);



}
