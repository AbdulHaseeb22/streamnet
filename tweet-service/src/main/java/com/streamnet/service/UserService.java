package com.streamnet.service;

import com.streamnet.model.Tweet;
import com.streamnet.model.User;
import com.streamnet.repository.projection.UserProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserService {

    User getAuthUser();

    Optional<User> getUserById(Long userId);

    Optional<User> getUserIdByUsername(String username);

    boolean isUserHavePrivateProfile(Long userId);

    boolean isMyProfileBlockedByUser(Long userId);

    Page<UserProjection> getLikedUsersByTweet(Tweet tweet, Pageable pageable);

    Page<UserProjection> getRetweetedUsersByTweet(Tweet tweet, Pageable pageable);

    Page<UserProjection> getTaggedImageUsers(Tweet tweet, Pageable pageable);
}
