package com.microservices.accounts.service;

import com.microservices.accounts.dto.CustomerDto;


public interface IAccountsService {
    void createAccount(CustomerDto customerDto);
    boolean updateAccount(CustomerDto customerDto);
    CustomerDto fetchAccount(String mobileNumber);
    boolean deleteAccount(String mobileNumber);
}
