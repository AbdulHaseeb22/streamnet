package com.streamnet.mapper;

import com.streamnet.commons.dto.response.notification.NotificationResponse;
import com.streamnet.commons.event.TweetNotificationEvent;
import com.streamnet.commons.mapper.BasicMapper;
import com.streamnet.model.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationHandlerMapper {

    private final BasicMapper basicMapper;

    public NotificationResponse convertToNotificationListResponse(Notification notification, boolean isAddedToList) {
        NotificationResponse notificationResponse = basicMapper.convertToResponse(notification, NotificationResponse.class);
        notificationResponse.setAddedToList(isAddedToList);
        return notificationResponse;
    }

    public NotificationResponse convertToNotificationUserResponse(Notification notification, boolean isFollowed) {
        NotificationResponse notificationResponse = basicMapper.convertToResponse(notification, NotificationResponse.class);
        notificationResponse.getUserToFollow().setFollower(isFollowed);
        return notificationResponse;
    }

    public NotificationResponse convertToNotificationTweetResponse(Notification notification, boolean isTweetLiked) {
        NotificationResponse notificationResponse = basicMapper.convertToResponse(notification, NotificationResponse.class);
        notificationResponse.getTweet().setNotificationCondition(isTweetLiked);
        return notificationResponse;
    }

    public NotificationResponse convertToNotificationTweetResponse(TweetNotificationEvent event) {
        NotificationResponse notificationResponse = basicMapper.convertToResponse(event, NotificationResponse.class);
        notificationResponse.getTweet().setNotificationCondition(event.isNotificationCondition());
        return notificationResponse;
    }
}
