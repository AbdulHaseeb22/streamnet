package com.streamnet.service;

import com.streamnet.model.User;

public interface UserService {

    User getAuthUser();

    void validateUserProfile(Long userId);
}
