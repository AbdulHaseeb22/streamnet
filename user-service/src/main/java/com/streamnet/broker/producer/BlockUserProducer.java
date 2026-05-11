package com.streamnet.broker.producer;

import com.streamnet.broker.util.ProducerUtil;
import com.streamnet.commons.constants.KafkaTopicConstants;
import com.streamnet.commons.event.BlockUserEvent;
import com.streamnet.mapper.ProducerMapper;
import com.streamnet.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BlockUserProducer {

    private final KafkaTemplate<String, BlockUserEvent> kafkaTemplate;
    private final ProducerMapper producerMapper;

    public void sendBlockUserEvent(User user, Long authUserId, boolean hasUserBlocked) {
        BlockUserEvent event = producerMapper.toBlockUserEvent(user, hasUserBlocked);
        kafkaTemplate.send(ProducerUtil.authHeaderWrapper(KafkaTopicConstants.BLOCK_USER_TOPIC, event, authUserId));
    }
}
