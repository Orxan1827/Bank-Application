package az.spring.bankapplication.service.userService;

import az.spring.bankapplication.mapper.UserMapper;
import az.spring.bankapplication.dto.response.UserReadResponse;
import az.spring.bankapplication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserReadAllService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public List<UserReadResponse> getAllUser() {
        return userRepository.findAll().stream()
                .map(userMapper::mapUserToReadResponse)
                .collect(Collectors.toList());
    }

}
