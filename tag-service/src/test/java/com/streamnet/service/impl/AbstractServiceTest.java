package com.streamnet.service.impl;

import com.streamnet.client.TweetClient;
import com.streamnet.commons.constants.HeaderConstants;
import com.streamnet.commons.util.TestConstants;
import com.streamnet.repository.TagRepository;
import com.streamnet.repository.TweetTagRepository;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@SpringBootTest
@RunWith(SpringRunner.class)
public abstract class AbstractServiceTest {

    @MockBean
    public TagRepository tagRepository;

    @MockBean
    public TweetTagRepository tweetTagRepository;

    @MockBean
    public TweetClient tweetClient;

    @Before
    public void setUp() {
        MockHttpServletRequest mockRequest = new MockHttpServletRequest();
        mockRequest.addHeader(HeaderConstants.AUTH_USER_ID_HEADER, TestConstants.USER_ID);
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(mockRequest));
    }
}
