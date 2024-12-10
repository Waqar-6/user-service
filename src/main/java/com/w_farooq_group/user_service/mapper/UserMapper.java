package com.w_farooq_group.user_service.mapper;

import com.w_farooq_group.user_service.entity.User;
import com.w_farooq_group.user_service.requests.RegistrationRequest;

public final class UserMapper {

    private UserMapper() {}

    public static User registrationRequestToUser( RegistrationRequest registrationRequest, User user ) {
        user.setFirstName(registrationRequest.getFirstName());
        user.setLastName(registrationRequest.getLastName());
        user.setEmail(registrationRequest.getEmail());
        user.setPassword(registrationRequest.getPassword());
        user.setRole(registrationRequest.getUserRole());
        return user;
    }
}
