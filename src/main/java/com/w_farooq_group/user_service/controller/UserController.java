package com.w_farooq_group.user_service.controller;

import com.w_farooq_group.user_service.constants.UserConstants;
import com.w_farooq_group.user_service.dto.ErrorResponseDto;
import com.w_farooq_group.user_service.dto.ResponseDto;
import com.w_farooq_group.user_service.dto.UserDto;
import com.w_farooq_group.user_service.dto.UserProfileDto;
import com.w_farooq_group.user_service.request.LoginRequest;
import com.w_farooq_group.user_service.request.RegistrationRequest;
import com.w_farooq_group.user_service.service.IBaseUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(
        name = "CRUD and AUTH REST API FOR USERS",
        description = "authentication and user profile management rest api"
)
@RestController
@RequestMapping(value = "/api/users", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class UserController {

    private final IBaseUserService baseUserService;

    @Operation(
            summary = "Create REST API",
            description = "api endpoint for user registration"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP STATUS CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP STATUS INTERNAL SERVER ERROR",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @PostMapping("/register")
    public ResponseEntity<ResponseDto> createNewUser (@Valid @RequestBody RegistrationRequest registrationRequest) {
        baseUserService.createUser(registrationRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(UserConstants.STATUS_201, UserConstants.MESSAGE_201));
    }


    @Operation(
            summary = "User Login Endpoint",
            description = "REST end point for user to login"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status INTERNAL SERVER ERROR",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @PostMapping("/login")
    public ResponseEntity<String> login (@Valid @RequestBody LoginRequest loginRequest) {
        String result = baseUserService.login(loginRequest);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @Operation(
            summary = "UPDATE REST API",
            description = "REST API to update existing user"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "HTTP Status EXPECTATION Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateUserProfile (@Valid @RequestBody UserProfileDto userProfileDto) {
        Boolean isUpdated = baseUserService.updateProfile(userProfileDto);
        return isUpdated ? ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(UserConstants.STATUS_200, UserConstants.MESSAGE_200_UPDATE)) :
                ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDto(UserConstants.STATUS_417, UserConstants.MESSAGE_417_UPDATE));
    }

    @Operation(
            summary = "FETCH REST API",
            description = "REST API to fetch user by id"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status INTERNAL SERVER ERROR",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })

    @GetMapping("/fetch/{id}")
    public ResponseEntity<UserDto> fetchUser (@PathVariable UUID id) {
        UserDto userDto = baseUserService.getUser(id);
        return ResponseEntity.status(HttpStatus.OK).body(userDto);

    }

    @Operation(
            summary = "DELETE REST API",
            description = "REST API to delete user by id"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status INTERNAL SERVER ERROR",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDto> deleteUser (@PathVariable UUID id) {
       Boolean isDeleted =  baseUserService.deleteUser(id);
       return isDeleted ?  ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(UserConstants.STATUS_200, UserConstants.MESSAGE_200_DELETE)):
        ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDto(UserConstants.STATUS_417, UserConstants.MESSAGE_417_DELETE));


    }
}
