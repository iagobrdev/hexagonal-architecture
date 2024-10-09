package com.architecture.hexagonal.domain.interfaces;

import com.architecture.hexagonal.domain.Product;

import java.util.List;
import java.util.Optional;

/**
 * Interface for managing product persistence.
 * This repository provides methods to save, retrieve, and manage
 * products in the system. It abstracts the underlying persistence mechanism,
 * allowing the domain layer to interact with product data without depending
 * on specific infrastructure implementations.
 */
public interface ProductRepository {

    /**
     * Saves a product to the repository.
     *
     * @param product the {@link Product} object to be saved.
     * @return the saved {@link Product}.
     */
    Product saveProduct(Product product);

    /**
     * Retrieves all products from the repository.
     *
     * @return a list of all {@link Product} objects in the repository.
     */
    List<Product> findAllProducts();

    /**
     * Finds a product by its ID.
     *
     * @param id the ID of the product to be retrieved.
     * @return an {@link Optional} containing the {@link Product} if found, or empty if not.
     */
    Optional<Product> findProductById(Long id);
}