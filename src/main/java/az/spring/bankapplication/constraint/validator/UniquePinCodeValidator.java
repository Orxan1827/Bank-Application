package az.spring.bankapplication.constraint.validator;

import az.spring.bankapplication.constraint.UniquePinCode;
import az.spring.bankapplication.repository.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UniquePinCodeValidator implements ConstraintValidator<UniquePinCode, String> {

    private final UserRepository userRepository;

    @Override
    public boolean isValid(String pinCode, ConstraintValidatorContext context) {
        return !userRepository.existsUserByPinCode(pinCode);
    }

}
