package com.w_farooq_group.user_service.controller;

import com.w_farooq_group.user_service.constants.ResponseConstants;
import com.w_farooq_group.user_service.constants.StatusConstants;
import com.w_farooq_group.user_service.dto.ErrorResponseDto;
import com.w_farooq_group.user_service.dto.ResponseDto;
import com.w_farooq_group.user_service.dto.UserDto;
import com.w_farooq_group.user_service.dto.UserProfileDTO;
import com.w_farooq_group.user_service.requests.RegistrationRequest;
import com.w_farooq_group.user_service.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(
        name = "CRUD REST API FOR USERS & USER PROFILES"
)
@RestController
@RequestMapping(value = "/api/v1/users", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class UserController {

    private final IUserService iUserService;

    public UserController(IUserService iUserService) {
        this.iUserService = iUserService;
    }

    @Operation(
            summary = "Create User REST API",
            description = "creates a user and the user profiles"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status Created"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "HTTP Status Bad Request",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })

    @PostMapping(value = "/register")
    public ResponseEntity<ResponseDto> registerUser (@Valid @RequestBody RegistrationRequest registrationRequest) {
        iUserService.registerUser(registrationRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(ResponseConstants.RESPONSE_201, StatusConstants.STATUS_201));
    }

    @Operation(
            summary = "GET user REST API",
            description = "fetches a single user by id"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "HTTP Status Not Found",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })

    @GetMapping("/fetch/{userId}")
    public ResponseEntity<UserDto> fetchUser (@PathVariable(value = "userId") UUID userId) {
        UserDto userDto = iUserService.fetchUser(userId);
        return ResponseEntity.status(HttpStatus.OK).body(userDto);
    }

    @Operation(summary = "GET All Users REST API", description = "fetches all users")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Internal Server Error",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            )
    })

    @GetMapping(value = "/fetch")
    public ResponseEntity<List<UserDto>> fetchAllUsers() {
        List<UserDto> userDtoList = iUserService.fetchAllUsers();
        return new ResponseEntity<>(userDtoList, HttpStatus.OK);
    }

    @Operation(
            summary = "UPDATE User REST API",
            description = "updates a users profile"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "HTTP Status Expectation Failed",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @PutMapping(value = "/update/{userId}")
    public ResponseEntity<ResponseDto> updateUser (@Valid @RequestBody UserProfileDTO userProfileDTO, @PathVariable UUID userId) {
        boolean isUpdated = iUserService.updateUserProfile(userProfileDTO, userId);
        return isUpdated ? ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDto(ResponseConstants.RESPONSE_200, StatusConstants.STATUS_200)) :
                ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                        .body(new ResponseDto(ResponseConstants.RESPONSE_417_UPDATE, StatusConstants.STATUS_417));
    }

    @Operation(
            summary = "Delete User REST API",
            description = "deletes a user and the user profile"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "HTTP Status Expectation Failed",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @DeleteMapping(value = "/delete/{userId}")
    public ResponseEntity<ResponseDto> deleteUser (@PathVariable UUID userId) {
        boolean isDeleted = iUserService.deleteUserById(userId);
        return isDeleted ? ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDto(ResponseConstants.RESPONSE_200, StatusConstants.STATUS_200)) :
        ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                .body(new ResponseDto(ResponseConstants.RESPONSE_417_DELETE, StatusConstants.STATUS_417));
    }
}
