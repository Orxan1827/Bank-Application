package az.spring.bankapplication.userServiceTest;

import az.spring.bankapplication.dto.request.OTPCodeRequest;
import az.spring.bankapplication.dto.request.ResetPasswordRequest;
import az.spring.bankapplication.dto.request.UserPasswordRequest;
import az.spring.bankapplication.entity.User;
import az.spring.bankapplication.exception.UserEmailNotSameException;
import az.spring.bankapplication.exception.UserNotFoundByEmailException;
import az.spring.bankapplication.repository.UserRepository;
import az.spring.bankapplication.service.emailService.EmailService;
import az.spring.bankapplication.service.otpService.OTPService;
import az.spring.bankapplication.service.tokenService.JwtTokenService;
import az.spring.bankapplication.service.userService.UserPasswordChangeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static az.spring.bankapplication.util.UserUtil.*;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.quality.Strictness.LENIENT;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = LENIENT)
public class UserPasswordChangeServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private EmailService emailService;

    @Mock
    private JwtTokenService tokenService;

    @Mock
    private PasswordEncoder encoder;

    @Mock
    private OTPService otpService;

    @InjectMocks
    private UserPasswordChangeService userPasswordChangeService;

    @Test
    public void testResetPassword_whenUserPasswordRequestSuccessful() {
        UserPasswordRequest request = userPasswordRequest();
        User userByEmail = user();

        when(tokenService.extractUsername(token)).thenReturn(usernameFromToken);
        when(userRepository.findUserByEmail(request.getEmail())).thenReturn(Optional.of(userByEmail));
        when(otpService.generateOTPCode(usernameFromDataBase)).thenReturn(otpCode);

        userPasswordChangeService.resetPassword(auth, request);

        verify(tokenService, times(1)).extractUsername(token);
        verify(userRepository, times(1)).findUserByEmail(request.getEmail());
        verify(otpService, times(1)).generateOTPCode(usernameFromDataBase);
        verify(emailService, times(1)).sendPasswordResetEmail(eq(request.getEmail()), eq("Verification code: "), eq(otpCode));
    }

    @Test
    public void testResetPassword_whenUserNotFoundByEmail() {
        UserPasswordRequest request = userPasswordRequest();

        when(tokenService.extractUsername(token)).thenReturn(usernameFromToken);
        when(userRepository.findUserByEmail(request.getEmail())).thenReturn(Optional.empty());
        when(otpService.generateOTPCode(usernameFromDataBase)).thenReturn(otpCode);

        assertThrows(UserNotFoundByEmailException.class, () -> userPasswordChangeService.resetPassword(auth, request));

        verify(tokenService, times(1)).extractUsername(token);
        verify(userRepository, times(1)).findUserByEmail(request.getEmail());
        verify(otpService, never()).generateOTPCode(usernameFromDataBase);
        verify(emailService, never()).sendPasswordResetEmail(eq(request.getEmail()), eq("Verification code: "), eq(otpCode));
    }

    @Test
    public void testResetPassword_whenUserNameNotSame_shouldReturnUserEmailNotSameException() {
        UserPasswordRequest request = userPasswordRequestNotSame();
        User userByEmail = user();
        userByEmail.setUsername("test_username1");

        when(tokenService.extractUsername(token)).thenReturn(usernameFromToken);
        when(userRepository.findUserByEmail(request.getEmail())).thenReturn(Optional.of(userByEmail));
        when(otpService.generateOTPCode(usernameFromDataBase)).thenReturn(otpCode);

        assertThrows(UserEmailNotSameException.class, () -> userPasswordChangeService.resetPassword(auth, request));

        verify(tokenService, times(1)).extractUsername(token);
        verify(userRepository, times(1)).findUserByEmail(request.getEmail());
        verify(otpService, never()).generateOTPCode(usernameFromDataBase);
        verify(emailService, never()).sendPasswordResetEmail(eq(request.getEmail()), eq("Verification code: "), eq(otpCode));
    }

    @Test
    public void testEnterOtpCode_whenOTPCodeRequestSuccess_thanReturnSuccessfully() {
        OTPCodeRequest request = otpCodeRequest();
        User user = user();

        when(tokenService.extractUsername(token)).thenReturn(username);
        when(userRepository.findUserByUsername(username)).thenReturn(Optional.of(user));

        userPasswordChangeService.enterOtpCode(auth, request);

        verify(tokenService, times(1)).extractUsername(token);
        verify(userRepository, times(1)).findUserByUsername(username);
        verify(otpService, times(1)).verifyOTPCode(username, request.getOtpCode());
    }

    @Test
    public void testEnterNewPassword_whenResetPasswordRequest_Success() {
        ResetPasswordRequest request = resetPasswordRequest();
        User user = user();
        when(tokenService.extractUsername(token)).thenReturn(username);
        when(userRepository.findUserByUsername(username)).thenReturn(Optional.of(user));
        when(encoder.encode(request.getPassword())).thenReturn(anyString());

        userPasswordChangeService.enterNewPassword(auth, request);

        verify(tokenService, times(1)).extractUsername(token);
        verify(userRepository, times(1)).findUserByUsername(username);
        verify(encoder, times(1)).encode(request.getPassword());
    }

}
