package com.example.backend.inventory.repository;

import com.example.backend.inventory.domain.entity.InventoryLevel;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepositoryImplementation<InventoryLevel, Integer> {
    Optional<InventoryLevel> findByCatalogueItemId(Integer catalogueItemId);
}
