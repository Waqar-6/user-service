package com.w_farooq_group.user_service.service.impl;

import com.w_farooq_group.user_service.dto.UserDto;
import com.w_farooq_group.user_service.dto.UserProfileDto;
import com.w_farooq_group.user_service.entity.BaseUserEntity;
import com.w_farooq_group.user_service.entity.UserProfile;
import com.w_farooq_group.user_service.exception.ResourceNotFoundException;
import com.w_farooq_group.user_service.exception.UserAlreadyExistsException;
import com.w_farooq_group.user_service.mapper.BaseUserMapper;
import com.w_farooq_group.user_service.mapper.UserProfileMapper;
import com.w_farooq_group.user_service.repository.BaseUserEntityRepository;
import com.w_farooq_group.user_service.repository.UserProfileRepository;
import com.w_farooq_group.user_service.request.LoginRequest;
import com.w_farooq_group.user_service.request.RegistrationRequest;
import com.w_farooq_group.user_service.service.IBaseUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.file.ReadOnlyFileSystemException;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class BaseUserServiceImpl implements IBaseUserService {

    private final BaseUserEntityRepository baseUserEntityRepository;
    private final UserProfileRepository userProfileRepository;
    /**
     * @param registrationRequest - RegistrationRequest Object
     */
    @Override
    public void createUser(RegistrationRequest registrationRequest) {
        Boolean userExists = baseUserEntityRepository.existsByEmail(registrationRequest.getEmail());
        if (userExists)
            throw new UserAlreadyExistsException("User with the email " + registrationRequest.getEmail() + " already exists");
        BaseUserEntity user = new BaseUserEntity();
        createNewUser(user, registrationRequest);
    }

    /**
     * @param loginRequest - LoginRequest Object
     * @return String based on login result
     */
    @Override
    public String login(LoginRequest loginRequest) {
        BaseUserEntity user = baseUserEntityRepository.findByEmail(loginRequest.getEmail()).orElseThrow(() -> new ResourceNotFoundException("User", "email", loginRequest.getEmail()));
        boolean isCorrectPassword = user.getPassword().equals(loginRequest.getPassword());
        return isCorrectPassword ?  "logged in" : "incorrect password";
    }


    private void createNewUser(BaseUserEntity user, RegistrationRequest registrationRequest) {
        user.setEmail(registrationRequest.getEmail());
        user.setPassword(registrationRequest.getPassword());
        user.setRole(registrationRequest.getRole());
        user.setCreatedAt(LocalDateTime.now());
        UserProfile userProfile = new UserProfile();
        user.setUserProfile(userProfile);
        baseUserEntityRepository.save(user);
    }



    /**
     * @param userProfileDto - UserProfileDto Object
     * @return True or False based on updation
     */
    @Override
    public Boolean updateProfile(UserProfileDto userProfileDto) {
        UserProfile userProfile = userProfileRepository.findById(userProfileDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User profile", "userProfileId", userProfileDto.getId().toString()));

        UserProfile updatedUserProfile = UserProfileMapper.mapToUserProfile(userProfileDto, userProfile);
        userProfileRepository.save(updatedUserProfile);
        return true;
    }

    /**
     * @param id - UUID
     * @return UserDto Object
     */
    @Override
    public UserDto getUser(UUID id) {
        BaseUserEntity user = baseUserEntityRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "userId", id.toString()));
        UserProfile userProfile = user.getUserProfile();
        UserProfileDto userProfileDto = UserProfileMapper.mapToUserProfileDto(userProfile, new UserProfileDto());
        UserDto userDto = BaseUserMapper.mapToUserDto(user, new UserDto());
        userDto.setUserProfileDto(userProfileDto);
        return userDto;
    }

    /**
     * @param id - UUID
     * @return True or False based on Delete
     */
    @Override
    public Boolean deleteUser(UUID id) {
        BaseUserEntity user = baseUserEntityRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "userId", id.toString()));
        baseUserEntityRepository.delete(user);
        return true;
    }


}
