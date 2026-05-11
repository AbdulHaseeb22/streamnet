package com.streamnet.service.impl;

import com.streamnet.commons.dto.request.IdsRequest;
import com.streamnet.commons.dto.response.tweet.TweetResponse;
import com.streamnet.commons.exception.ApiRequestException;
import com.streamnet.client.TweetClient;
import com.streamnet.constants.TagErrorMessage;
import com.streamnet.model.Tag;
import com.streamnet.repository.TagRepository;
import com.streamnet.repository.TweetTagRepository;
import com.streamnet.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;
    private final TweetTagRepository tweetTagRepository;
    private final TweetClient tweetClient;

    @Override
    public List<Tag> getTags() {
        return tagRepository.findTop5ByOrderByTweetsQuantityDesc();
    }

    @Override
    public Page<Tag> getTrends(Pageable pageable) {
        return tagRepository.findByOrderByTweetsQuantityDesc(pageable);
    }

    @Override
    public List<TweetResponse> getTweetsByTag(String tagName) {
        Tag tag = tagRepository.findByTagName(tagName)
                .orElseThrow(() -> new ApiRequestException(TagErrorMessage.TAG_NOT_FOUND, HttpStatus.NOT_FOUND));
        List<Long> tweetIds = tweetTagRepository.getTweetIdsByTagId(tag.getId());
        return tweetClient.getTweetsByIds(new IdsRequest(tweetIds));
    }
}
