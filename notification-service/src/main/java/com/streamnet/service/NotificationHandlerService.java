package com.streamnet.service;

import com.streamnet.commons.event.*;

public interface NotificationHandlerService {

    void handleListsNotification(ListsNotificationEvent notificationEvent);

    void handleFollowUserNotification(FollowUserNotificationEvent notificationEvent);

    void handleTweetNotification(TweetNotificationEvent notificationEvent);

    void handleTweetSubscriberNotification(TweetSubscriberNotificationEvent notificationEvent);

    void handleTweetMentionNotification(TweetMentionNotificationEvent notificationEvent);
}
