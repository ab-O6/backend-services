package com.example.backend.order.repository;

import com.example.backend.order.domain.entity.Order;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepositoryImplementation<Order, Integer> {}
