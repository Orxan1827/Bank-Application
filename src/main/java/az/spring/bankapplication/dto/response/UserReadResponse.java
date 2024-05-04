package az.spring.bankapplication.dto.response;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserReadResponse {

    private Long id;

    private String email;

    private String username;

    private boolean emailVerified;

    private Long fkAccountId;

}
