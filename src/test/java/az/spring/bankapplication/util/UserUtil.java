package az.spring.bankapplication.util;

import az.spring.bankapplication.dto.request.*;
import az.spring.bankapplication.dto.response.TokenResponse;
import az.spring.bankapplication.dto.response.UserReadResponse;
import az.spring.bankapplication.dto.response.UserRegisterResponse;
import az.spring.bankapplication.entity.User;

import java.util.ArrayList;
import java.util.List;

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

    public static User userWithPinCode() {
        return User.builder()
                .id(1L)
                .username("test_username")
                .password("test_password")
                .email("test_email")
                .pinCode("123456")
                .build();
    }

    public static UserRegisterResponse userRegisterResponse() {
        return UserRegisterResponse.builder()
                .id(1L)
                .username("test_username")
                .email("test_email")
                .fkAccountId(1L)
                .build();
    }

    public static UserRegisterRequest userRegisterRequest() {
        return UserRegisterRequest.builder()
                .username("test_username")
                .password("test_password")
                .email("test_email")
                .pinCode("123456")
                .build();
    }

    public static UserLoginRequest userLoginRequest() {
        return UserLoginRequest.builder()
                .username("test_username")
                .password("test_password")
                .build();
    }

    public static List<UserReadResponse> userReadResponse() {
        List<UserReadResponse> expectedList = new ArrayList<>();
        expectedList.add(UserReadResponse.builder().id(1L).username("test_username").email("test_email").fkAccountId(1L).build());
        expectedList.add(UserReadResponse.builder().id(2L).username("test_username").email("test_email").fkAccountId(1L).build());
        return expectedList;
    }

    public static List<User> userList() {
        List<User> userList = new ArrayList<>();
        userList.add(User.builder().id(1L).username("test_username").email("test_email").fkAccountId(1L).build());
        userList.add(User.builder().id(2L).username("test_username").email("test_email").fkAccountId(1L).build());
        return userList;
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
