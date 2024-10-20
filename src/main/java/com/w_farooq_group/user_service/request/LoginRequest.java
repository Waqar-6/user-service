package com.w_farooq_group.user_service.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginRequest {
    @NotEmpty(message = "email can not be mepty")
    @Email(message = "email has to be a valid email")
    private String email;
    @NotEmpty(message = "password can not be empty")
    @Size(min = 3, max = 10, message = "password has to be between 3-10 characters")
    private String password;
}
