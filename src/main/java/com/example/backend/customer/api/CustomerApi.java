package com.example.backend.customer.api;

import com.example.backend.customer.domain.dto.CustomerDto;

public interface CustomerApi {
    CustomerDto getCustomer(Integer id);
}
