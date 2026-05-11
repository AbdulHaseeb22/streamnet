package com.streamnet.service;

import com.streamnet.model.User;

import java.util.List;

public interface UserService {

    User getAuthUser();

    User getUserById(Long userId);

    List<User> searchListMembersByUsername(String username);

    void checkUserIsBlocked(Long userId, Long supposedBlockedUserId);

    void checkIsPrivateUserProfile(Long userId, Long authUserId);

    boolean isUserBlocked(Long userId, Long supposedBlockedUserId);

    boolean isUserHavePrivateProfile(Long userId, Long authUserId);
}
