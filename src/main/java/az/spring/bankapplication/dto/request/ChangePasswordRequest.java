package az.spring.bankapplication.dto.request;

import az.spring.bankapplication.constraint.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordRequest {

    @Email
    private String email;

    @NotBlank
    private String newPassword;
}
