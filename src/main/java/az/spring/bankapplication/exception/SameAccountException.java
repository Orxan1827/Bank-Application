package az.spring.bankapplication.exception;

import org.springframework.http.HttpStatus;

import static az.spring.bankapplication.constant.ExceptionConstant.SAME_ACCOUNT_NUMBER;

public class SameAccountException extends GenericException{

    public SameAccountException() {
        super(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(), SAME_ACCOUNT_NUMBER);
    }

}
