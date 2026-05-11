package com.streamnet.mapper;

import com.streamnet.commons.event.ListsNotificationDto;
import com.streamnet.commons.event.UserNotificationDto;
import com.streamnet.commons.mapper.BasicMapper;
import com.streamnet.model.Lists;
import com.streamnet.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ListsProducerMapperTest {

    @InjectMocks
    private ListsProducerMapper listsProducerMapper;

    @Mock
    private BasicMapper basicMapper;

    @Test
    public void toUserDto() {
        User notifiedUser = new User();
        User user = new User();
        Lists lists = new Lists();
        listsProducerMapper.toListsNotificationEvent(notifiedUser, user, lists);
        verify(basicMapper, times(2)).convertToResponse(user, UserNotificationDto.class);
        verify(basicMapper, times(1)).convertToResponse(lists, ListsNotificationDto.class);
    }
}
