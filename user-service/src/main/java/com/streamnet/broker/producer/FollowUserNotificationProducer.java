package com.streamnet.broker.producer;

import com.streamnet.commons.constants.KafkaTopicConstants;
import com.streamnet.commons.event.FollowUserNotificationEvent;
import com.streamnet.mapper.ProducerMapper;
import com.streamnet.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FollowUserNotificationProducer {

    private final KafkaTemplate<String, FollowUserNotificationEvent> kafkaTemplate;
    private final ProducerMapper producerMapper;

    public void sendFollowUserNotificationEvent(User authUser, User notifiedUser) {
        FollowUserNotificationEvent event = producerMapper.toUserNotificationEvent(authUser, notifiedUser);
        kafkaTemplate.send(KafkaTopicConstants.SEND_USER_NOTIFICATION_TOPIC, event);
    }
}
