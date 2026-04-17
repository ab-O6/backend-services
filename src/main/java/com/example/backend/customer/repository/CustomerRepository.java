package com.example.backend.customer.repository;

import com.example.backend.customer.domain.entity.Customer;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepositoryImplementation<Customer, Integer> {
    Optional<Customer> findByEmail(String email);
}
