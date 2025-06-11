package com.microservices.accounts.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name="customer", description = "Customer details and account details")

public class CustomerDto {
    @NotEmpty(message = "name is required")
    @Size(min = 2, message = "name should have at least 2 characters")
    @Schema(description = "Customer name",example = "John Doe")
    private String name;
    @NotEmpty(message = "Email is required")
    @Email(message = "Invalid email address")
    @Schema(description = "Customer email",example = "V4t0V@example.com")
    private String email;
    @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
    @Schema(description = "Customer mobile number",example = "1234567890")
    private String mobileNumber;

    private AccountsDto accountsDto;
}
