package az.spring.bankapplication.exception;

import org.springframework.http.HttpStatus;

import static az.spring.bankapplication.constant.ExceptionConstant.ACCOUNT_IS_INACTIVE;

public class AccountActivationException extends GenericException {

    public AccountActivationException() {
        super(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(), ACCOUNT_IS_INACTIVE);
    }

}
