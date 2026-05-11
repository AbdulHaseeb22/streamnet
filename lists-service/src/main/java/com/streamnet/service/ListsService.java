package com.streamnet.service;

import com.streamnet.commons.dto.HeaderResponse;
import com.streamnet.dto.request.ListsRequest;
import com.streamnet.commons.dto.response.tweet.TweetResponse;
import com.streamnet.dto.request.UserToListsRequest;
import com.streamnet.model.User;
import com.streamnet.repository.projection.*;
import com.streamnet.repository.projection.PinnedListProjection;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface ListsService {

    List<ListProjection> getAllTweetLists();

    List<ListUserProjection> getUserTweetLists();

    List<PinnedListProjection> getUserPinnedLists();

    BaseListProjection getListById(Long listId);

    ListUserProjection createTweetList(ListsRequest listsRequest);

    List<ListProjection> getUserTweetListsById(Long userId);

    List<ListProjection> getTweetListsWhichUserIn();

    BaseListProjection editTweetList(ListsRequest listsRequest);

    String deleteList(Long listId);

    ListUserProjection followList(Long listId);

    PinnedListProjection pinList(Long listId);

    List<Map<String, Object>> getListsToAddUser(Long userId);

    String addUserToLists(UserToListsRequest userToListsRequest);

    Boolean addUserToList(Long userId, Long listId);

    HeaderResponse<TweetResponse> getTweetsByListId(Long listId, Pageable pageable);

    BaseListProjection getListDetails(Long listId);

    List<User> getListFollowers(Long listId, Long listOwnerId);

    List<Map<String, Object>> getListMembers(Long listId, Long listOwnerId);

    List<Map<String, Object>> searchListMembersByUsername(Long listId, String username);
}
