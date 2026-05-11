package com.streamnet.mapper;

import com.streamnet.commons.dto.HeaderResponse;
import com.streamnet.commons.dto.response.notification.NotificationTweetResponse;
import com.streamnet.commons.dto.response.tweet.TweetResponse;
import com.streamnet.commons.dto.response.user.UserResponse;
import com.streamnet.commons.mapper.BasicMapper;
import com.streamnet.model.Tweet;
import com.streamnet.repository.projection.LikeTweetProjection;
import com.streamnet.repository.projection.TweetProjection;
import com.streamnet.repository.projection.UserProjection;
import com.streamnet.service.LikeTweetService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class LikeTweetMapper {

    private final BasicMapper basicMapper;
    private final LikeTweetService likeTweetService;

    public HeaderResponse<TweetResponse> getUserLikedTweets(Long userId, Pageable pageable) {
        Page<LikeTweetProjection> userLikedTweets = likeTweetService.getUserLikedTweets(userId, pageable);
        List<TweetProjection> tweets = new ArrayList<>();
        userLikedTweets.getContent().forEach(likeTweet -> tweets.add(likeTweet.getTweet()));
        return basicMapper.getHeaderResponse(tweets, userLikedTweets.getTotalPages(), TweetResponse.class);
    }

    public HeaderResponse<UserResponse> getLikedUsersByTweetId(Long tweetId, Pageable pageable) {
        Page<UserProjection> users = likeTweetService.getLikedUsersByTweetId(tweetId, pageable);
        return basicMapper.getHeaderResponse(users, UserResponse.class);
    }

    public NotificationTweetResponse likeTweet(Long tweetId) {
        Tweet tweet = likeTweetService.likeTweet(tweetId);
        return basicMapper.convertToResponse(tweet, NotificationTweetResponse.class);
    }
}
