package com.microservices.accounts.controller;


import com.microservices.accounts.dto.CustomerDetailsDto;
import com.microservices.accounts.service.impl.ICustomerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping(path = "/accounts/v1",produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
@Validated
@Tag(name = " REST API for customer", description = "CREATE,READ,UPDATE,DELETE ACCOUNT DETAILS")
public class CustomerController {

    private final ICustomerService iCustomerService;
    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);



    @GetMapping("/fetchCustomerDetails")
    public ResponseEntity<CustomerDetailsDto> fetchCustomerDetails(@RequestHeader("microservice-correlation-id") String correlationId,@RequestParam
                                                                   @Pattern(regexp = "[0-9]{10}",message = "Mobile number must be 10 digits")
                                                                   String mobileNumber
                                                                     ){
        logger.debug("microservice-correlation-id found:{}",correlationId);
        CustomerDetailsDto customerDetailsDto = iCustomerService.fetchCustomerDetails(mobileNumber,correlationId);
        return ResponseEntity.status(HttpStatus.OK).body(customerDetailsDto);
    }
}
