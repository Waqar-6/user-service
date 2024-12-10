package com.w_farooq_group.user_service.mapper;

import com.w_farooq_group.user_service.dto.UserProfileDTO;
import com.w_farooq_group.user_service.entity.UserProfile;

public final class UserProfileMapper {

    private UserProfileMapper() {}

    public static UserProfileDTO userProfileToUserProfileDTO(UserProfile userProfile, UserProfileDTO userProfileDTO) {
        userProfileDTO.setUserId(userProfile.getUserId());
        userProfileDTO.setBio(userProfile.getBio());
        return userProfileDTO;
    }

    public static UserProfile userProfileDTOToUserProfile(UserProfileDTO userProfileDTO, UserProfile userProfile) {
        userProfile.setBio(userProfileDTO.getBio());
        return userProfile;
    }
}
