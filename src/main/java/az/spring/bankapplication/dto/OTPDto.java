package az.spring.bankapplication.dto;

import az.spring.bankapplication.enums.OTPStatus;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OTPDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String otpCode;
    private Long creationTime;
    private String username;
    private OTPStatus status;
    private int attempts;

}
