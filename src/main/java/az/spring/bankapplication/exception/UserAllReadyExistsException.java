package az.spring.bankapplication.exception;

import org.springframework.http.HttpStatus;

import static az.spring.bankapplication.constant.ExceptionConstant.USER_ALREADY_EXISTS;

public class UserAllReadyExistsException extends GenericException{

    public UserAllReadyExistsException() {
        super(HttpStatus.CONFLICT, HttpStatus.CONFLICT.value(), USER_ALREADY_EXISTS);
    }

}
