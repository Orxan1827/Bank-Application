package az.spring.bankapplication.dto.request;

import az.spring.bankapplication.constraint.Password;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResetPasswordRequest {

    @Password
    private String password;

}
