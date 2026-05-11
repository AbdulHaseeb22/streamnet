package com.streamnet.dto.response;

import com.streamnet.commons.dto.response.tweet.TweetResponse;
import com.streamnet.commons.dto.response.user.UserResponse;
import com.streamnet.commons.enums.NotificationType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NotificationInfoResponse {
    private Long id;
    private LocalDateTime date;
    private NotificationType notificationType;
    private UserResponse user;
    private TweetResponse tweet;
}
