package com.streamnet.service;

public interface TagHandlerService {

    void parseHashtag(Long tweetId, String tweetText);

    void deleteTag(Long tweetId);
}
