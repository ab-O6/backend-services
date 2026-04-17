package com.example.backend.catalogue.repository;

import com.example.backend.catalogue.domain.entity.CatalogueItem;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogueItemRepository extends JpaRepositoryImplementation<CatalogueItem, Integer> {
}
