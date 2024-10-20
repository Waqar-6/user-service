package com.w_farooq_group.user_service.mapper;

import com.w_farooq_group.user_service.dto.UserProfileDto;
import com.w_farooq_group.user_service.entity.UserProfile;

public final class UserProfileMapper {

    private UserProfileMapper() {}

    public static UserProfileDto mapToUserProfileDto(UserProfile userProfile, UserProfileDto userProfileDto) {
        userProfileDto.setId(userProfile.getId());
        userProfileDto.setFirstName(userProfile.getFirstName());
        userProfileDto.setLastName(userProfile.getLastName());
        userProfileDto.setBio(userProfile.getBio());
        userProfileDto.setProfilePictureUrl(userProfile.getProfilePictureUrl());
        return userProfileDto;
    }

    public static UserProfile mapToUserProfile(UserProfileDto userProfileDto, UserProfile userProfile) {
        userProfile.setFirstName(userProfileDto.getFirstName());
        userProfile.setLastName(userProfileDto.getLastName());
        userProfile.setBio(userProfileDto.getBio());
        userProfile.setProfilePictureUrl(userProfileDto.getProfilePictureUrl());
        return userProfile;
    }
}
