package com.microservices.accounts.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(name = "Response", description = "schema to hold response details")
@AllArgsConstructor
public class ResponseDto {
    @Schema(description = "Status code")
    private String statusMsg;
    private String statusCode;
}
