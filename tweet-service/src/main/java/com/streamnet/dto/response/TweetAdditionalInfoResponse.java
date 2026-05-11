package com.streamnet.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.streamnet.commons.dto.response.tweet.TweetAdditionalInfoUserResponse;
import com.streamnet.commons.enums.ReplyType;
import lombok.Data;

@Data
public class TweetAdditionalInfoResponse {
    private String text;
    private ReplyType replyType;
    private Long addressedTweetId;
    private TweetAdditionalInfoUserResponse author;
    @JsonProperty("isDeleted")
    private boolean isDeleted;
}
