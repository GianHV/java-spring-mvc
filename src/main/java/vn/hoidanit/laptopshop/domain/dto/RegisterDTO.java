package vn.hoidanit.laptopshop.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import vn.hoidanit.laptopshop.service.validator.RegisterChecked;

/**
 * Data Transfer Object (DTO) for user registration.
 * This class is used to hold the data for a user's registration process.
 */
@RegisterChecked
public class RegisterDTO {

    /**
     * First name of the user.
     * This field is validated to ensure it has a minimum length of 3 characters.
     */
    @Size(min = 3, message = "FirstName phải có tối thiểu 3 ký tự")
    private String firstName;

    /**
     * Last name of the user.
     */
    private String lastName;

    /**
     * Email of the user, must match the specified regex for valid email format.
     */
    @Email(message = "Email không hợp lệ", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private String email;

    /**
     * Password of the user.
     */
    private String password;

    /**
     * Confirm password field, must match the password and have a minimum of 3 characters.
     */
    @Size(min = 3, message = "confirmPassword phải có tối thiểu 3 ký tự")
    private String confirmPassword;

    /**
     * Gets the first name of the user.
     * @return the first name of the user.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the user.
     * @param firstName the first name to be set for the user.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the last name of the user.
     * @return the last name of the user.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the user.
     * @param lastName the last name to be set for the user.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the email of the user.
     * @return the email of the user.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the user.
     * @param email the email to be set for the user.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the password of the user.
     * @return the password of the user.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     * @param password the password to be set for the user.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the confirm password of the user.
     * @return the confirm password of the user.
     */
    public String getConfirmPassword() {
        return confirmPassword;
    }

    /**
     * Sets the confirm password of the user.
     * @param confirmPassword the confirm password to be set for the user.
     */
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
