package com.w_farooq_group.user_service.mapper;

import com.w_farooq_group.user_service.dto.UserDto;
import com.w_farooq_group.user_service.entity.BaseUserEntity;

public final class BaseUserMapper {

    private  BaseUserMapper () {}

    public static UserDto mapToUserDto (BaseUserEntity baseUser, UserDto userDto) {
        userDto.setId(baseUser.getId());
        userDto.setEmail(baseUser.getEmail());
        userDto.setRole(baseUser.getRole());
        return userDto;
    }

}
