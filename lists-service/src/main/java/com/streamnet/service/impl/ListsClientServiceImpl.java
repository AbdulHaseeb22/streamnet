package com.streamnet.service.impl;

import com.streamnet.commons.dto.response.tweet.TweetListResponse;
import com.streamnet.commons.mapper.BasicMapper;
import com.streamnet.repository.ListsRepository;
import com.streamnet.repository.projection.TweetListProjection;
import com.streamnet.service.ListsClientService;
import com.streamnet.service.UserService;
import com.streamnet.commons.util.AuthUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ListsClientServiceImpl implements ListsClientService {

    private final ListsRepository listsRepository;
    private final UserService userService;
    private final BasicMapper basicMapper;

    @Override
    public TweetListResponse getTweetList(Long listId) {
        Long authUserId = AuthUtil.getAuthenticatedUserId();
        Optional<TweetListProjection> list = listsRepository.getListById(listId, authUserId, TweetListProjection.class);

        if (list.isEmpty() || userService.isUserBlocked(list.get().getListOwner().getId(), authUserId)) {
            return new TweetListResponse();
        }
        if (!authUserId.equals(list.get().getListOwner().getId()) && userService.isUserHavePrivateProfile(list.get().getListOwner().getId(), authUserId)) {
            return new TweetListResponse();
        }
        return basicMapper.convertToResponse(list.get(), TweetListResponse.class);
    }
}
