package com.streamnet.mapper;

import com.streamnet.commons.event.ListsNotificationDto;
import com.streamnet.commons.event.ListsNotificationEvent;
import com.streamnet.commons.event.UserNotificationDto;
import com.streamnet.commons.mapper.BasicMapper;
import com.streamnet.model.Lists;
import com.streamnet.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ListsProducerMapper {

    private final BasicMapper basicMapper;

    public ListsNotificationEvent toListsNotificationEvent(User notifiedUser, User user, Lists lists) {
        return ListsNotificationEvent.builder()
                .notificationCondition(true)
                .notifiedUser(toUserDto(notifiedUser))
                .user(toUserDto(user))
                .lists(toListsDto(lists))
                .build();
    }

    private UserNotificationDto toUserDto(User user) {
        return basicMapper.convertToResponse(user, UserNotificationDto.class);
    }

    private ListsNotificationDto toListsDto(Lists lists) {
        return basicMapper.convertToResponse(lists, ListsNotificationDto.class);
    }
}
