package az.spring.bankapplication.exception;

import org.springframework.http.HttpStatus;

import static az.spring.bankapplication.constant.ExceptionConstant.BALANCE_IS_NOT_ENOUGH;

public class InsufficientBalanceException extends GenericException {

    public InsufficientBalanceException() {
        super(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(), BALANCE_IS_NOT_ENOUGH);
    }

}
