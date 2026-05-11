package com.streamnet.service.impl;

import com.streamnet.model.User;
import com.streamnet.broker.producer.BlockUserProducer;
import com.streamnet.repository.BlockUserRepository;
import com.streamnet.repository.FollowerUserRepository;
import com.streamnet.repository.projection.BlockedUserProjection;
import com.streamnet.service.AuthenticationService;
import com.streamnet.service.BlockUserService;
import com.streamnet.service.util.UserServiceHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BlockUserServiceImpl implements BlockUserService {

    private final AuthenticationService authenticationService;
    private final BlockUserRepository blockUserRepository;
    private final UserServiceHelper userServiceHelper;
    private final FollowerUserRepository followerUserRepository;
    private final BlockUserProducer blockUserProducer;

    @Override
    public Page<BlockedUserProjection> getBlockList(Pageable pageable) {
        Long authUserId = authenticationService.getAuthenticatedUserId();
        return blockUserRepository.getUserBlockListById(authUserId, pageable);
    }

    @Override
    @Transactional
    public Boolean processBlockList(Long userId) {
        User user = userServiceHelper.getUserById(userId);
        Long authUserId = authenticationService.getAuthenticatedUserId();
        boolean hasUserBlocked;

        if (blockUserRepository.isUserBlocked(authUserId, userId)) {
            blockUserRepository.unblockUser(authUserId, userId);
            hasUserBlocked = false;
        } else {
            blockUserRepository.blockUser(authUserId, userId);
            followerUserRepository.unfollow(authUserId, userId);
            followerUserRepository.unfollow(userId, authUserId);
            followerUserRepository.updateFollowersCount(false, userId);
            followerUserRepository.updateFollowingCount(false, authUserId);
            hasUserBlocked = true;
        }
        blockUserProducer.sendBlockUserEvent(user, authUserId, hasUserBlocked);
        return hasUserBlocked;
    }
}
