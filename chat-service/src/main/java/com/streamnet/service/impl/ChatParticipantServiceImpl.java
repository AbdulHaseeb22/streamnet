package com.streamnet.service.impl;

import com.streamnet.commons.exception.ApiRequestException;
import com.streamnet.constants.ChatErrorMessage;
import com.streamnet.constants.ChatSuccessMessage;
import com.streamnet.model.Chat;
import com.streamnet.model.ChatParticipant;
import com.streamnet.model.User;
import com.streamnet.repository.ChatParticipantRepository;
import com.streamnet.repository.ChatRepository;
import com.streamnet.repository.projection.UserChatProjection;
import com.streamnet.repository.projection.UserProjection;
import com.streamnet.service.ChatParticipantService;
import com.streamnet.service.UserService;
import com.streamnet.commons.util.AuthUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ChatParticipantServiceImpl implements ChatParticipantService {

    private final ChatRepository chatRepository;
    private final ChatParticipantRepository chatParticipantRepository;
    private final UserService userService;

    @Override
    @Transactional(readOnly = true)
    public UserProjection getParticipant(Long participantId, Long chatId) {
        if (!chatRepository.isChatExists(chatId, AuthUtil.getAuthenticatedUserId())) {
            throw new ApiRequestException(ChatErrorMessage.CHAT_NOT_FOUND, HttpStatus.NOT_FOUND);
        }
        User user = chatParticipantRepository.getChatParticipant(participantId, chatId)
                .orElseThrow(() -> new ApiRequestException(ChatErrorMessage.CHAT_PARTICIPANT_NOT_FOUND, HttpStatus.NOT_FOUND))
                .getUser();
        return userService.getUserProjectionById(user.getId());
    }

    @Override
    @Transactional
    public String leaveFromConversation(Long participantId, Long chatId) {
        Chat chat = chatRepository.findById(chatId)
                .orElseThrow(() -> new ApiRequestException(ChatErrorMessage.CHAT_NOT_FOUND, HttpStatus.NOT_FOUND));
        ChatParticipant chatParticipant = chatParticipantRepository.getChatParticipant(participantId, chatId)
                .orElseThrow(() -> new ApiRequestException(ChatErrorMessage.CHAT_PARTICIPANT_NOT_FOUND, HttpStatus.NOT_FOUND));
        chatParticipant.setLeftChat(true);

        if (chat.getParticipants().stream().allMatch(ChatParticipant::isLeftChat)) {
            chatRepository.delete(chat);
            return ChatSuccessMessage.CHAT_SUCCESSFULLY_DELETED;
        }
        return ChatSuccessMessage.SUCCESSFULLY_LEFT_THE_CHAT;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UserChatProjection> searchUsersByUsername(String username, Pageable pageable) {
        return userService.searchUsersByUsername(username, pageable);
    }
}
