package com.example.bikash.Social.Media.Services.ServiceImplementation;

import com.example.bikash.Social.Media.Entittes.Chat;
import com.example.bikash.Social.Media.Entittes.Message;
import com.example.bikash.Social.Media.Entittes.User;
import com.example.bikash.Social.Media.Repositories.ChatRepository;
import com.example.bikash.Social.Media.Repositories.MessageRepository;
import com.example.bikash.Social.Media.Services.MessageService;
import com.example.bikash.Social.Media.Utils.GetAuthenticatedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private GetAuthenticatedUser currentUser;

    @Autowired
    private ChatServiceImpl chatService;

    @Autowired
    private ChatRepository chatRepository;


    @Override
    public Message crateMessage(Long chatId, Message message) {


        Message newMessage = new Message();

        User user = currentUser.getAuthenticatedUser();
        Chat chat = this.chatService.findChatById(chatId);

        newMessage.setMessage(message.getMessage());
        newMessage.setUser(user);
        newMessage.setChat(chat);
        newMessage.setImage(message.getImage());
        newMessage.setCreatedAt(LocalDateTime.now());
        Message savedMessage = this.messageRepository.save(newMessage);


        chat.getMessages().add(savedMessage);

        chatRepository.save(chat);
        return savedMessage;
    }

    @Override
    public List<Message> findByChatId(Long chatId) {

        return this.messageRepository.findByChatId(chatId);


    }
}
