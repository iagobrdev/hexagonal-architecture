package com.architecture.hexagonal.domain.interfaces;

import com.architecture.hexagonal.domain.Product;

import java.util.List;

/**
 * Interface for the product repository.
 * This repository provides methods to persist and retrieve products
 * in the system. It abstracts the underlying persistence mechanism
 * and allows the domain to interact with product data.
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
}