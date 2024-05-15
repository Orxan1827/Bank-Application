package az.spring.bankapplication.exception;

import org.springframework.http.HttpStatus;

import static az.spring.bankapplication.constant.ExceptionConstant.BAD_CREDENTIAL;

public class CustomBadCredentialsException extends GenericException {

    public CustomBadCredentialsException() {
        super(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(), BAD_CREDENTIAL);
    }

}
