package com.example.backend.customer.service;

import com.example.backend.customer.api.CustomerApi;
import com.example.backend.customer.domain.dto.CreateCustomerRequest;
import com.example.backend.customer.domain.dto.CustomerDto;
import com.example.backend.customer.domain.entity.Customer;
import com.example.backend.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomerService implements CustomerApi {

    private final CustomerRepository customerRepository;

    @Override
    @Transactional(readOnly = true)
    public CustomerDto getCustomer(Integer id) {
        return customerRepository.findById(id)
                .map(c -> new CustomerDto(c.getId(), c.getFirstName(), c.getLastName(), c.getEmail()))
                .orElse(null);
    }
    
    @Transactional
    public Integer createCustomer(CreateCustomerRequest request) {
        if (customerRepository.findByEmail(request.email()).isPresent()) {
            throw new IllegalArgumentException("Customer with email already exists");
        }
        Customer customer = new Customer(
                request.firstName(), 
                request.lastName(), 
                request.email(), 
                request.phoneNumber()
        );
        return customerRepository.save(customer).getId();
    }
}
