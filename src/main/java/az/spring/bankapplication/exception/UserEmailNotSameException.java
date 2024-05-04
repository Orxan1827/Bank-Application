package az.spring.bankapplication.exception;

import org.springframework.http.HttpStatus;

import static az.spring.bankapplication.constant.ExceptionConstant.USER_EMAIL_NOT_SAME_MESSAGE;

public class UserEmailNotSameException extends GenericException {

    public UserEmailNotSameException() {
        super(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(), USER_EMAIL_NOT_SAME_MESSAGE);
    }

}
