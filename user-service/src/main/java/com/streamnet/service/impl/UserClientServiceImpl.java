package com.streamnet.service.impl;

import com.streamnet.commons.dto.response.notification.NotificationUserResponse;
import com.streamnet.commons.dto.response.user.UserResponse;
import com.streamnet.commons.event.UpdateUserEvent;
import com.streamnet.commons.mapper.BasicMapper;
import com.streamnet.model.User;
import com.streamnet.repository.UserRepository;
import com.streamnet.repository.projection.NotificationUserProjection;
import com.streamnet.repository.projection.UserProjection;
import com.streamnet.service.UserClientService;
import com.streamnet.commons.util.AuthUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserClientServiceImpl implements UserClientService {

    private final UserRepository userRepository;
    private final BasicMapper basicMapper;

    @Override
    public UserResponse getUserResponseById(Long userId) {
        UserProjection user = userRepository.getUserById(userId, UserProjection.class).get();
        return basicMapper.convertToResponse(user, UserResponse.class);
    }

    @Override
    public List<NotificationUserResponse> getUsersWhichUserSubscribed() {
        Long authUserId = AuthUtil.getAuthenticatedUserId();
        List<NotificationUserProjection> users = userRepository.getUsersWhichUserSubscribed(authUserId);
        return basicMapper.convertToResponseList(users, NotificationUserResponse.class);
    }

    @Override
    public List<Long> getUserIdsWhichUserSubscribed() {
        Long authUserId = AuthUtil.getAuthenticatedUserId();
        return userRepository.getUserIdsWhichUserSubscribed(authUserId);
    }

    @Override
    public List<UpdateUserEvent> getBatchUsers(Integer period, Integer page, Integer limit) {
        LocalDateTime sinceDate = LocalDateTime.now().minusDays(period);
        PageRequest pageable = PageRequest.of(page, limit);
        List<User> users = userRepository.findByRegistrationAndUpdatedDate(sinceDate, pageable);
        return basicMapper.convertToResponseList(users, UpdateUserEvent.class);
    }
}
