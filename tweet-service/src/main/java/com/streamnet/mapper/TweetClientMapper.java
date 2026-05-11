package com.streamnet.mapper;

import com.streamnet.commons.dto.response.chat.ChatTweetResponse;
import com.streamnet.commons.dto.HeaderResponse;
import com.streamnet.commons.dto.request.IdsRequest;
import com.streamnet.commons.dto.response.tweet.TweetResponse;
import com.streamnet.commons.event.UpdateTweetEvent;
import com.streamnet.commons.mapper.BasicMapper;
import com.streamnet.model.Tweet;
import com.streamnet.repository.projection.ChatTweetProjection;
import com.streamnet.repository.projection.TweetProjection;
import com.streamnet.service.TweetClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TweetClientMapper {

    private final BasicMapper basicMapper;
    private final TweetClientService tweetClientService;

    public List<TweetResponse> getTweetsByIds(IdsRequest requests) {
        List<TweetProjection> tweets = tweetClientService.getTweetsByIds(requests);
        return basicMapper.convertToResponseList(tweets, TweetResponse.class);
    }

    public HeaderResponse<TweetResponse> getTweetsByUserIds(IdsRequest request, Pageable pageable) {
        Page<TweetProjection> tweets = tweetClientService.getTweetsByUserIds(request, pageable);
        return basicMapper.getHeaderResponse(tweets, TweetResponse.class);
    }

    public TweetResponse getTweetById(Long tweetId) {
        TweetProjection tweet = tweetClientService.getTweetById(tweetId);
        return basicMapper.convertToResponse(tweet, TweetResponse.class);
    }

    public Boolean isTweetExists(Long tweetId) {
        return tweetClientService.isTweetExists(tweetId);
    }

    public Long getTweetCountByText(String text) {
        return tweetClientService.getTweetCountByText(text);
    }

    public ChatTweetResponse getChatTweet(Long tweetId) {
        ChatTweetProjection tweet = tweetClientService.getChatTweet(tweetId);
        return basicMapper.convertToResponse(tweet, ChatTweetResponse.class);
    }

    public List<UpdateTweetEvent> getBatchTweets(Integer period, Integer page, Integer limit) {
        List<Tweet> users = tweetClientService.getBatchTweets(period, page, limit);
        return basicMapper.convertToResponseList(users, UpdateTweetEvent.class);
    }
}
