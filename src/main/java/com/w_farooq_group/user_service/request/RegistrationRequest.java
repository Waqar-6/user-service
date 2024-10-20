package com.w_farooq_group.user_service.request;

import com.w_farooq_group.user_service.constants.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RegistrationRequest {
    @NotEmpty(message = "email can not be empty")
    @Email(message = "has to be valid format")
    private String email;
    @NotEmpty(message = "password can not be empty")
    @Size(min = 3, message = "password has to be min 3 characters")
    private String password;
    private Role role;
}
