package az.spring.bankapplication.constraint.validator;

import az.spring.bankapplication.constraint.Username;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UsernameValidator implements ConstraintValidator<Username, String> {

    @Override
    public void initialize(Username constraintAnnotation) {
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        return username.length() >= 5 && username.length() <= 20;
    }
}
