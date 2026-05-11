package com.streamnet.service;

import com.streamnet.commons.dto.response.notification.NotificationUserResponse;
import com.streamnet.commons.dto.response.tweet.TweetResponse;
import com.streamnet.repository.projection.NotificationInfoProjection;
import com.streamnet.repository.projection.NotificationProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NotificationService {

    Page<NotificationProjection> getUserNotifications(Pageable pageable);

    Page<TweetResponse> getUserMentionsNotifications(Pageable pageable);

    List<NotificationUserResponse> getTweetAuthorsNotifications();

    NotificationInfoProjection getUserNotificationById(Long notificationId);

    Page<TweetResponse> getNotificationsFromTweetAuthors(Pageable pageable);
}
