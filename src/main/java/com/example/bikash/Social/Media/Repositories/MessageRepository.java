package com.example.bikash.Social.Media.Repositories;

import com.example.bikash.Social.Media.Entittes.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository  extends JpaRepository<Message,Long> {

    public List<Message>findByChatId(Long chatId);

}
