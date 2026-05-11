package com.streamnet.service;

import com.streamnet.commons.event.UpdateTweetCountEvent;

public interface UserUpdateTweetCountService {

    void handleUpdateTweetCount(UpdateTweetCountEvent tweetCountEvent, String authId);

    void handleUpdateLikeTweetCount(UpdateTweetCountEvent tweetCountEvent, String authId);

    void handleUpdateMediaTweetCount(UpdateTweetCountEvent tweetCountEvent, String authId);
}
