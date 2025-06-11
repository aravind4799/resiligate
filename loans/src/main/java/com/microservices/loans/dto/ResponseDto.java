package com.microservices.loans.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseDto {
    private String StatusCode;
    private String statusMsg;
}
