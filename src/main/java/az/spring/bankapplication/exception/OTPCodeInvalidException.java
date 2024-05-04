package az.spring.bankapplication.exception;

import org.springframework.http.HttpStatus;

import static az.spring.bankapplication.constant.ExceptionConstant.OTP_INVALID_MESSAGE;

public class OTPCodeInvalidException extends GenericException{

    public OTPCodeInvalidException() {
        super(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(), OTP_INVALID_MESSAGE);
    }

}
