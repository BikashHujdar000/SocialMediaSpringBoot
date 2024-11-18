package com.example.bikash.Social.Media.Services;

import com.example.bikash.Social.Media.Entittes.Chat;
import com.example.bikash.Social.Media.Entittes.User;

import java.util.List;

public interface ChatService {


    // 2 user needed one will give by authentication
       Chat createChat(User user);

       Chat findChatById(Long chatId);


     //  It will required on user Id
       List<Chat> findUsersChat();


}
