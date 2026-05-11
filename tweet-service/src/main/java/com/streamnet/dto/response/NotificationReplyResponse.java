package com.streamnet.dto.response;

import com.streamnet.commons.dto.response.tweet.TweetResponse;
import com.streamnet.commons.enums.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NotificationReplyResponse {
    private Long tweetId;
    private NotificationType notificationType;
    private TweetResponse tweet;
}
