package az.spring.bankapplication.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class GenericException extends RuntimeException {

    private HttpStatus httpStatus;
    private Integer errorCode;
    private String errorMessage;

}
