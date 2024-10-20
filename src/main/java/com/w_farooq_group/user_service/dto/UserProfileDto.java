package com.w_farooq_group.user_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@Schema(
        name = "UserProfile",
        description = "To Hold user profile details"
)
@Data @AllArgsConstructor @NoArgsConstructor
public class UserProfileDto {
    private UUID id;
    @Schema(description = "hold users first name")
    @NotEmpty(message = "firstname can not be empty")
    @Size(min = 3, message = "firstname has to have minimum 3 characters")
    private String firstName;
    @Schema(description = "hold users last name")
    @NotEmpty(message = "lastname can not be empty")
    @Size(min = 3, message = "lastname has to have minimum 3 characters")
    private String lastName;
    @Schema(description = "hold users bio")
    @NotEmpty(message = "bio can not be empty")
    private String bio;
    @Schema(description = "holds users profile image url")
    private String profilePictureUrl;
}
