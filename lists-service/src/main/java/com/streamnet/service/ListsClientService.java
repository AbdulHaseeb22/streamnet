package com.streamnet.service;

import com.streamnet.commons.dto.response.tweet.TweetListResponse;

public interface ListsClientService {

    TweetListResponse getTweetList(Long listId);
}
