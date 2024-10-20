package com.w_farooq_group.user_service.service;

import com.w_farooq_group.user_service.dto.UserDto;
import com.w_farooq_group.user_service.dto.UserProfileDto;
import com.w_farooq_group.user_service.request.LoginRequest;
import com.w_farooq_group.user_service.request.RegistrationRequest;

import java.util.UUID;

public interface IBaseUserService {

    /**
     *
     * @param registrationRequest - RegistrationRequest Object
     */
    void createUser (RegistrationRequest registrationRequest);

    /**
     *
     * @param loginRequest - LoginRequest Object
     * @return String based on login result
     */
    String login (LoginRequest loginRequest);

    /**
     *
     * @param userProfileDto - UserProfileDto Object
     * @return True or False based on updation
     */
    Boolean updateProfile (UserProfileDto userProfileDto);

    /**
     *
     * @param id - UUID
     * @return UserDto Object
     */
    UserDto getUser (UUID id);

    /**
     *
     * @param id - UUID
     * @return True or False based on Delete
     */
    Boolean deleteUser (UUID id);
}
