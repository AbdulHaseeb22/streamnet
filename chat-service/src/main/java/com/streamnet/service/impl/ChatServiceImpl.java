package com.streamnet.service.impl;

import com.streamnet.commons.exception.ApiRequestException;
import com.streamnet.constants.ChatErrorMessage;
import com.streamnet.model.Chat;
import com.streamnet.model.ChatParticipant;
import com.streamnet.model.User;
import com.streamnet.repository.ChatRepository;
import com.streamnet.repository.projection.ChatProjection;
import com.streamnet.service.ChatService;
import com.streamnet.service.UserService;
import com.streamnet.commons.util.AuthUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final ChatRepository chatRepository;
    private final UserService userService;

    @Override
    @Transactional(readOnly = true)
    public ChatProjection getChatById(Long chatId) {
        Long authUserId = AuthUtil.getAuthenticatedUserId();
        return chatRepository.getChatById(chatId, authUserId, ChatProjection.class)
                .orElseThrow(() -> new ApiRequestException(ChatErrorMessage.CHAT_NOT_FOUND, HttpStatus.NOT_FOUND));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ChatProjection> getUserChats() {
        Long authUserId = AuthUtil.getAuthenticatedUserId();
        return chatRepository.getChatsByUserId(authUserId);
    }

    @Override
    @Transactional
    public ChatProjection createChat(Long userId) {
        User authUser = userService.getAuthUser();
        User user = userService.getUserById(userId);
        userService.isParticipantBlocked(authUser.getId(), user.getId());
        Chat chat = chatRepository.getChatByParticipants(authUser.getId(), user.getId());

        if (chat == null) {
            Chat newChat = new Chat();
            newChat.getParticipants().add(new ChatParticipant(newChat, authUser));
            newChat.getParticipants().add(new ChatParticipant(newChat, user));
            chatRepository.save(newChat);
            return chatRepository.getChatById(newChat.getId());
        }
        return chatRepository.getChatById(chat.getId());
    }

    public boolean isUserChatParticipant(Long userId) {
        Long authUserId = AuthUtil.getAuthenticatedUserId();
        return chatRepository.getChatByParticipants(authUserId, userId) != null;
    }
}
