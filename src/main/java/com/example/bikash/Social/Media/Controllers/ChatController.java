package com.example.bikash.Social.Media.Controllers;

import com.example.bikash.Social.Media.Entittes.Chat;
import com.example.bikash.Social.Media.Entittes.CreateChatRequest;
import com.example.bikash.Social.Media.Entittes.User;

import com.example.bikash.Social.Media.Services.ServiceImplementation.ChatServiceImpl;
import com.example.bikash.Social.Media.Services.ServiceImplementation.UserServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/chat")
public class ChatController {



    @Autowired
    private ChatServiceImpl chatService;

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/create")
    public ResponseEntity<Chat> createChat(@RequestBody CreateChatRequest chatRequest) {

        User userX = userService.getUserById(chatRequest.getUserIdX());
        System.out.println(userX);
        Chat chat = this.chatService.createChat(userX);
        return new ResponseEntity<>(chat, HttpStatus.CREATED);



    }


    @GetMapping("/user")
    public ResponseEntity<List<Chat>> getUsersChat() {
        List<Chat> usersChat = this.chatService.findUsersChat();
        return new ResponseEntity<>(usersChat, HttpStatus.OK);

    }


    @GetMapping("/{chatId}")
    public ResponseEntity<Chat> getChatByChatId(@PathVariable("chatId") Long chatId) {

        Chat chat = this.chatService.findChatById(chatId);

        return new ResponseEntity<>(chat, HttpStatus.OK);

    }


}
