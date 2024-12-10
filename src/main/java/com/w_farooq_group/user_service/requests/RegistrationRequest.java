package com.w_farooq_group.user_service.requests;

import com.w_farooq_group.user_service.enums.UserRole;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


@Schema(
        description = "Schema to hold user information for registration"
)
public class RegistrationRequest {
    @Schema(description = "users first name", example = "Pathan")
    @NotNull(message = "first name can not be empty")
    @Size(min = 3, max = 30, message = "first name has to be between 3-30 characters")
    private String firstName;
    @Schema(description = "users last name", example = "Khan")
    @NotNull(message = "first name can not be empty")
    @Size(min = 3, max = 30, message = "last name has to be between 3-30 characters")
    private String lastName;
    @Schema(description = "users email", example = "pathankhan@gmail.com")
    @NotEmpty(message = "Email can not be empty")
    @Email(message = "email has to be valid")
    private String email;
    @Schema(description = "users password")
    @NotEmpty(message = "Password can not be empty")
    @Size(min = 4, max = 20, message = "password has to be between 4-20 characters")
    private String password;

    private UserRole userRole;

    public RegistrationRequest(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.userRole = UserRole.USER;
    }

    public RegistrationRequest () {}

    public @NotNull(message = "first name can not be empty") @Size(min = 3, max = 30, message = "first name has to be between 3-30 characters") String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NotNull(message = "first name can not be empty") @Size(min = 3, max = 30, message = "first name has to be between 3-30 characters") String firstName) {
        this.firstName = firstName;
    }

    public @NotNull(message = "first name can not be empty") @Size(min = 3, max = 30, message = "last name has to be between 3-30 characters") String getLastName() {
        return lastName;
    }

    public void setLastName(@NotNull(message = "first name can not be empty") @Size(min = 3, max = 30, message = "last name has to be between 3-30 characters") String lastName) {
        this.lastName = lastName;
    }

    public @NotEmpty(message = "Email can not be empty") @Email(message = "email has to be valid") String getEmail() {
        return email;
    }

    public void setEmail(@NotEmpty(message = "Email can not be empty") @Email(message = "email has to be valid") String email) {
        this.email = email;
    }

    public @NotEmpty(message = "Password can not be empty") @Size(min = 4, max = 20, message = "password has to be between 4-20 characters") String getPassword() {
        return password;
    }

    public void setPassword(@NotEmpty(message = "Password can not be empty") @Size(min = 4, max = 20, message = "password has to be between 4-20 characters") String password) {
        this.password = password;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }
}
