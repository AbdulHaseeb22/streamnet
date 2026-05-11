package com.streamnet.mapper;

import com.streamnet.commons.dto.HeaderResponse;
import com.streamnet.commons.mapper.BasicMapper;
import com.streamnet.dto.request.TweetDeleteRequest;
import com.streamnet.dto.request.TweetRequest;
import com.streamnet.commons.dto.response.tweet.TweetResponse;
import com.streamnet.model.Tweet;
import com.streamnet.repository.projection.TweetProjection;
import com.streamnet.service.ScheduledTweetService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ScheduledTweetMapper {

    private final BasicMapper basicMapper;
    private final ScheduledTweetService scheduledTweetService;

    public HeaderResponse<TweetResponse> getScheduledTweets(Pageable pageable) {
        Page<TweetProjection> tweets = scheduledTweetService.getScheduledTweets(pageable);
        return basicMapper.getHeaderResponse(tweets, TweetResponse.class);
    }

    public TweetResponse createScheduledTweet(TweetRequest tweetRequest) {
        TweetProjection tweet = scheduledTweetService.createScheduledTweet(basicMapper.convertToResponse(tweetRequest, Tweet.class));
        return basicMapper.convertToResponse(tweet, TweetResponse.class);
    }

    public TweetResponse updateScheduledTweet(TweetRequest tweetRequest) {
        TweetProjection tweet = scheduledTweetService.updateScheduledTweet(basicMapper.convertToResponse(tweetRequest, Tweet.class));
        return basicMapper.convertToResponse(tweet, TweetResponse.class);
    }

    public String deleteScheduledTweets(TweetDeleteRequest tweetRequest) {
        return scheduledTweetService.deleteScheduledTweets(tweetRequest.getTweetsIds());
    }
}
