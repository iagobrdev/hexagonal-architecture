package com.architecture.hexagonal.infrastructure.repository;

import com.architecture.hexagonal.domain.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ActiveProfiles("test")
class JpaProductRepositoryTest {

    @Autowired
    private JpaProductRepositoryImpl jpaProductRepositoryImpl;

    @Test
    void testSaveProduct() {
        var product = new Product(null, "Test Product", BigDecimal.valueOf(100.0));
        var savedProduct = jpaProductRepositoryImpl.saveProduct(product);

        assertNotNull(savedProduct.getId());
        assertEquals("Test Product", savedProduct.getName());
    }

    @Test
    void testFindById() {
        var product = new Product(null, "Test Product", BigDecimal.valueOf(100.0));
        var savedProduct = jpaProductRepositoryImpl.saveProduct(product);

        var retrievedProduct = jpaProductRepositoryImpl.findProductById(savedProduct.getId());
        assertTrue(retrievedProduct.isPresent());
        assertEquals("Test Product", retrievedProduct.get().getName());
    }

    @Test
    void testFindAllProducts() {
        var product1 = new Product(null, "Test Product", BigDecimal.valueOf(100.0));
        var product2 = new Product(null, "Test Product 2", BigDecimal.valueOf(150.0));
        jpaProductRepositoryImpl.saveProduct(product1);
        jpaProductRepositoryImpl.saveProduct(product2);

        var products = jpaProductRepositoryImpl.findAllProducts();
        assertEquals(2, products.size());
    }
}