package az.spring.bankapplication.userServiceTest;

import az.spring.bankapplication.dto.response.UserReadResponse;
import az.spring.bankapplication.entity.User;
import az.spring.bankapplication.exception.UserNotFoundException;
import az.spring.bankapplication.mapper.UserMapper;
import az.spring.bankapplication.repository.UserRepository;
import az.spring.bankapplication.service.userService.UserReadService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;

import java.util.Optional;

import static az.spring.bankapplication.util.UserUtil.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.quality.Strictness.LENIENT;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = LENIENT)
public class UserReadServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserReadService userReadService;

    @Test
    public void testUserReadService_whenUserExists_shouldReturnUserReadResponse() {
        Long userId = 1L;
        UserReadResponse expected = userReadResponse().get(0);
        User user = userList().get(0);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(userMapper.mapUserToReadResponse(user)).thenReturn(expected);

        UserReadResponse actually = userReadService.getUser(userId);

        assertNotNull(actually);
        assertEquals(actually, expected);

        verify(userRepository, times(1)).findById(userId);
        verify(userMapper, times(1)).mapUserToReadResponse(user);
    }

    @Test
    public void testUserReadService_whenUserDoesNotExists_shouldThrowsUserNotFoundException() {
        Long userId = 1L;

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        Assert.assertThrows(UserNotFoundException.class, () -> userReadService.getUser(userId));

        verify(userRepository, times(1)).findById(userId);
    }

}
