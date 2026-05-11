package com.streamnet.service.impl;

import com.streamnet.commons.event.UpdateTweetCountEvent;
import com.streamnet.commons.exception.ApiRequestException;
import com.streamnet.constants.UserErrorMessage;
import com.streamnet.model.User;
import com.streamnet.repository.UserRepository;
import com.streamnet.service.UserUpdateTweetCountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserUpdateTweetCountServiceImpl implements UserUpdateTweetCountService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public void handleUpdateTweetCount(UpdateTweetCountEvent tweetCountEvent, String authId) {
        User user = getUserById(Long.parseLong(authId));
        userRepository.updateTweetCount(tweetCountEvent.isUpdateTweetsCount(), user.getId());
    }

    @Override
    @Transactional
    public void handleUpdateLikeTweetCount(UpdateTweetCountEvent tweetCountEvent, String authId) {
        User user = getUserById(Long.parseLong(authId));
        userRepository.updateLikeCount(tweetCountEvent.isUpdateTweetsCount(), user.getId());
    }

    @Override
    @Transactional
    public void handleUpdateMediaTweetCount(UpdateTweetCountEvent tweetCountEvent, String authId) {
        User user = getUserById(Long.parseLong(authId));
        userRepository.updateMediaTweetCount(tweetCountEvent.isUpdateTweetsCount(), user.getId());
    }

    private User getUserById(Long userId) {
        return userRepository.getUserById(userId, User.class)
                .orElseThrow(() -> new ApiRequestException(UserErrorMessage.USER_NOT_FOUND, HttpStatus.NOT_FOUND));
    }
}
