package az.spring.bankapplication.service.userService;

import az.spring.bankapplication.constant.ExceptionConstant;
import az.spring.bankapplication.entity.User;
import az.spring.bankapplication.exception.GenericException;
import az.spring.bankapplication.exception.UserAllReadyExistsException;
import az.spring.bankapplication.mapper.UserMapper;
import az.spring.bankapplication.dto.request.UserRegisterRequest;
import az.spring.bankapplication.dto.response.UserRegisterResponse;
import az.spring.bankapplication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRegisterService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final PasswordEncoder encoder;

    public UserRegisterResponse signup(UserRegisterRequest registerRequest) {
        boolean userAllReadyExists = userRepository.existsUserByPinCode(registerRequest.getPinCode());
        if (userAllReadyExists) {
          throw new UserAllReadyExistsException();
        }
        User user = userMapper.mapRegisterRequestToUser(registerRequest);
        user.setPassword(encoder.encode(registerRequest.getPassword()));
        User savedUser = userRepository.save(user);
        return userMapper.mapUserToRegisterResponse(savedUser);
    }

}
