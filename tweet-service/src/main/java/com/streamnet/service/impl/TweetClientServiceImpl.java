package com.streamnet.service.impl;

import com.streamnet.commons.dto.request.IdsRequest;
import com.streamnet.model.Tweet;
import com.streamnet.repository.TweetRepository;
import com.streamnet.repository.projection.ChatTweetProjection;
import com.streamnet.repository.projection.TweetProjection;
import com.streamnet.service.TweetClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TweetClientServiceImpl implements TweetClientService {

    private final TweetRepository tweetRepository;

    @Override
    public List<TweetProjection> getTweetsByIds(IdsRequest requests) {
        return tweetRepository.getTweetListsByIds(requests.getIds());
    }

    @Override
    public Page<TweetProjection> getTweetsByUserIds(IdsRequest request, Pageable pageable) {
        return tweetRepository.getTweetsByAuthorIds(request.getIds(), pageable);
    }

    @Override
    public TweetProjection getTweetById(Long tweetId) {
        return tweetRepository.getTweetById(tweetId, TweetProjection.class).get();
    }

    @Override
    public Boolean isTweetExists(Long tweetId) {
        return tweetRepository.isTweetExists(tweetId);
    }

    @Override
    public Long getTweetCountByText(String text) {
        return tweetRepository.getTweetCountByText(text);
    }

    @Override
    public ChatTweetProjection getChatTweet(Long tweetId) {
        return tweetRepository.getTweetById(tweetId, ChatTweetProjection.class).get();
    }

    @Override
    public List<Tweet> getBatchTweets(Integer period, Integer page, Integer limit) {
        LocalDateTime sinceDate = LocalDateTime.now().minusDays(period);
        PageRequest pageable = PageRequest.of(page, limit);
        return tweetRepository.findByCreationAndUpdatedDate(sinceDate, pageable);
    }
}
