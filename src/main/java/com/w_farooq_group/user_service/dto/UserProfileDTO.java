package com.w_farooq_group.user_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import java.util.UUID;
@Schema(
        name = "UserProfile",
        description = "Schema to hold user profile information"
)
public class UserProfileDTO {
    @Schema(description = "userId that the profile belongs to", example = "1234-2456-23456-4567")
    private UUID userId;
    @Schema(description = "bio to brief info about user", example = "Hello i am cool")
    @NotEmpty(message = "bio can not be empty")
    @Length(min = 5, max = 100, message = "bio can not be less then 5 or more then 100 characters")
    private String bio;

    public UserProfileDTO (UUID userId, String bio) {
        this.userId = userId;
        this.bio = bio;
    }
    public UserProfileDTO () {}

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public @NotEmpty(message = "bio can not be empty") @Length(min = 5, max = 100, message = "bio can not be less then 5 or more then 100 characters") String getBio() {
        return bio;
    }

    public void setBio(@NotEmpty(message = "bio can not be empty") @Length(min = 5, max = 100, message = "bio can not be less then 5 or more then 100 characters") String bio) {
        this.bio = bio;
    }
}
