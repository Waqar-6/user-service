package com.w_farooq_group.user_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Schema(
        name = "Error Response",
        description = "Schema to hold error response information"
)
@Data @AllArgsConstructor @NoArgsConstructor
public class ErrorResponseDto {
    @Schema(description = "Api path invoked")
    private String apiPath;
    @Schema(description = "Error code representing the error")
    private HttpStatus errorCode;
    @Schema(description = "Error message of the error that occurred")
    private String errorMessage;
    @Schema(description = "Time error occurred")
    private LocalDateTime errorTime;
}
