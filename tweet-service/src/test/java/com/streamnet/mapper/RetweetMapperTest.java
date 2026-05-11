package com.streamnet.mapper;

import com.streamnet.TweetServiceTestHelper;
import com.streamnet.commons.dto.HeaderResponse;
import com.streamnet.commons.dto.response.tweet.TweetResponse;
import com.streamnet.commons.mapper.BasicMapper;
import com.streamnet.commons.dto.response.notification.NotificationTweetResponse;
import com.streamnet.commons.dto.response.user.UserResponse;
import com.streamnet.model.Tweet;
import com.streamnet.repository.projection.TweetUserProjection;
import com.streamnet.repository.projection.UserProjection;
import com.streamnet.service.RetweetService;
import com.streamnet.commons.util.TestConstants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class RetweetMapperTest {

    @InjectMocks
    private RetweetMapper retweetMapper;

    @Mock
    private BasicMapper basicMapper;

    @Mock
    private RetweetService retweetService;

    private static final PageRequest pageable = TweetServiceTestHelper.pageable;

    @Test
    public void getUserRetweetsAndReplies() {
        List<TweetUserProjection> tweetUserProjections = TweetServiceTestHelper.createMockTweetUserProjectionList();
        Page<TweetUserProjection> pageableTweetUserProjections = new PageImpl<>(tweetUserProjections, pageable, 20);
        HeaderResponse<TweetResponse> headerResponse = new HeaderResponse<>(
                List.of(new TweetResponse(), new TweetResponse()), new HttpHeaders());
        when(retweetService.getUserRetweetsAndReplies(TestConstants.USER_ID, pageable)).thenReturn(pageableTweetUserProjections);
        when(basicMapper.getHeaderResponse(pageableTweetUserProjections, TweetResponse.class)).thenReturn(headerResponse);
        assertEquals(headerResponse, retweetMapper.getUserRetweetsAndReplies(TestConstants.USER_ID, pageable));
        verify(retweetService, times(1)).getUserRetweetsAndReplies(TestConstants.USER_ID, pageable);
        verify(basicMapper, times(1)).getHeaderResponse(pageableTweetUserProjections, TweetResponse.class);
    }

    @Test
    public void getRetweetedUsersByTweetId() {
        HeaderResponse<UserResponse> headerResponse = new HeaderResponse<>(
                List.of(new UserResponse(), new UserResponse()), new HttpHeaders());
        Page<UserProjection> userProjections = TweetServiceTestHelper.createUserProjections();
        when(basicMapper.getHeaderResponse(userProjections, UserResponse.class)).thenReturn(headerResponse);
        when(retweetService.getRetweetedUsersByTweetId(TestConstants.TWEET_ID, pageable)).thenReturn(userProjections);
        assertEquals(headerResponse, retweetMapper.getRetweetedUsersByTweetId(TestConstants.TWEET_ID, pageable));
        verify(retweetService, times(1)).getRetweetedUsersByTweetId(TestConstants.TWEET_ID, pageable);
    }

    @Test
    public void retweet() {
        Tweet tweet = new Tweet();
        NotificationTweetResponse notificationResponse = new NotificationTweetResponse();
        when(retweetService.retweet(TestConstants.TWEET_ID)).thenReturn(tweet);
        when(basicMapper.convertToResponse(tweet, NotificationTweetResponse.class)).thenReturn(notificationResponse);
        assertEquals(notificationResponse, retweetMapper.retweet(TestConstants.TWEET_ID));
        verify(retweetService, times(1)).retweet(TestConstants.TWEET_ID);
    }
}
