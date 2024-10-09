package com.architecture.hexagonal.infrastructure.config;

import com.architecture.hexagonal.domain.interfaces.ProductRepository;
import com.architecture.hexagonal.domain.interfaces.ProductService;
import com.architecture.hexagonal.domain.services.ProductServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for defining Spring beans in the application context.
 * This class is responsible for providing instances of services and mappers used in the application.
 */
@Configuration
public class BeanConfigurations {

    /**
     * Creates a ProductService bean using the provided ProductRepository.
     *
     * @param productRepository the repository used to manage product persistence.
     * @return a ProductService instance.
     */
    @Bean
    public ProductService productService(ProductRepository productRepository) {
        return new ProductServiceImpl(productRepository);
    }

    /**
     * Creates a ModelMapper bean for object mapping.
     *
     * @return a ModelMapper instance.
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}