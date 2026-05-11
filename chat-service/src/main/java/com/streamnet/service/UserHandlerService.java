package com.streamnet.service;

import com.streamnet.commons.event.BlockUserEvent;
import com.streamnet.commons.event.FollowRequestUserEvent;
import com.streamnet.commons.event.FollowUserEvent;
import com.streamnet.commons.event.UpdateUserEvent;
import com.streamnet.model.User;

public interface UserHandlerService {

    User handleNewOrUpdateUser(UpdateUserEvent updateUserEvent);

    void handleBlockUser(BlockUserEvent blockUserEvent, String authId);

    void handleFollowUser(FollowUserEvent followUserEvent, String authId);

    void handleFollowUserRequest(FollowRequestUserEvent followRequestUserEvent, String authId);
}
