package com.w_farooq_group.user_service.constants;

public final class UserConstants {

    private UserConstants() {}


    // HTTP STATUS CODES
    public static final String STATUS_200 = "200";
    public static final String STATUS_201 = "201";
    public static final String STATUS_417 = "417";
    public static final String STATUS_500 = "500";

    // RESPONSE MESSAGES
    public static String MESSAGE_200 = "Request processed successfully";
    public static String MESSAGE_201 = "User registered successfully";
    public static String MESSAGE_200_UPDATE = "Profile updated successfully";
    public static String MESSAGE_200_DELETE = "User deleted successfully";
    public static String MESSAGE_417_UPDATE = "Expectation failed could not update profile. please try again";
    public static String MESSAGE_417_DELETE = "Expectation failed could not delete profile. please try again";


}
