package az.spring.bankapplication.service.userService;

import az.spring.bankapplication.entity.User;
import az.spring.bankapplication.exception.UserNotFoundException;
import az.spring.bankapplication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDeleteService {

    private final UserRepository userRepository;

    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        userRepository.delete(user);
    }

}
