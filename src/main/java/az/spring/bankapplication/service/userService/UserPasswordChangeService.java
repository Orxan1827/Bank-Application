package az.spring.bankapplication.service.userService;

import az.spring.bankapplication.dto.request.OTPCodeRequest;
import az.spring.bankapplication.dto.request.ResetPasswordRequest;
import az.spring.bankapplication.entity.User;
import az.spring.bankapplication.exception.UserEmailNotSameException;
import az.spring.bankapplication.exception.UserNotFoundByEmailException;
import az.spring.bankapplication.exception.UserNotFoundByUsernameException;
import az.spring.bankapplication.dto.request.UserPasswordRequest;
import az.spring.bankapplication.repository.UserRepository;
import az.spring.bankapplication.service.otpService.OTPService2;
import az.spring.bankapplication.service.emailService.EmailService;
import az.spring.bankapplication.service.tokenService.JwtTokenService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserPasswordChangeService {

    private final UserRepository userRepository;

    private final EmailService emailService;

    private final JwtTokenService tokenService;

    private final PasswordEncoder encoder;

    private final OTPService2 otpService;

    public String resetPassword(String auth, UserPasswordRequest request) {
        String token = auth.substring(7);
        String usernameFromToken = tokenService.extractUsername(token);

        User userByEmail = userRepository.findUserByEmail(request.getEmail()).orElseThrow(UserNotFoundByEmailException::new);
        String usernameFromDataBase = userByEmail.getUsername();
        if (!usernameFromToken.equals(usernameFromDataBase)) {
            throw new UserEmailNotSameException();
        }
        return otpService.generateOTPCode(usernameFromDataBase);
//        emailService.sendPasswordResetEmail(userByEmail.getEmail(), "Verification code: ", otpCode);
    }

    public String enterOtpCode(String auth, OTPCodeRequest request) {
        log.info("auth: {}", auth);
        String token = auth.substring(7);
        log.info("token: {}", token);
        String username = tokenService.extractUsername(token);
        User userByUsername = userRepository.findUserByUsername(username).orElseThrow(UserNotFoundByUsernameException::new);
        otpService.verifyOTPCode(username, request.getOtpCode());
        return "Successfully!";
    }

    public String enterNewPassword(String auth, ResetPasswordRequest resetPasswordRequest) {
        String token = auth.substring(7);
        String username = tokenService.extractUsername(token);
        User userByUsername = userRepository.findUserByUsername(username).orElseThrow(UserNotFoundByUsernameException::new);
        userByUsername.setPassword(encoder.encode(resetPasswordRequest.getPassword()));
        userRepository.save(userByUsername);
        return "Your password changed successfully";
    }

}
