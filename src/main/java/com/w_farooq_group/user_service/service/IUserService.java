package com.w_farooq_group.user_service.service;

import com.w_farooq_group.user_service.dto.UserProfileDTO;
import com.w_farooq_group.user_service.requests.RegistrationRequest;

import java.util.UUID;

public interface IUserService {

    /**
     *
     * @param registrationRequest - RegistrationRequest Object
     */
    void registerUser(RegistrationRequest registrationRequest);

    /**
     *
     * @param userId - UUID
     * @return - True or False based on delete
     */
    boolean deleteUserById (UUID userId);


    /**
     *
     * @param userProfileDTO - UserProfileDTO Object
     * @param userId - UUID
     * @return - True or False based on update
     */
    boolean updateUserProfile (UserProfileDTO userProfileDTO, UUID userId);

}
