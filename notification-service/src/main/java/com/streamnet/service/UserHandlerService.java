package com.streamnet.service;

import com.streamnet.commons.event.UpdateUserEvent;
import com.streamnet.commons.event.UserNotificationDto;
import com.streamnet.model.User;

public interface UserHandlerService {

    User handleNewOrUpdateUser(UpdateUserEvent userEvent);

    User getOrCreateUser(UserNotificationDto user);
}
