package vn.hoidanit.laptopshop.service.validator;

import org.springframework.stereotype.Service;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import vn.hoidanit.laptopshop.domain.dto.RegisterDTO;
import vn.hoidanit.laptopshop.service.UserService;

/*
 * Custom validator class for validating the user registration process.
 * This class implements the `ConstraintValidator` interface and performs custom validation logic 
 */
@Service
public class RegisterValidator implements ConstraintValidator<RegisterChecked, RegisterDTO> {

    private final UserService userService;

    /*
     * Constructor to inject the `UserService` dependency.
     * This service is used to check if the email already exists in the system.
     * 
     * @param userService the service used for email validation.
     */
    public RegisterValidator(UserService userService) {
        this.userService = userService;
    }

    /*
     * Validates the provided `RegisterDTO` object.
     * @param user the `RegisterDTO` object containing user registration data.
     * @param context the context in which the validation takes place.
     * @return true if the object is valid, false otherwise.
     */
    @Override
    public boolean isValid(RegisterDTO user, ConstraintValidatorContext context) {
        boolean valid = true;

        // Check if password fields match
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            context.buildConstraintViolationWithTemplate("Passwords nhập không chính xác")
                    .addPropertyNode("confirmPassword")
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
            valid = false;
        }

        // Additional validations can be added here
        // check email
        if (this.userService.checkEmailExist(user.getEmail())) {
            context.buildConstraintViolationWithTemplate("Email đã tồn tại")
                    .addPropertyNode("email")
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
            valid = false;
        }

        return valid;
    }
}
