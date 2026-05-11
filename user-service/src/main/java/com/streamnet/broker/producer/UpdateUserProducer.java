package com.streamnet.broker.producer;

import com.streamnet.commons.constants.KafkaTopicConstants;
import com.streamnet.commons.event.UpdateUserEvent;
import com.streamnet.mapper.ProducerMapper;
import com.streamnet.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateUserProducer {

    private final KafkaTemplate<String, UpdateUserEvent> kafkaTemplate;
    private final ProducerMapper producerMapper;

    public void sendUpdateUserEvent(User user) {
        UpdateUserEvent updateUserEvent = producerMapper.toUpdateUserEvent(user);
        kafkaTemplate.send(KafkaTopicConstants.UPDATE_USER_TOPIC, updateUserEvent);
    }
}
