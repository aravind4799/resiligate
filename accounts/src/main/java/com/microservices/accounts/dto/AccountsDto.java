package com.microservices.accounts.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Accounts", description = "Account details")
public class AccountsDto
{
    @Pattern(regexp = "[0-9]{10}", message = "Account number must be 10 digits")
    @NotEmpty(message = "Account number is required")
    @Schema(description = "Account number", example = "1234567890")
    private Long accountNumber;
    @Schema(description = "Account type", example = "Savings")
    @NotEmpty(message = "Account type is required")
    private String accountType;
    @Schema(description = "Branch address", example = "Pune")
    @NotEmpty(message = "Branch address is required")
    private String branchAddress;
}
