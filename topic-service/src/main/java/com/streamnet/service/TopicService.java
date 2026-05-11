package com.streamnet.service;

import com.streamnet.dto.response.TopicsByCategoriesResponse;
import com.streamnet.commons.enums.TopicCategory;
import com.streamnet.repository.projection.FollowedTopicProjection;
import com.streamnet.repository.projection.NotInterestedTopicProjection;
import com.streamnet.repository.projection.TopicProjection;

import java.util.List;

public interface TopicService {

    List<TopicProjection> getTopicsByIds(List<Long> topicsIds);

    List<TopicsByCategoriesResponse> getTopicsByCategories(List<TopicCategory> categories);

    List<FollowedTopicProjection> getFollowedTopics();

    List<TopicProjection> getFollowedTopicsByUserId(Long userId);

    List<NotInterestedTopicProjection> getNotInterestedTopics();

    Boolean processNotInterestedTopic(Long topicId);

    Boolean processFollowTopic(Long topicId);
}
