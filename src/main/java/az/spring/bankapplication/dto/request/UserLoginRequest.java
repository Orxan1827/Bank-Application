package az.spring.bankapplication.dto.request;

import az.spring.bankapplication.constraint.Password;
import az.spring.bankapplication.constraint.Username;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginRequest {

    @Username
    private String username;

    @Password
    private String password;

}
