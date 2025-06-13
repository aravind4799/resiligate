package com.microservices.accounts.service.client;


import com.microservices.accounts.dto.LoansDto;
import jakarta.validation.constraints.Pattern;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "loans",fallback = LoansFallback.class)
public interface LoansFeignClient {
    @GetMapping("loans/v1/fetch")
    public ResponseEntity<LoansDto> fetchLoanDetails(@RequestHeader("microservice-correlation-id") String correlationId,@RequestParam String mobileNumber);
}
