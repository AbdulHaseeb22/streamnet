package com.streamnet.service;

import com.streamnet.commons.dto.response.tweet.TweetResponse;
import com.streamnet.model.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TagService {

    List<Tag> getTags();

    Page<Tag> getTrends(Pageable pageable);

    List<TweetResponse> getTweetsByTag(String tagName);
}
