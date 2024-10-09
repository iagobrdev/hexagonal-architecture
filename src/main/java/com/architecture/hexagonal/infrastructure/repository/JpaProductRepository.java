package com.architecture.hexagonal.infrastructure.repository;

import com.architecture.hexagonal.domain.Product;
import com.architecture.hexagonal.domain.interfaces.ProductRepository;
import com.architecture.hexagonal.infrastructure.entities.ProductEntity;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for managing {@link ProductEntity} persistence.
 * This repository extends {@link JpaRepository} to provide basic CRUD operations
 * and implements {@link ProductRepository} to bridge the domain layer with the persistence layer.
 * It uses {@link ModelMapper} to convert between the domain model {@link Product} and
 * the persistence model {@link ProductEntity}.
 */
@Repository
public interface JpaProductRepository extends JpaRepository<ProductEntity, Long>, ProductRepository {

    /**
     * Provides a new instance of {@link ModelMapper}.
     *
     * @return a {@link ModelMapper} instance used for converting objects.
     */
    default ModelMapper getModelMapper() {
        return new ModelMapper();
    }

    /**
     * Converts a {@link Product} domain object to a {@link ProductEntity} persistence object.
     *
     * @param product the {@link Product} object to convert.
     * @return the corresponding {@link ProductEntity} object.
     */
    default ProductEntity toEntity(Product product) {
        return getModelMapper().map(product, ProductEntity.class);
    }

    /**
     * Converts a {@link ProductEntity} persistence object to a {@link Product} domain object.
     *
     * @param entity the {@link ProductEntity} object to convert.
     * @return the corresponding {@link Product} object.
     */
    default Product toDomain(ProductEntity entity) {
        return getModelMapper().map(entity, Product.class);
    }

    /**
     * Saves a {@link Product} object by converting it to a {@link ProductEntity}.
     * After saving, it converts the entity back to the {@link Product} domain model.
     *
     * @param product the {@link Product} object to be saved.
     * @return the saved {@link Product} object.
     */
    @Override
    default Product saveProduct(Product product) {
        var entity = this.save(toEntity(product));
        return toDomain(entity);
    }

    /**
     * Retrieves all products from the persistence layer and converts them to the domain model.
     *
     * @return a list of all {@link Product} objects.
     */
    @Override
    default List<Product> findAllProducts() {
        return this.findAll().stream()
                .map(this::toDomain)
                .toList();
    }
}