package com.example.bikash.Social.Media.Controllers;

import com.example.bikash.Social.Media.Entittes.Message;
import com.example.bikash.Social.Media.Services.ServiceImplementation.MessageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {


    @Autowired
    private MessageServiceImpl messageService;

    // create messagge
@PostMapping("/create/{chatId}")
    public ResponseEntity<Message>createMessage(@RequestBody Message message, @PathVariable("chatId") Long chatId)
    {
        Message message1 =  this.messageService.crateMessage(chatId,message);
        return new ResponseEntity<>(message1, HttpStatus.CREATED);

    }


    @GetMapping("/all/{chatId}")
    public  ResponseEntity<List<Message>> getAllMessages(@PathVariable("chatId") Long chatId)
    {

        List <Message> messages = this.messageService.findByChatId(chatId);
        return  new ResponseEntity<>(messages,HttpStatus.OK);

    }

}
