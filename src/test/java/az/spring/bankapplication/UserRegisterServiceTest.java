package az.spring.bankapplication;

import az.spring.bankapplication.dto.request.UserRegisterRequest;
import az.spring.bankapplication.dto.response.UserRegisterResponse;
import az.spring.bankapplication.entity.User;
import az.spring.bankapplication.exception.UserAllReadyExistsException;
import az.spring.bankapplication.mapper.UserMapper;
import az.spring.bankapplication.repository.UserRepository;
import az.spring.bankapplication.service.userService.UserRegisterService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.security.crypto.password.PasswordEncoder;

import static az.spring.bankapplication.util.UserUtil.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.quality.Strictness.LENIENT;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = LENIENT)
public class UserRegisterServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private PasswordEncoder encoder;

    @InjectMocks
    private UserRegisterService userRegisterService;

    @Test
    public void testUserRegisterService_whenUserRegisterRequestSuccess() {
        UserRegisterResponse expected = userRegisterResponse();
        UserRegisterRequest request = userRegisterRequest();
        User user = userWithPinCode();

        when(userRepository.existsUserByPinCode(request.getPinCode())).thenReturn(false);
        when(userMapper.mapRegisterRequestToUser(request)).thenReturn(user);
        when(encoder.encode(request.getPassword())).thenReturn("encoded_test_password");
        when(userRepository.save(user)).thenReturn(user);
        when(userMapper.mapUserToRegisterResponse(user)).thenReturn(expected);

        UserRegisterResponse actually = userRegisterService.signup(request);

        assertNotNull(actually);
        assertEquals(actually, expected);

        verify(userRepository, times(1)).existsUserByPinCode(request.getPinCode());
        verify(userMapper, times(1)).mapRegisterRequestToUser(request);
        verify(encoder, times(1)).encode(request.getPassword());
        verify(userRepository, times(1)).save(user);
        verify(userMapper, times(1)).mapUserToRegisterResponse(user);
    }

    @Test
    public void testUserRegisterService_whenUserAllReadyExists_shouldThrowUserAllReadyExistsException() {
        UserRegisterRequest request = userRegisterRequest();
        User user = userWithPinCode();

        when(userRepository.existsUserByPinCode(request.getPinCode())).thenReturn(true);

        Assert.assertThrows(UserAllReadyExistsException.class, () -> userRegisterService.signup(request));

        verify(userRepository, times(1)).existsUserByPinCode(request.getPinCode());
        verify(userMapper, never()).mapRegisterRequestToUser(request);
        verify(encoder, never()).encode(request.getPassword());
        verify(userRepository, never()).save(user);
        verify(userMapper, never()).mapUserToRegisterResponse(user);
    }

}
