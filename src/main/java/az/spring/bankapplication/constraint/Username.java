package az.spring.bankapplication.constraint;

import az.spring.bankapplication.constraint.validator.UsernameValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.lang.annotation.*;

import static az.spring.bankapplication.constant.ValidationMessageConstants.USERNAME_REGEX;
import static az.spring.bankapplication.constant.ValidationMessageConstants.USERNAME_REGEX_MESSAGE;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@NotBlank
@NotNull
@Pattern(regexp = USERNAME_REGEX, message = USERNAME_REGEX_MESSAGE)
@Constraint(validatedBy = {UsernameValidator.class})
public @interface Username {

    String message() default "The length of the username must be greater than 4 and less than 20.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
