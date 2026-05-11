package com.streamnet.broker.producer;

import com.streamnet.commons.constants.KafkaTopicConstants;
import com.streamnet.commons.enums.NotificationType;
import com.streamnet.commons.event.TweetMentionNotificationEvent;
import com.streamnet.commons.event.TweetNotificationEvent;
import com.streamnet.commons.event.TweetSubscriberNotificationEvent;
import com.streamnet.mapper.TweetNotificationMapper;
import com.streamnet.model.Tweet;
import com.streamnet.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TweetNotificationProducer {

    private final KafkaTemplate<String, TweetNotificationEvent> tweetNotificationTemplate;
    private final KafkaTemplate<String, TweetSubscriberNotificationEvent> tweetSubscriberNotificationTemplate;
    private final KafkaTemplate<String, TweetMentionNotificationEvent> tweetMentionNotificationTemplate;
    private final TweetNotificationMapper mapper;

    public void sendTweetNotificationEvent(NotificationType type, Tweet tweet, User authUser, boolean condition) {
        TweetNotificationEvent event = mapper.toTweetNotificationEvent(type, tweet, authUser, condition);
        tweetNotificationTemplate.send(KafkaTopicConstants.SEND_TWEET_NOTIFICATION_TOPIC, event);
    }

    public void sendTweetSubscriberNotificationEvent(Tweet tweet, User authUser) {
        TweetSubscriberNotificationEvent event = mapper.toTweetSubscriberNotificationEvent(tweet, authUser);
        tweetSubscriberNotificationTemplate.send(KafkaTopicConstants.SEND_SUBSCRIBER_NOTIFICATION_TOPIC, event);
    }

    public void sendTweetMentionNotificationEvent(NotificationType notificationType, Tweet tweet, User user, User authUser) {
        TweetMentionNotificationEvent event = mapper.toTweetMentionNotificationEvent(notificationType, tweet, user, authUser);
        tweetMentionNotificationTemplate.send(KafkaTopicConstants.SEND_TWEET_MENTION_NOTIFICATION_TOPIC, event);
    }
}
