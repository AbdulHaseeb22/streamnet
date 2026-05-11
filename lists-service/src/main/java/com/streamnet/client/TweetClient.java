package com.streamnet.client;

import com.streamnet.commons.configuration.FeignConfiguration;
import com.streamnet.commons.constants.FeignConstants;
import com.streamnet.commons.constants.PathConstants;
import com.streamnet.commons.dto.HeaderResponse;
import com.streamnet.commons.dto.response.tweet.TweetResponse;
import com.streamnet.commons.dto.request.IdsRequest;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CircuitBreaker(name = FeignConstants.TWEET_SERVICE)
@FeignClient(value = FeignConstants.TWEET_SERVICE, path = PathConstants.API_V1_TWEETS, configuration = FeignConfiguration.class)
public interface TweetClient {

    @PostMapping(PathConstants.USER_IDS)
    HeaderResponse<TweetResponse> getTweetsByUserIds(@RequestBody IdsRequest request,
                                                     @SpringQueryMap Pageable pageable);
}
