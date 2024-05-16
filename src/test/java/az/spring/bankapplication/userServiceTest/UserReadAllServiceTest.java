package az.spring.bankapplication.userServiceTest;

import az.spring.bankapplication.dto.response.UserReadResponse;
import az.spring.bankapplication.entity.User;
import az.spring.bankapplication.mapper.UserMapper;
import az.spring.bankapplication.repository.UserRepository;
import az.spring.bankapplication.service.userService.UserReadAllService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;

import java.util.List;

import static az.spring.bankapplication.util.UserUtil.*;
import static az.spring.bankapplication.util.UserUtil.userReadResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.quality.Strictness.LENIENT;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = LENIENT)
public class UserReadAllServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserReadAllService userReadAllService;

    @Test
    public void testUserReadAllService_whenGetAllUserCalled() {
        List<UserReadResponse> expectedList = userReadResponse();
        List<User> userList = userList();

        when(userRepository.findAll()).thenReturn(userList);
        when(userMapper.mapUserToReadResponse(any(User.class))).thenReturn(expectedList.get(0), expectedList.get(1));

        List<UserReadResponse> actuallyList = userReadAllService.getAllUser();

        assertNotNull(actuallyList);
        assertEquals(actuallyList, expectedList);
        assertEquals(actuallyList.get(0), expectedList.get(0));

        verify(userRepository, times(1)).findAll();
        verify(userMapper, times(2)).mapUserToReadResponse(any(User.class));
    }

}
