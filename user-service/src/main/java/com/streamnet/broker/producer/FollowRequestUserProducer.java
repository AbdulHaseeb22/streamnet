package com.streamnet.broker.producer;

import com.streamnet.broker.util.ProducerUtil;
import com.streamnet.commons.constants.KafkaTopicConstants;
import com.streamnet.commons.event.FollowRequestUserEvent;
import com.streamnet.mapper.ProducerMapper;
import com.streamnet.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FollowRequestUserProducer {

    private final KafkaTemplate<String, FollowRequestUserEvent> kafkaTemplate;
    private final ProducerMapper producerMapper;

    public void sendFollowRequestUserEvent(User user, Long authUserId, boolean hasUserFollowRequest) {
        FollowRequestUserEvent event = producerMapper.toFollowRequestUserEvent(user, hasUserFollowRequest);
        kafkaTemplate.send(ProducerUtil.authHeaderWrapper(KafkaTopicConstants.FOLLOW_REQUEST_USER_TOPIC, event, authUserId));
    }
}
