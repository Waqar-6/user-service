package com.w_farooq_group.user_service.service.impl;

import com.w_farooq_group.user_service.dto.UserDto;
import com.w_farooq_group.user_service.dto.UserProfileDTO;
import com.w_farooq_group.user_service.entity.User;
import com.w_farooq_group.user_service.entity.UserProfile;
import com.w_farooq_group.user_service.exceptions.AlreadyExistsException;
import com.w_farooq_group.user_service.exceptions.ResourceNotFoundException;
import com.w_farooq_group.user_service.mapper.UserMapper;
import com.w_farooq_group.user_service.mapper.UserProfileMapper;
import com.w_farooq_group.user_service.repository.UserProfileRepository;
import com.w_farooq_group.user_service.repository.UserRepository;
import com.w_farooq_group.user_service.requests.RegistrationRequest;
import com.w_farooq_group.user_service.service.IUserService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;
    private final UserProfileRepository userProfileRepository;

    public UserServiceImpl(UserRepository userRepository, UserProfileRepository userProfileRepository) {
        this.userRepository = userRepository;
        this.userProfileRepository = userProfileRepository;
    }

    /**
     * @param registrationRequest - RegistrationRequest Object
     */
    @Transactional
    @Override
    public void registerUser(RegistrationRequest registrationRequest) {
        Boolean existsByEmail = userRepository.existsByEmail(registrationRequest.getEmail());
        if (existsByEmail) throw new AlreadyExistsException("user", "email",registrationRequest.getEmail());
        User newUser = UserMapper.registrationRequestToUser(registrationRequest, new User());
        userRepository.save(newUser);
        createAndSaveUserProfile(newUser);
        System.out.println("uuid generated: " + newUser.getId());

    }

    /**
     * @param userId - UUID
     * @return - UserDto Object
     */
    @Override
    public UserDto fetchUser(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId.toString()));
        UserProfile userProfile = userProfileRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("UserProfile", "id", userId.toString()));

        UserDto userDto = UserMapper.userToUserDto(user, new UserDto());
        UserProfileDTO userProfileDTO = UserProfileMapper
                .userProfileToUserProfileDTO(userProfile, new UserProfileDTO());
        userDto.setUserProfileDTO(userProfileDTO);
        return userDto;
    }

    /**
     * @return List of UserDto Objects
     */
    @Override
    public List<UserDto> fetchAllUsers() {
        List<User> allUsers = userRepository.findAll();
        List<UserDto> userDtoList = new ArrayList<>();
        for(User user : allUsers) {
            UserDto userDto = UserMapper.userToUserDto(user, new UserDto());
            UserProfile userProfile = userProfileRepository.findByUserId(user.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("UserProfile", "id", user.getId().toString()));
            UserProfileDTO userProfileDTO = UserProfileMapper.userProfileToUserProfileDTO(userProfile, new UserProfileDTO());
            userDto.setUserProfileDTO(userProfileDTO);
            userDtoList.add(userDto);
        }
        return userDtoList;
    }

    private void createAndSaveUserProfile (User user) {
        UserProfile userProfile = new UserProfile();
        userProfile.setUser(user);
        userProfileRepository.save(userProfile);
        System.out.println("user profile id: " + userProfile.getUserId());
    }

    /**
     * @param userId - UUID
     * @return - True or False based on delete
     */
    @Override
    public boolean deleteUserById(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId.toString()));
        UserProfile userProfile = userProfileRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("userprofile", "id", userId.toString()));
        userProfileRepository.delete(userProfile);
        userRepository.delete(user);
        return true;
    }

    /**
     * @param userProfileDTO - UserProfileDTO Object
     * @param userId         - UUID
     * @return - True or False based on update
     */
    @Override
    public boolean updateUserProfile(UserProfileDTO userProfileDTO, UUID userId) {
       UserProfile userProfile = userProfileRepository.findByUserId(userId)
               .orElseThrow(() -> new ResourceNotFoundException("profile", "id", userId.toString()));
       UserProfileMapper.userProfileDTOToUserProfile(userProfileDTO, userProfile);
       userProfileRepository.save(userProfile);
        return true;
    }
}
