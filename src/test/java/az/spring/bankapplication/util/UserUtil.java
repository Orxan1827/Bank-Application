package az.spring.bankapplication.util;

import az.spring.bankapplication.dto.request.OTPCodeRequest;
import az.spring.bankapplication.dto.request.ResetPasswordRequest;
import az.spring.bankapplication.dto.request.UserLoginRequest;
import az.spring.bankapplication.dto.request.UserPasswordRequest;
import az.spring.bankapplication.dto.response.TokenResponse;
import az.spring.bankapplication.entity.User;

public class UserUtil {

    private UserUtil() {

    }

    public static final String otpCode = "123456";

    public static final String token = "test_token";

    public static final String auth = "Bearer " + token;

    public static final String username = "test_username";

    public static final String usernameFromToken = "test_username";

    public static final String usernameFromDataBase = "test_username";

    public static User user() {
        return User.builder()
                .id(1L)
                .username("test_username")
                .password("test_password")
                .email("test_email")
                .build();
    }

    public static UserLoginRequest userLoginRequest() {
        return UserLoginRequest.builder()
                .username("test_username")
                .password("test_password")
                .build();
    }

    public static UserPasswordRequest userPasswordRequest() {
        return UserPasswordRequest.builder()
                .email("test_email")
                .build();
    }

    public static UserPasswordRequest userPasswordRequestNotSame() {
        return UserPasswordRequest.builder()
                .email("test_email")
                .build();
    }

    public static OTPCodeRequest otpCodeRequest() {
        return OTPCodeRequest.builder()
                .otpCode("123456")
                .build();
    }

    public static TokenResponse tokenResponse() {
        return TokenResponse.builder()
                .accessToken("test_token")
                .build();
    }

    public static ResetPasswordRequest resetPasswordRequest() {
        return ResetPasswordRequest.builder()
                .password("test_password")
                .build();
    }

}
