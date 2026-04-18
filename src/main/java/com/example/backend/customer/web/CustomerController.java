package com.example.backend.customer.web;

import com.example.backend.customer.domain.dto.CreateCustomerRequest;
import com.example.backend.customer.domain.dto.CustomerDto;
import com.example.backend.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Customer Management", description = "Endpoints for managing customers")
@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @Operation(
        summary = "Create a new customer",
        description = """
            Registers a new **Customer** in the e-commerce system.
            
            ### Request Example
            ```json
            {
              "firstName": "Rahul",
              "lastName": "Sharma",
              "email": "rahul.sharma@example.in",
              "phoneNumber": "+919876543210"
            }
            ```
            """
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer createCustomer(@RequestBody CreateCustomerRequest request) {
        return customerService.createCustomer(request);
    }

    @Operation(
        summary = "Get customer details",
        description = "Retrieves the details of a customer by their unique ID."
    )
    @GetMapping("/{id}")
    public CustomerDto getCustomer(@PathVariable Integer id) {
        return customerService.getCustomer(id);
    }
}
