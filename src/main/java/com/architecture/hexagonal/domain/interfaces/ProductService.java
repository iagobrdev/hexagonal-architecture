package com.architecture.hexagonal.domain.interfaces;

import com.architecture.hexagonal.domain.Product;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for managing products.
 * This interface defines the contract for handling operations related to products,
 * such as retrieving, creating, and listing products. It abstracts the business logic
 * from the underlying persistence mechanisms.
 */
public interface ProductService {

    /**
     * Finds a product by its ID.
     *
     * @param id the ID of the product to be retrieved.
     * @return an {@link Optional} containing the {@link Product} if found, otherwise empty.
     */
    Optional<Product> findById(Long id);

    /**
     * Creates a new product.
     *
     * @param product the product object to be created.
     * @return the created {@link Product}.
     */
    Product createProduct(Product product);

    /**
     * Retrieves all products.
     *
     * @return a list of all {@link Product} objects.
     */
    List<Product> getAllProducts();
}