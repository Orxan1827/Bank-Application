package az.spring.bankapplication.constraint;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.lang.annotation.*;

import static az.spring.bankapplication.constant.ValidationMessageConstants.PASSWORD_REGEX;
import static az.spring.bankapplication.constant.ValidationMessageConstants.PASSWORD_REGEX_MESSAGE;

@Pattern(regexp = PASSWORD_REGEX, message = PASSWORD_REGEX_MESSAGE)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@NotBlank
@Size(min = 7, max = 15)
@Constraint(validatedBy = {})
public @interface Password {

    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
