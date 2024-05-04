package az.spring.bankapplication.exception;

import org.springframework.http.HttpStatus;

import static az.spring.bankapplication.constant.ExceptionConstant.OTP_UPDATE_MESSAGE;

public class OTPCodeException extends GenericException {

    public OTPCodeException() {
        super(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(), OTP_UPDATE_MESSAGE);
    }

}
