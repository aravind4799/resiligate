package com.microservices.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
public class CustomerDetailsDto {

    @NotEmpty(message = "name is required")
    @Size(min = 2, message = "name should have at least 2 characters")
    private String name;
    @NotEmpty(message = "Email is required")
    @Email(message = "Invalid email address")
    private String email;
    @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
    private String mobileNumber;
    private AccountsDto accountsDto;
    private LoansDto loansDto;
    private CardsDto cardsDto;
}
