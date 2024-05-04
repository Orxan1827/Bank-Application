package az.spring.bankapplication.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OTPCodeRequest {

    @NotBlank
    private String otpCode;

}
