package com.streamnet.broker.producer;

import com.streamnet.broker.util.ProducerUtil;
import com.streamnet.commons.constants.KafkaTopicConstants;
import com.streamnet.commons.event.FollowUserEvent;
import com.streamnet.mapper.ProducerMapper;
import com.streamnet.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FollowUserProducer {

    private final KafkaTemplate<String, FollowUserEvent> kafkaTemplate;
    private final ProducerMapper producerMapper;

    public void sendFollowUserEvent(User user, Long authUserId, boolean hasUserFollowed) {
        FollowUserEvent event = producerMapper.toFollowUserEvent(user, hasUserFollowed);
        kafkaTemplate.send(ProducerUtil.authHeaderWrapper(KafkaTopicConstants.FOLLOW_USER_TOPIC, event, authUserId));
    }
}
