package az.spring.bankapplication.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterResponse {

    private Long id;

    private String email;

    private String username;

    private Long fkAccountId;

}
