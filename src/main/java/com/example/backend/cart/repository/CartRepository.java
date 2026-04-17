package com.example.backend.cart.repository;

import com.example.backend.cart.domain.entity.Cart;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepositoryImplementation<Cart, Integer> {
    Optional<Cart> findByCustomerId(Integer customerId);
}
