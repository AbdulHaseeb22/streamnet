package com.streamnet.mapper;

import com.streamnet.commons.dto.HeaderResponse;
import com.streamnet.commons.mapper.BasicMapper;
import com.streamnet.dto.response.UserChatResponse;
import com.streamnet.commons.dto.response.user.UserResponse;
import com.streamnet.repository.projection.UserChatProjection;
import com.streamnet.repository.projection.UserProjection;
import com.streamnet.service.ChatParticipantService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChatParticipantMapper {

    private final BasicMapper basicMapper;
    private final ChatParticipantService chatParticipantService;

    public UserResponse getParticipant(Long participantId, Long chatId) {
        UserProjection participant = chatParticipantService.getParticipant(participantId, chatId);
        return basicMapper.convertToResponse(participant, UserResponse.class);
    }

    public String leaveFromConversation(Long participantId, Long chatId) {
        return chatParticipantService.leaveFromConversation(participantId, chatId);
    }

    public HeaderResponse<UserChatResponse> searchParticipantsByUsername(String username, Pageable pageable) {
        Page<UserChatProjection> participants = chatParticipantService.searchUsersByUsername(username, pageable);
        return basicMapper.getHeaderResponse(participants, UserChatResponse.class);
    }
}
