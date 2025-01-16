package vn.hoidanit.laptopshop.service.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
/*
 * Custom annotation used for validating a user registration object.
 * This annotation is used at the class level and is tied to the `RegisterValidator` class 
 * for the validation logic implementation.
 */
@Constraint(validatedBy = RegisterValidator.class)
@Target({ ElementType.TYPE }) // Adjusted to apply to the class level
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RegisterChecked {
    /*
     * The default error message that will be used if validation fails.
     */
    String message() default "User register validation failed";
    /*
     * Groups allow the annotation to be applied in different validation scenarios.
     * This is left empty by default.
     */
    Class<?>[] groups() default {};
    /*
     * Payload is used to carry metadata information about the annotation, 
     * typically used for carrying additional data during validation.
     */
    Class<? extends Payload>[] payload() default {};
}
