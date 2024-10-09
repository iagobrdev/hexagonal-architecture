package com.architecture.hexagonal.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Domain model representing a Product.
 * This class defines the core attributes of a product including its ID, name, and price.
 * It uses Lombok annotations to automatically generate getters, setters, and constructors.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    /**
     * The unique identifier of the product.
     */
    private Long id;

    /**
     * The name of the product.
     */
    private String name;

    /**
     * The price of the product.
     * It is represented using {@link BigDecimal} to ensure precision for monetary values.
     */
    private BigDecimal price;
}