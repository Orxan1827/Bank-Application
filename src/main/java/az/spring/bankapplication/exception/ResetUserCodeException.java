package az.spring.bankapplication.exception;

import org.springframework.http.HttpStatus;

import static az.spring.bankapplication.constant.ExceptionConstant.BALANCE_IS_NOT_ENOUGH;
import static az.spring.bankapplication.constant.ExceptionConstant.RESET_CODE_EXCEPTION_MESSAGE;

public class ResetUserCodeException extends GenericException{

    public ResetUserCodeException() {
        super(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(), RESET_CODE_EXCEPTION_MESSAGE);
    }

}
