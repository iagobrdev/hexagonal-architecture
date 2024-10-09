package com.architecture.hexagonal.infrastructure.controllers;

import com.architecture.hexagonal.application.ProductServiceImpl;
import com.architecture.hexagonal.domain.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller for managing products.
 * This controller exposes endpoints to create, retrieve, and list products.
 * It delegates the business logic to the {@link ProductServiceImpl}.
 */
@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductServiceImpl productService;

    /**
     * Constructor to initialize the ProductController with the ProductServiceImpl.
     *
     * @param productService the service responsible for managing products.
     */
    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    /**
     * Endpoint to create a new product.
     *
     * @param product the product object to be created.
     * @return the created product wrapped in a {@link ResponseEntity}.
     */
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        var createdProduct = productService.createProduct(product);
        return ResponseEntity.ok(createdProduct);
    }

    /**
     * Endpoint to retrieve a product by its ID.
     *
     * @param id the ID of the product to be retrieved.
     * @return the product, if found, or a 404 Not Found response if the product does not exist.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        var product = productService.findById(id);
        return product.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Endpoint to retrieve all products.
     *
     * @return a list of all products wrapped in a {@link ResponseEntity}.
     */
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        var products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }
}