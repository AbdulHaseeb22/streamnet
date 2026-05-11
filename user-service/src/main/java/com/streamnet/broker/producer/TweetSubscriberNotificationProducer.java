package com.streamnet.broker.producer;

import com.streamnet.commons.constants.KafkaTopicConstants;
import com.streamnet.commons.event.TweetSubscriberNotificationEvent;
import com.streamnet.mapper.ProducerMapper;
import com.streamnet.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TweetSubscriberNotificationProducer {

    private final KafkaTemplate<String, TweetSubscriberNotificationEvent> kafkaTemplate;
    private final ProducerMapper producerMapper;

    public void sendTweetSubscriberNotificationEvent(TweetSubscriberNotificationEvent notificationEvent, List<User> subscribers) {
        TweetSubscriberNotificationEvent event = producerMapper.toTweetSubscriberNotificationEvent(notificationEvent, subscribers);
        kafkaTemplate.send(KafkaTopicConstants.SEND_TWEET_SUBSCRIBER_NOTIFICATION_TOPIC, event);
    }
}
