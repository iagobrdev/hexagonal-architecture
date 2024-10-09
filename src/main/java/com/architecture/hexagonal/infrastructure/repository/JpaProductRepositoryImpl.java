package com.architecture.hexagonal.infrastructure.repository;

import com.architecture.hexagonal.domain.Product;
import com.architecture.hexagonal.domain.interfaces.ProductRepository;
import com.architecture.hexagonal.infrastructure.entities.ProductEntity;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of the ProductRepository interface for managing ProductEntity instances.
 * This class handles the mapping between Product and ProductEntity,
 * providing CRUD operations using the JpaProductRepository.
 */
@Component
public class JpaProductRepositoryImpl implements ProductRepository {

    private final JpaProductRepository jpaProductRepository;
    private final ModelMapper modelMapper;

    /**
     * Constructor to initialize JpaProductRepositoryImpl with the necessary dependencies.
     *
     * @param jpaProductRepository the JPA repository for ProductEntity.
     * @param modelMapper the ModelMapper for converting between Product and ProductEntity.
     */
    @Lazy
    public JpaProductRepositoryImpl(JpaProductRepository jpaProductRepository, ModelMapper modelMapper) {
        this.jpaProductRepository = jpaProductRepository;
        this.modelMapper = modelMapper;
    }

    /**
     * Saves a Product to the repository.
     *
     * @param product the Product object to be saved.
     * @return the saved Product.
     */
    @Override
    public Product saveProduct(Product product) {
        var entity = modelMapper.map(product, ProductEntity.class);
        entity = jpaProductRepository.save(entity);
        return modelMapper.map(entity, Product.class);
    }

    /**
     * Retrieves all Products from the repository.
     *
     * @return a list of all Products.
     */
    @Override
    public List<Product> findAllProducts() {
        return jpaProductRepository.findAll().stream()
                .map(entity -> modelMapper.map(entity, Product.class))
                .toList();
    }

    /**
     * Finds a Product by its ID.
     *
     * @param id the ID of the Product to be retrieved.
     * @return an Optional containing the Product if found, or empty if not found.
     */
    @Override
    public Optional<Product> findProductById(Long id) {
        return jpaProductRepository.findById(id)
                .map(entity -> modelMapper.map(entity, Product.class));
    }
}