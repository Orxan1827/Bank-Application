package az.spring.bankapplication.service.userService;

import az.spring.bankapplication.dto.response.UserReadResponse;
import az.spring.bankapplication.exception.UserNotFoundException;
import az.spring.bankapplication.mapper.UserMapper;
import az.spring.bankapplication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserReadService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public UserReadResponse getUser(Long userId) {
        return userRepository.findById(userId)
                .map(userMapper::mapUserToReadResponse)
                .orElseThrow(UserNotFoundException::new);
    }
}
