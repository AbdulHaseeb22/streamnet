package com.streamnet.service.impl;

import com.streamnet.commons.constants.ErrorMessage;
import com.streamnet.commons.util.TestConstants;
import com.streamnet.service.AbstractServiceTest;
import com.streamnet.TopicTestHelper;
import com.streamnet.commons.exception.ApiRequestException;
import com.streamnet.model.User;
import com.streamnet.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class UserServiceImplTest extends AbstractServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void getAuthUser() {
        User authUser = TopicTestHelper.mockAuthUser();
        when(userRepository.findById(TestConstants.USER_ID)).thenReturn(Optional.of(authUser));
        assertEquals(authUser, userService.getAuthUser());
        verify(userRepository, times(1)).findById(TestConstants.USER_ID);
    }

    @Test
    public void getAuthUser_shouldUserNotFound() {
        when(userRepository.findById(TestConstants.USER_ID)).thenReturn(Optional.empty());
        ApiRequestException exception = assertThrows(ApiRequestException.class,
                () -> userService.getAuthUser());
        assertEquals(ErrorMessage.USER_NOT_FOUND, exception.getMessage());
        assertEquals(HttpStatus.UNAUTHORIZED, exception.getStatus());
    }

    @Test
    public void validateUserProfile_shouldUserIdNotFound() {
        when(userRepository.isUserExists(1L)).thenReturn(false);
        ApiRequestException exception = assertThrows(ApiRequestException.class,
                () -> userService.validateUserProfile(1L));
        assertEquals(String.format(ErrorMessage.USER_ID_NOT_FOUND, 1L), exception.getMessage());
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
    }

    @Test
    public void validateUserProfile_shouldUserProfileBlocked() {
        when(userRepository.isUserExists(1L)).thenReturn(true);
        when(userRepository.isUserBlocked(1L, TestConstants.USER_ID)).thenReturn(true);
        ApiRequestException exception = assertThrows(ApiRequestException.class,
                () -> userService.validateUserProfile(1L));
        assertEquals(ErrorMessage.USER_PROFILE_BLOCKED, exception.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
    }

    @Test
    public void validateUserProfile_shouldUserHavePrivateProfile() {
        when(userRepository.isUserExists(1L)).thenReturn(true);
        when(userRepository.isUserBlocked(1L, TestConstants.USER_ID)).thenReturn(false);
        when(userRepository.isUserHavePrivateProfile(1L, TestConstants.USER_ID)).thenReturn(false);
        ApiRequestException exception = assertThrows(ApiRequestException.class,
                () -> userService.validateUserProfile(1L));
        assertEquals(ErrorMessage.USER_NOT_FOUND, exception.getMessage());
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
    }
}
