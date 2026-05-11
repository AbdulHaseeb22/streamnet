package com.streamnet.client;

import com.streamnet.commons.configuration.FeignConfiguration;
import com.streamnet.commons.constants.FeignConstants;
import com.streamnet.commons.constants.PathConstants;
import com.streamnet.commons.dto.request.IdsRequest;
import com.streamnet.commons.dto.response.tweet.TweetResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@CircuitBreaker(name = FeignConstants.TWEET_SERVICE)
@FeignClient(name = FeignConstants.TWEET_SERVICE, path = PathConstants.API_V1_TWEETS, configuration = FeignConfiguration.class)
public interface TweetClient {

    @GetMapping(PathConstants.TWEET_ID)
    TweetResponse getTweetById(@PathVariable("tweetId") Long tweetId);

    @PostMapping(PathConstants.IDS)
    List<TweetResponse> getTweetsByIds(@RequestBody IdsRequest idsRequest);
}
