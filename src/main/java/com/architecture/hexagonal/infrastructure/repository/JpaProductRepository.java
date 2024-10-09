package com.architecture.hexagonal.infrastructure.repository;

import com.architecture.hexagonal.infrastructure.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing ProductEntity instances.
 * This interface extends JpaRepository, providing CRUD operations for ProductEntity.
 */
@Repository
public interface JpaProductRepository extends JpaRepository<ProductEntity, Long> {}
