package com.streamnet.service;

import com.streamnet.commons.dto.response.tweet.TweetResponse;
import com.streamnet.model.Tweet;
import com.streamnet.repository.projection.TweetProjection;

import java.util.List;

public interface PollService {

    TweetResponse createPoll(Long pollDateTime, List<String> choices, Tweet tweet);

    TweetProjection voteInPoll(Long tweetId, Long pollId, Long pollChoiceId);
}
