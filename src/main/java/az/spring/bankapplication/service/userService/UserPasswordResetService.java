package az.spring.bankapplication.service.userService;

import az.spring.bankapplication.entity.User;
import az.spring.bankapplication.exception.UserNotFoundException;
import az.spring.bankapplication.dto.request.ChangePasswordRequest;
import az.spring.bankapplication.dto.request.UserPasswordRequest;
import az.spring.bankapplication.repository.UserRepository;
import az.spring.bankapplication.service.emailService.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class UserPasswordResetService {

    private final UserRepository userRepository;

    private final EmailService emailService;

    private final PasswordEncoder encoder;

    public void resetPassword(UserPasswordRequest request) {
        User userByEmail = userRepository.findUserByEmail(request.getEmail()).orElseThrow(UserNotFoundException::new);

        String newPassword = generateNewPassword();

        String encodedPassword = encoder.encode(newPassword);

        userByEmail.setPassword(encodedPassword);

        userRepository.save(userByEmail);

        emailService.sendPasswordResetEmail(userByEmail.getEmail(), "Password message: ", newPassword);
    }

    public void changePassword(ChangePasswordRequest request) {
        var userByEmail = userRepository.findUserByEmail(request.getEmail()).orElseThrow(UserNotFoundException::new);
        userByEmail.setPassword(encoder.encode(request.getNewPassword()));
        userRepository.save(userByEmail);
    }

    private String generateNewPassword() {
        int length = 10;

        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder newPassword = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            newPassword.append(characters.charAt(random.nextInt(characters.length())));
        }
        return newPassword.append("@On1").toString();
    }

}
