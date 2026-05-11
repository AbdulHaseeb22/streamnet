package com.streamnet.repository.projection;

import com.streamnet.commons.enums.TopicCategory;
import org.springframework.beans.factory.annotation.Value;

public interface FollowedTopicProjection {
    Long getId();
    String getTopicName();
    TopicCategory getTopicCategory();

    @Value("#{true}")
    boolean getIsTopicFollowed();
}
