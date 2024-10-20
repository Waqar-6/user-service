package com.w_farooq_group.user_service.dto;

import com.w_farooq_group.user_service.constants.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@Schema(
        name = "User",
        description = "Schema to hold user information"
)
@Data @AllArgsConstructor @NoArgsConstructor
public class UserDto {
    private UUID id;
    @Schema(description = "Holds user's email")
    @NotEmpty(message = "email can not be empty")
    @Email(message = "email has to be in a valid format")
    private String email;
    @Schema(description = "Holds user's role")
    private Role role;
    @Schema(description = "Holds user's profile details")
    private UserProfileDto userProfileDto;
}
