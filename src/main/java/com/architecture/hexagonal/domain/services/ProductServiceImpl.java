package com.architecture.hexagonal.domain.services;

import com.architecture.hexagonal.domain.Product;
import com.architecture.hexagonal.domain.interfaces.ProductRepository;
import com.architecture.hexagonal.domain.interfaces.ProductService;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of {@link ProductService} for managing products.
 * This service handles operations such as retrieving products by ID,
 * creating new products, and fetching all products. It interacts with the
 * {@link ProductRepository} to persist product data and utilizes the {@link ModelMapper}
 * for converting between the domain model {@link Product} and the persistence model.
 */
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    /**
     * Constructor to initialize ProductServiceImpl with repository and model mapper.
     *
     * @param productRepository the repository responsible for product persistence.
     * @param modelMapper the ModelMapper used for converting between domain and persistence models.
     */
    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    /**
     * Finds a product by its ID.
     *
     * @param id the ID of the product to be retrieved.
     * @return an {@link Optional} containing the {@link Product} if found, otherwise empty.
     */
    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findProductById(id)
                .map(entity -> modelMapper.map(entity, Product.class));
    }

    /**
     * Creates a new product.
     *
     * @param product the product object to be created.
     * @return the created {@link Product}.
     */
    @Override
    public Product createProduct(Product product) {
        var savedEntity = productRepository.saveProduct(product);
        return modelMapper.map(savedEntity, Product.class);
    }

    /**
     * Retrieves all products from the repository.
     *
     * @return a list of all {@link Product} objects.
     */
    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAllProducts().stream()
                .map(entity -> modelMapper.map(entity, Product.class))
                .toList();
    }
}