package com.streamnet.broker.producer;

import com.streamnet.commons.constants.KafkaTopicConstants;
import com.streamnet.commons.event.ListsNotificationEvent;
import com.streamnet.mapper.ListsProducerMapper;
import com.streamnet.model.Lists;
import com.streamnet.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ListsNotificationProducer {

    private final KafkaTemplate<String, ListsNotificationEvent> kafkaTemplate;
    private final ListsProducerMapper listsProducerMapper;

    public void sendNotificationEvent(User notifiedUser, User user, Lists lists) {
        ListsNotificationEvent notificationEvent = listsProducerMapper.toListsNotificationEvent(notifiedUser, user, lists);
        kafkaTemplate.send(KafkaTopicConstants.SEND_LISTS_NOTIFICATION_TOPIC, notificationEvent);
    }
}
