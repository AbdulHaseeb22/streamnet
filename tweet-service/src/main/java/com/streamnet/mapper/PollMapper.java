package com.streamnet.mapper;

import com.streamnet.commons.dto.response.tweet.TweetResponse;
import com.streamnet.commons.mapper.BasicMapper;
import com.streamnet.dto.request.TweetRequest;
import com.streamnet.dto.request.VoteRequest;
import com.streamnet.model.Tweet;
import com.streamnet.repository.projection.TweetProjection;
import com.streamnet.service.PollService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PollMapper {

    private final BasicMapper basicMapper;
    private final PollService pollService;

    public TweetResponse createPoll(TweetRequest tweetRequest) {
        Tweet tweet = basicMapper.convertToResponse(tweetRequest, Tweet.class);
        return pollService.createPoll(tweetRequest.getPollDateTime(), tweetRequest.getChoices(), tweet);
    }

    public TweetResponse voteInPoll(VoteRequest voteRequest) {
        TweetProjection tweet = pollService.voteInPoll(voteRequest.getTweetId(), voteRequest.getPollId(),
                voteRequest.getPollChoiceId());
        return basicMapper.convertToResponse(tweet, TweetResponse.class);
    }
}
