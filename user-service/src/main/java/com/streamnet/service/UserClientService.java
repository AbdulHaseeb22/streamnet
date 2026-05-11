package com.streamnet.service;

import com.streamnet.commons.dto.response.notification.NotificationUserResponse;
import com.streamnet.commons.dto.response.user.UserResponse;
import com.streamnet.commons.event.UpdateUserEvent;

import java.util.List;

public interface UserClientService {

    UserResponse getUserResponseById(Long userId);

    List<NotificationUserResponse> getUsersWhichUserSubscribed();

    List<Long> getUserIdsWhichUserSubscribed();

    List<UpdateUserEvent> getBatchUsers(Integer period, Integer page, Integer limit);
}
