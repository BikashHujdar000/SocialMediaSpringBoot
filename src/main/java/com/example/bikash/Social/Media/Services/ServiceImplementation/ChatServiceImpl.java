package com.example.bikash.Social.Media.Services.ServiceImplementation;

import com.example.bikash.Social.Media.Entittes.Chat;
import com.example.bikash.Social.Media.Entittes.User;
import com.example.bikash.Social.Media.Exceptions.ResourceNotFoundException;
import com.example.bikash.Social.Media.Repositories.ChatRepository;
import com.example.bikash.Social.Media.Services.ChatService;
import com.example.bikash.Social.Media.Utils.GetAuthenticatedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private GetAuthenticatedUser currentUser;


    @Override
    public Chat createChat(User user) {


        //upcomming user should be that user whom the origina user id creating a  chat

        User regUser = currentUser.getAuthenticatedUser();

        System.out.println(regUser);
        Chat isExist = chatRepository.findChatByUsersId(user,regUser);

        if (isExist != null) {
            return isExist;
        }

        {
            Chat newChat = new Chat();
            System.out.println("yaha pe aaya");
            newChat.getUsers().add(user);
            newChat.getUsers().add(regUser);
            newChat.setCreatedAt(LocalDateTime.now());

            return chatRepository.save(newChat);
        }


    }

    @Override
    public Chat findChatById(Long chatId) {

        Chat chat = chatRepository.findById(chatId).orElseThrow(() -> new ResourceNotFoundException("Chat", "chatId", chatId));
        return chat;
    }

    @Override
    public List<Chat> findUsersChat() {

        User user = currentUser.getAuthenticatedUser();
        List<Chat> usersChat = this.chatRepository.findByUserId(user.getId());
        return usersChat;

    }
}
