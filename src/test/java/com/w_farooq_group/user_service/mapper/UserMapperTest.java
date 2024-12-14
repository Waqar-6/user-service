package com.w_farooq_group.user_service.mapper;

import com.w_farooq_group.user_service.entity.User;
import com.w_farooq_group.user_service.enums.UserRole;
import com.w_farooq_group.user_service.requests.RegistrationRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserMapperTest {

    @Test
    public void registrationRequestToUserTest () {
        // Arrange
        RegistrationRequest registrationRequest = new RegistrationRequest("John", "Doe", "johndoe@gmail.com","123", UserRole.USER);
        User user = new User();
        // Act
        UserMapper.registrationRequestToUser(registrationRequest, user);
        // Assert
        assertNotNull(user, "test failed");
        assertEquals("John", user.getFirstName(), "expected John but got " + user.getFirstName());
        assertEquals("Doe", user.getLastName(), "expected Doe but got " + user.getLastName());
        assertEquals("johndoe@gmail.com", user.getEmail(), "expected johndoe@gmail.com but got " + user.getEmail());
        assertEquals("123", user.getPassword(), "expected 123 but got " + user.getPassword());
        assertEquals(UserRole.USER, user.getRole(), "expected role USER but got " + user.getRole());
    }
}
