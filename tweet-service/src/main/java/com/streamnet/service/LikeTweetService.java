package com.streamnet.service;

import com.streamnet.model.Tweet;
import com.streamnet.repository.projection.LikeTweetProjection;
import com.streamnet.repository.projection.UserProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LikeTweetService {

    Page<LikeTweetProjection> getUserLikedTweets(Long userId, Pageable pageable);

    Page<UserProjection> getLikedUsersByTweetId(Long tweetId, Pageable pageable);

    Tweet likeTweet(Long tweetId);
}
