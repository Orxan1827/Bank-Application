package az.spring.bankapplication.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateResponse {

    private Long id;

    private String email;

    private String username;

    private String pinCode;

    private boolean emailVerified;

    private Long fkAccountId;

}
