package com.streamnet.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.streamnet.commons.enums.TopicCategory;
import lombok.Data;

@Data
public class TopicResponse {
    private Long id;
    private String topicName;
    private TopicCategory topicCategory;

    @JsonProperty("isTopicFollowed")
    private boolean isTopicFollowed;

    @JsonProperty("isTopicNotInterested")
    private boolean isTopicNotInterested;
}
