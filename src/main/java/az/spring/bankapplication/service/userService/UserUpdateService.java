package az.spring.bankapplication.service.userService;

import az.spring.bankapplication.entity.User;
import az.spring.bankapplication.exception.UserNotFoundException;
import az.spring.bankapplication.mapper.UserMapper;
import az.spring.bankapplication.dto.request.UserUpdateRequest;
import az.spring.bankapplication.dto.response.UserUpdateResponse;
import az.spring.bankapplication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserUpdateService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public UserUpdateResponse updateUser(Long userId, UserUpdateRequest updateRequest) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        User updatedUser = userMapper.updateUSerFromUpdateRequest(updateRequest, user);
        User savedUser = userRepository.save(updatedUser);
        return userMapper.mapUserToUpdatedResponse(savedUser);
    }

}
