package com.w_farooq_group.user_service.dto;

import java.util.UUID;

public class UserDto {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private UserProfileDTO userProfileDTO;

    public UserDto (UUID id, String firstName, String lastName, String email, UserProfileDTO userProfileDTO) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userProfileDTO = userProfileDTO;
    }

    public UserDto () {}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserProfileDTO getUserProfileDTO() {
        return userProfileDTO;
    }

    public void setUserProfileDTO(UserProfileDTO userProfileDTO) {
        this.userProfileDTO = userProfileDTO;
    }
}
