package com.architecture.hexagonal.infrastructure.services;

import com.architecture.hexagonal.domain.Product;
import com.architecture.hexagonal.domain.interfaces.ProductRepository;
import com.architecture.hexagonal.domain.services.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindById() {
        var productEntity = new Product(1L, "Test Product", BigDecimal.valueOf(100.0));
        var product = new Product(1L, "Test Product", BigDecimal.valueOf(100.0));

        when(productRepository.findProductById(1L)).thenReturn(Optional.of(productEntity));
        when(modelMapper.map(productEntity, Product.class)).thenReturn(product);

        var result = productService.findById(1L);

        assertTrue(result.isPresent());
        assertEquals("Test Product", result.get().getName());
        verify(productRepository).findProductById(1L);
    }

    @Test
    void testCreateProduct() {
        var product = new Product(null, "New Product", BigDecimal.valueOf(50.0));
        var savedProductEntity = new Product(1L, "New Product", BigDecimal.valueOf(50.0));

        when(productRepository.saveProduct(any(Product.class))).thenReturn(savedProductEntity);
        when(modelMapper.map(product, Product.class)).thenReturn(savedProductEntity);
        when(modelMapper.map(savedProductEntity, Product.class)).thenReturn(savedProductEntity);

        var createdProduct = productService.createProduct(product);

        assertNotNull(createdProduct);
        assertEquals("New Product", createdProduct.getName());
        assertEquals(1L, createdProduct.getId());
        verify(productRepository).saveProduct(any(Product.class));
    }

    @Test
    void testGetAllProducts() {
        var product1 = new Product(1L, "Product 1", BigDecimal.valueOf(30.0));
        var product2 = new Product(2L, "Product 2", BigDecimal.valueOf(40.0));
        var products = Arrays.asList(product1, product2);

        when(productRepository.findAllProducts()).thenReturn(products);

        var result = productService.getAllProducts();

        assertNotNull(result);
        assertEquals(2, result.size());

        verify(productRepository).findAllProducts();
    }
}