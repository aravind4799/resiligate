package com.microservices.accounts.service.client;


import com.microservices.accounts.dto.CardsDto;
import jakarta.validation.constraints.Pattern;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "cards",fallback = CardsFallback.class)
public interface CardsFeignClient {
    @GetMapping("cards/v1/fetch")
    public ResponseEntity<CardsDto> fetchCardDetails(@RequestHeader("microservice-correlation-id") String correlationId,@RequestParam String mobileNumber);
}
