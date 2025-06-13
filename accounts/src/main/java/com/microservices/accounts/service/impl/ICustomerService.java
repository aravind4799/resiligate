package com.microservices.accounts.service.impl;

import com.microservices.accounts.dto.CustomerDetailsDto;

public interface ICustomerService {
    CustomerDetailsDto fetchCustomerDetails(String mobileNumber,String correlationId);
}
