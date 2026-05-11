package com.streamnet.mapper;

import com.streamnet.commons.dto.HeaderResponse;
import com.streamnet.commons.dto.response.notification.NotificationTweetResponse;
import com.streamnet.commons.dto.response.tweet.TweetResponse;
import com.streamnet.commons.dto.response.user.UserResponse;
import com.streamnet.commons.mapper.BasicMapper;
import com.streamnet.model.Tweet;
import com.streamnet.repository.projection.TweetUserProjection;
import com.streamnet.repository.projection.UserProjection;
import com.streamnet.service.RetweetService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RetweetMapper {

    private final BasicMapper basicMapper;
    private final RetweetService retweetService;

    public HeaderResponse<TweetResponse> getUserRetweetsAndReplies(Long userId, Pageable pageable) {
        Page<TweetUserProjection> tweets = retweetService.getUserRetweetsAndReplies(userId, pageable);
        return basicMapper.getHeaderResponse(tweets, TweetResponse.class);
    }

    public HeaderResponse<UserResponse> getRetweetedUsersByTweetId(Long tweetId, Pageable pageable) {
        Page<UserProjection> users = retweetService.getRetweetedUsersByTweetId(tweetId, pageable);
        return basicMapper.getHeaderResponse(users, UserResponse.class);
    }

    public NotificationTweetResponse retweet(Long tweetId) {
        Tweet tweet = retweetService.retweet(tweetId);
        return basicMapper.convertToResponse(tweet, NotificationTweetResponse.class);
    }
}
