package com.streamnet.service.impl;

import com.streamnet.commons.constants.ErrorMessage;
import com.streamnet.commons.exception.ApiRequestException;
import com.streamnet.model.User;
import com.streamnet.repository.UserRepository;
import com.streamnet.service.UserService;
import com.streamnet.commons.util.AuthUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User getAuthUser() {
        Long authUserId = AuthUtil.getAuthenticatedUserId();
        return userRepository.findById(authUserId)
                .orElseThrow(() -> new ApiRequestException(ErrorMessage.USER_NOT_FOUND, HttpStatus.UNAUTHORIZED));
    }

    @Override
    public void validateUserProfile(Long userId) {
        Long authUserId = AuthUtil.getAuthenticatedUserId();

        if (!userRepository.isUserExists(userId)) {
            throw new ApiRequestException(String.format(ErrorMessage.USER_ID_NOT_FOUND, userId), HttpStatus.NOT_FOUND);
        }

        if (!userId.equals(authUserId)) {
            if (userRepository.isUserBlocked(userId, authUserId)) {
                throw new ApiRequestException(ErrorMessage.USER_PROFILE_BLOCKED, HttpStatus.BAD_REQUEST);
            }
            if (!userRepository.isUserHavePrivateProfile(userId, authUserId)) {
                throw new ApiRequestException(ErrorMessage.USER_NOT_FOUND, HttpStatus.NOT_FOUND);
            }
        }
    }
}
