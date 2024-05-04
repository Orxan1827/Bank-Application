package az.spring.bankapplication.constraint;

import az.spring.bankapplication.constraint.validator.UniquePinCodeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = {UniquePinCodeValidator.class})
@Target(value = FIELD)
@Retention(value =RUNTIME)
@NotBlank
@Size(min = 5,max = 7)
public @interface UniquePinCode {

    String message() default "Pin code must be unique";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
