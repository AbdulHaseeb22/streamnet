package com.streamnet.broker.producer;

import com.streamnet.broker.util.ProducerUtil;
import com.streamnet.commons.constants.KafkaTopicConstants;
import com.streamnet.commons.event.PinTweetEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PinTweetProducer {

    private final KafkaTemplate<String, PinTweetEvent> kafkaTemplate;

    public void sendPinTweetEvent(Long tweetId, Long authUserId) {
        PinTweetEvent pinTweetEvent = PinTweetEvent.builder()
                .tweetId(tweetId)
                .build();
        kafkaTemplate.send(ProducerUtil.authHeaderWrapper(KafkaTopicConstants.PIN_TWEET_USER_TOPIC, pinTweetEvent, authUserId));
    }
}
