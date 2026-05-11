package com.streamnet.broker.consumer;

import com.streamnet.commons.constants.KafkaTopicConstants;
import com.streamnet.commons.event.BlockUserEvent;
import com.streamnet.commons.event.FollowUserEvent;
import com.streamnet.commons.event.UpdateUserEvent;
import com.streamnet.service.UserHandlerService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import static com.streamnet.commons.constants.HeaderConstants.AUTH_USER_ID_HEADER;

@Component
@RequiredArgsConstructor
public class UserConsumer {

    private final UserHandlerService userHandlerService;

    @KafkaListener(topics = KafkaTopicConstants.UPDATE_USER_TOPIC, groupId = "${spring.kafka.consumer.group-id}")
    public void userUpdateListener(UpdateUserEvent updateUserEvent) {
        userHandlerService.handleNewOrUpdateUser(updateUserEvent);
    }

    @KafkaListener(topics = KafkaTopicConstants.BLOCK_USER_TOPIC, groupId = "${spring.kafka.consumer.group-id}")
    public void userBlockListener(BlockUserEvent blockUserEvent, @Header(AUTH_USER_ID_HEADER) String authId) {
        userHandlerService.handleBlockUser(blockUserEvent, authId);
    }

    @KafkaListener(topics = KafkaTopicConstants.FOLLOW_USER_TOPIC, groupId = "${spring.kafka.consumer.group-id}")
    public void userFollowListener(FollowUserEvent followUserEvent, @Header(AUTH_USER_ID_HEADER) String authId) {
        userHandlerService.handleFollowUser(followUserEvent, authId);
    }
}
