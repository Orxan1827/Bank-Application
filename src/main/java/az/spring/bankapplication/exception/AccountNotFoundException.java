package az.spring.bankapplication.exception;

import org.springframework.http.HttpStatus;

import static az.spring.bankapplication.constant.ExceptionConstant.ACCOUNT_NOT_FOUND;

public class AccountNotFoundException extends GenericException {

    public AccountNotFoundException() {
        super(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(), ACCOUNT_NOT_FOUND);
    }

}
