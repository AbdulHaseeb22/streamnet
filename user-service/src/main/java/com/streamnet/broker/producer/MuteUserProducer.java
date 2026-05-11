package com.streamnet.broker.producer;

import com.streamnet.broker.util.ProducerUtil;
import com.streamnet.commons.constants.KafkaTopicConstants;
import com.streamnet.commons.event.MuteUserEvent;
import com.streamnet.mapper.ProducerMapper;
import com.streamnet.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MuteUserProducer {

    private final KafkaTemplate<String, MuteUserEvent> kafkaTemplate;
    private final ProducerMapper producerMapper;

    public void sendMuteUserEvent(User user, Long authUserId, boolean hasUserMuted) {
        MuteUserEvent event = producerMapper.toMuteUserEvent(user, hasUserMuted);
        kafkaTemplate.send(ProducerUtil.authHeaderWrapper(KafkaTopicConstants.MUTE_USER_TOPIC, event, authUserId));
    }
}
