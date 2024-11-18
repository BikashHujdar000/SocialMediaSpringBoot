package com.example.bikash.Social.Media.Repositories;

import com.example.bikash.Social.Media.Entittes.Chat;
import com.example.bikash.Social.Media.Entittes.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatRepository  extends JpaRepository<Chat,Long>
{

    @Query("SELECT c FROM Chat c JOIN c.users u WHERE u.id = :userId")
    public List<Chat> findByUserId(Long userId);


    // method to get one chat by passing two users

//
//    @Query("select c from Chat c Where user: Member  of c.users and :regUser Member of c.users")
//    public  Chat findChatByUsersId(@Param("user") User user , @Param("regUser") User regUser);

    @Query("SELECT c FROM Chat c WHERE :user MEMBER OF c.users AND :regUser MEMBER OF c.users")
    Chat findChatByUsersId(@Param("user") User user, @Param("regUser") User regUser);


}
