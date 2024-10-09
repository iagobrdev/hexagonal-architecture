package com.architecture.hexagonal.application;

import com.architecture.hexagonal.domain.Product;
import com.architecture.hexagonal.infrastructure.entities.ProductEntity;
import com.architecture.hexagonal.infrastructure.repository.JpaProductRepository;
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
    private JpaProductRepository productRepository;

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
        var productEntity = new ProductEntity(1L, "Test Product", new BigDecimal("100.00"));
        var product = new Product(1L, "Test Product", BigDecimal.valueOf(100.0));

        when(productRepository.findById(1L)).thenReturn(Optional.of(productEntity));
        when(modelMapper.map(productEntity, Product.class)).thenReturn(product);

        var result = productService.findById(1L);

        assertTrue(result.isPresent());
        assertEquals("Test Product", result.get().getName());
        verify(productRepository).findById(1L);
    }

    @Test
    void testCreateProduct() {
        var productEntity = new ProductEntity(1L, "New Product", new BigDecimal("50.00"));
        var product = new Product(null, "New Product", BigDecimal.valueOf(50.0));

        when(modelMapper.map(product, ProductEntity.class)).thenReturn(productEntity);
        when(productRepository.save(any(ProductEntity.class))).thenReturn(productEntity);
        when(modelMapper.map(productEntity, Product.class)).thenReturn(product);

        var createdProduct = productService.createProduct(product);

        assertNotNull(createdProduct);
        assertEquals("New Product", createdProduct.getName());
        verify(productRepository).save(any(ProductEntity.class));
    }

    @Test
    void testGetAllProducts() {
        var productEntities = Arrays.asList(
                new ProductEntity(1L, "Product 1", new BigDecimal("30.00")),
                new ProductEntity(2L, "Product 2", new BigDecimal("40.00"))
        );

        var products = Arrays.asList(
                new Product(1L, "Product 1", BigDecimal.valueOf(30.0)),
                new Product(2L, "Product 2", BigDecimal.valueOf(40.0))
        );

        when(productRepository.findAll()).thenReturn(productEntities);
        when(modelMapper.map(productEntities.get(0), Product.class)).thenReturn(products.get(0));
        when(modelMapper.map(productEntities.get(1), Product.class)).thenReturn(products.get(1));

        var result = productService.getAllProducts();

        assertEquals(2, result.size());
        assertEquals("Product 1", result.get(0).getName());
        verify(productRepository).findAll();
    }
}