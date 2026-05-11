package com.streamnet.service.impl;

import com.streamnet.broker.producer.TweetNotificationProducer;
import com.streamnet.commons.enums.NotificationType;
import com.streamnet.model.LikeTweet;
import com.streamnet.model.Tweet;
import com.streamnet.model.User;
import com.streamnet.broker.producer.UpdateTweetCountProducer;
import com.streamnet.repository.LikeTweetRepository;
import com.streamnet.repository.TweetRepository;
import com.streamnet.repository.projection.LikeTweetProjection;
import com.streamnet.repository.projection.UserProjection;
import com.streamnet.service.LikeTweetService;
import com.streamnet.service.UserService;
import com.streamnet.service.util.TweetValidationHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LikeTweetServiceImpl implements LikeTweetService {

    private final LikeTweetRepository likeTweetRepository;
    private final TweetRepository tweetRepository;
    private final TweetValidationHelper tweetValidationHelper;
    private final UpdateTweetCountProducer tweetCountProducer;
    private final TweetNotificationProducer tweetNotificationProducer;
    private final UserService userService;

    @Override
    @Transactional(readOnly = true)
    public Page<LikeTweetProjection> getUserLikedTweets(Long userId, Pageable pageable) {
        tweetValidationHelper.validateUserProfile(userId);
        return likeTweetRepository.getUserLikedTweets(userId, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UserProjection> getLikedUsersByTweetId(Long tweetId, Pageable pageable) {
        Tweet tweet = tweetValidationHelper.checkValidTweet(tweetId);
        return userService.getLikedUsersByTweet(tweet, pageable);
    }

    @Override
    @Transactional
    public Tweet likeTweet(Long tweetId) {
        Tweet tweet = tweetValidationHelper.checkValidTweet(tweetId);
        User authUser = userService.getAuthUser();
        LikeTweet likedTweet = likeTweetRepository.getLikedTweet(authUser, tweet);
        boolean isTweetLiked;

        if (likedTweet != null) {
            likeTweetRepository.delete(likedTweet);
            isTweetLiked = false;
        } else {
            LikeTweet newLikeTweet = new LikeTweet(authUser, tweet);
            likeTweetRepository.save(newLikeTweet);
            isTweetLiked = true;
        }
        tweetRepository.updateLikesCount(isTweetLiked, tweet);
        tweetCountProducer.sendUpdateLikeTweetCountEvent(authUser.getId(), isTweetLiked);
        tweetNotificationProducer.sendTweetNotificationEvent(NotificationType.LIKE, tweet, authUser, isTweetLiked);
        return tweet;
    }
}
