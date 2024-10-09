package com.architecture.hexagonal.domain.services;

import com.architecture.hexagonal.domain.Product;
import com.architecture.hexagonal.domain.interfaces.ProductRepository;
import com.architecture.hexagonal.domain.interfaces.ProductService;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of the {@link ProductService} interface.
 * This class provides methods for managing products,
 * including creating new products and retrieving existing ones.
 */
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    /**
     * Constructs a new ProductServiceImpl with the specified ProductRepository.
     *
     * @param productRepository the repository used to manage product persistence.
     */
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Finds a product by its ID.
     *
     * @param id the ID of the product to be retrieved.
     * @return an {@link Optional} containing the {@link Product} if found, otherwise empty.
     */
    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findProductById(id);
    }

    /**
     * Creates a new product.
     *
     * @param product the {@link Product} object to be created.
     * @return the created {@link Product}.
     */
    @Override
    public Product createProduct(Product product) {
        return productRepository.saveProduct(product);
    }

    /**
     * Retrieves all products.
     *
     * @return a list of all {@link Product} objects.
     */
    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAllProducts();
    }
}