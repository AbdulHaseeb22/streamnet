package com.streamnet.service;

import com.streamnet.repository.projection.ChatProjection;

import java.util.List;

public interface ChatService {

    ChatProjection getChatById(Long chatId);

    List<ChatProjection> getUserChats();

    ChatProjection createChat(Long userId);
}
