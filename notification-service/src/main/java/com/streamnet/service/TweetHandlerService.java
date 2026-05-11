package com.streamnet.service;

import com.streamnet.commons.event.TweetNotificationDto;
import com.streamnet.commons.event.UpdateTweetEvent;
import com.streamnet.model.Tweet;

public interface TweetHandlerService {

    void handleUpdateTweet(UpdateTweetEvent tweetEvent);

    Tweet getOrCreateTweet(TweetNotificationDto tweet);
}
