package com.example.bikash.Social.Media.Services;

import com.example.bikash.Social.Media.Entittes.Chat;
import com.example.bikash.Social.Media.Entittes.Message;

import java.util.List;

public interface MessageService {

    // create message
    // wht i need cht id and user user will be directly processed
    Message  crateMessage(Long chatId ,Message message );

    List<Message> findByChatId(Long chatId);




}
