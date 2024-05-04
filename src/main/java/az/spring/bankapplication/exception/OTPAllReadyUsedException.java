package az.spring.bankapplication.exception;

import org.springframework.http.HttpStatus;

import static az.spring.bankapplication.constant.ExceptionConstant.OTP_USED_MESSAGE;


public class OTPAllReadyUsedException extends GenericException {

    public OTPAllReadyUsedException() {
        super(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(), OTP_USED_MESSAGE);
    }

}
