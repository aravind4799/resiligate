package com.microservices.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Schema(description = "schema to represent Error Response",name = "ErrorResponse")
public class ErrorResponseDto {
    @Schema(description = "API Path",example = "/accounts/fetch")
    private String apiPath;
    @Schema(description = "Error Code",example = "500")
    private HttpStatus errorCode;
    @Schema(description = "Error Message",example = "Internal Server Error")
    private String errorMessage;
    @Schema(description = "Error Time",example = "2023-01-01T00:00:00")
    private LocalDateTime errorTime;
}
