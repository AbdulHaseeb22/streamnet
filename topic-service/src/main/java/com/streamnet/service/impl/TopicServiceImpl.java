package com.streamnet.service.impl;

import com.streamnet.constants.TopicErrorMessage;
import com.streamnet.dto.response.TopicsByCategoriesResponse;
import com.streamnet.commons.enums.TopicCategory;
import com.streamnet.commons.exception.ApiRequestException;
import com.streamnet.model.Topic;
import com.streamnet.model.User;
import com.streamnet.repository.TopicRepository;
import com.streamnet.repository.projection.FollowedTopicProjection;
import com.streamnet.repository.projection.NotInterestedTopicProjection;
import com.streamnet.repository.projection.TopicProjection;
import com.streamnet.service.TopicService;
import com.streamnet.service.UserService;
import com.streamnet.commons.util.AuthUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TopicServiceImpl implements TopicService {

    private final TopicRepository topicRepository;
    private final UserService userService;

    @Override
    public List<TopicProjection> getTopicsByIds(List<Long> topicsIds) {
        return topicRepository.getTopicsByIds(topicsIds);
    }

    @Override
    public List<TopicsByCategoriesResponse> getTopicsByCategories(List<TopicCategory> categories) {
        return categories.stream()
                .map(topicCategory -> {
                    List<TopicProjection> topics = topicRepository.getTopicsByCategory(topicCategory);
                    return new TopicsByCategoriesResponse(topicCategory, topics);
                })
                .toList();
    }

    @Override
    public List<FollowedTopicProjection> getFollowedTopics() {
        Long authUserId = AuthUtil.getAuthenticatedUserId();
        return topicRepository.getTopicsByTopicFollowerId(authUserId, FollowedTopicProjection.class);
    }

    @Override
    public List<TopicProjection> getFollowedTopicsByUserId(Long userId) {
        userService.validateUserProfile(userId);
        return topicRepository.getTopicsByTopicFollowerId(userId, TopicProjection.class);
    }

    @Override
    public List<NotInterestedTopicProjection> getNotInterestedTopics() {
        Long authUserId = AuthUtil.getAuthenticatedUserId();
        return topicRepository.getTopicsByNotInterestedUserId(authUserId);
    }

    @Override
    @Transactional
    public Boolean processNotInterestedTopic(Long topicId) {
        Topic topic = getTopicById(topicId);
        User authUser = userService.getAuthUser();

        if (topic.getTopicNotInterested().contains(authUser)) {
            topic.getTopicNotInterested().remove(authUser);
            return false;
        } else {
            topic.getTopicFollowers().remove(authUser);
            topic.getTopicNotInterested().add(authUser);
            return true;
        }
    }

    @Override
    @Transactional
    public Boolean processFollowTopic(Long topicId) {
        Topic topic = getTopicById(topicId);
        User authUser = userService.getAuthUser();

        if (topic.getTopicFollowers().contains(authUser)) {
            topic.getTopicFollowers().remove(authUser);
            return false;
        } else {
            topic.getTopicNotInterested().remove(authUser);
            topic.getTopicFollowers().add(authUser);
            return true;
        }
    }

    private Topic getTopicById(Long topicId) {
        return topicRepository.findById(topicId)
                .orElseThrow(() -> new ApiRequestException(TopicErrorMessage.TOPIC_NOT_FOUND, HttpStatus.NOT_FOUND));
    }
}
