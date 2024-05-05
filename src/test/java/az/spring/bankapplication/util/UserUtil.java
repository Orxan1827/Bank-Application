package az.spring.bankapplication.util;

import az.spring.bankapplication.entity.User;

public class UserUtil {

    private UserUtil() {

    }

    public static User user() {
        return User.builder()
                .id(1L)
                .username("test_username")
                .password("test_password")
                .email("test_email")
                .build();
    }

}
