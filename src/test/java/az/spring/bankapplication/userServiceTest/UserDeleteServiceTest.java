package az.spring.bankapplication.userServiceTest;

import az.spring.bankapplication.entity.User;
import az.spring.bankapplication.exception.UserNotFoundException;
import az.spring.bankapplication.repository.UserRepository;
import az.spring.bankapplication.service.userService.UserDeleteService;
import az.spring.bankapplication.util.UserUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;

import java.util.Optional;

import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.quality.Strictness.LENIENT;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = LENIENT)
public class UserDeleteServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserDeleteService userDeleteService;

    @Test
    public void testDeleteService_whenUserExists_shouldReturnUser() {
        Long userId = 1L;
        User user = UserUtil.user();
        when(userRepository.findById(userId)).thenReturn(Optional.ofNullable(user));

        userDeleteService.deleteUser(userId);

        Assertions.assertNotNull(user);

        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, times(1)).delete(user);
    }

    @Test
    public void testDeleteService_whenUserDoesNotExists_shouldThrowsUserNotFoundException() {
        Long userId = 1L;
        User user = UserUtil.user();
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class,() -> userDeleteService.deleteUser(userId));

        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, never()).delete(user);
    }

}
