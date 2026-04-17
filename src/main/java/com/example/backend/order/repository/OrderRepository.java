package com.example.backend.order.repository;

import com.example.backend.order.domain.entity.Order;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepositoryImplementation<Order, Integer> {
    List<Order> findAllByCustomerId(Integer customerId);
}
