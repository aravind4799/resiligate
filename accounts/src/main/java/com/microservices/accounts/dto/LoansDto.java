package com.microservices.accounts.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class LoansDto {

    @NotEmpty(message = "Mobile Number is required")
    @Pattern(regexp = "\\d{10}", message = "Mobile Number must be 10 digits")
    private String mobileNumber;

    @NotEmpty(message = "Loan Number is required")
    @Pattern(regexp = "\\d{12}", message = "Loan Number must be 12 digits")
    private String loanNumber;

    @NotEmpty(message = "Loan Type is required")
    private String loanType;

    @Positive(message = "Total Loan must be a positive number")
    private int totalLoan;

    @PositiveOrZero(message = "Amount Paid must be a non-negative number")
    private int amountPaid;

    @PositiveOrZero(message = "Outstanding Amount must be a non-negative number")
    private int outstandingAmount;
}
