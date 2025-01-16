package vn.hoidanit.laptopshop.service.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

/*
 * Custom annotation used for validating strong passwords.
 */
@Constraint(validatedBy = StrongPasswordValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD }) 
@Retention(RetentionPolicy.RUNTIME)
@Documented 
public @interface StrongPassword {

    /*
     * The default error message that will be used if validation fails.
     * This message specifies the password requirements.
     */
    String message() default "Must be 8 characters long and combination of uppercase letters, lowercase letters, numbers, special characters.";

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

