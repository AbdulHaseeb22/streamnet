package com.streamnet.service.util;

import com.streamnet.client.TweetClient;
import com.streamnet.client.UserClient;
import com.streamnet.commons.dto.response.tweet.TweetResponse;
import com.streamnet.commons.dto.response.user.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationServiceHelper {

    private final UserClient userClient;
    private final TweetClient tweetClient;

    public UserResponse getUserById(Long userId) {
        return userClient.getUserById(userId);
    }

    public TweetResponse getTweetById(Long tweetId) {
        return tweetClient.getTweetById(tweetId);
    }
}
