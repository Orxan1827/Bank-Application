package az.spring.bankapplication.constraint;

import az.spring.bankapplication.constraint.validator.UniqueEmailValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.lang.annotation.*;

import static az.spring.bankapplication.constant.ValidationMessageConstants.EMAIL_REGEX;
import static az.spring.bankapplication.constant.ValidationMessageConstants.EMAIL_REGEX_MESSAGE;

@Pattern(regexp = EMAIL_REGEX, message = EMAIL_REGEX_MESSAGE)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@NotBlank
@Constraint(validatedBy = {UniqueEmailValidator.class})
public @interface Email {

    String message() default "Email must be unique";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
