package az.spring.bankapplication.dto.request;

import az.spring.bankapplication.constraint.Email;
import az.spring.bankapplication.constraint.Password;
import az.spring.bankapplication.constraint.UniquePinCode;
import az.spring.bankapplication.constraint.Username;
import az.spring.bankapplication.enums.UserRole;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterRequest {

    @Email
    private String email;

    @Username
    private String username;

    @Password
    private String password;

    @UniquePinCode
    private String pinCode;

    @NotNull
    private UserRole role;

}
