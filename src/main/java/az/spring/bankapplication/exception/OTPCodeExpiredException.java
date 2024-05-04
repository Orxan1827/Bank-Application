package az.spring.bankapplication.exception;

import org.springframework.http.HttpStatus;

import static az.spring.bankapplication.constant.ExceptionConstant.OTP_EXPIRED_MESSAGE;

public class OTPCodeExpiredException extends GenericException {

    public OTPCodeExpiredException() {
        super(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(), OTP_EXPIRED_MESSAGE);
    }

}
