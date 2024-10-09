package com.architecture.hexagonal.infrastructure.config;

import com.architecture.hexagonal.domain.interfaces.ProductService;
import com.architecture.hexagonal.domain.services.ProductServiceImpl;
import com.architecture.hexagonal.infrastructure.repository.JpaProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for managing bean registrations in the Spring context.
 * This class defines the beans necessary for dependency injection across
 * the application, such as the service implementations and utility classes.
 */
@Configuration
public class BeanConfigurations {

    /**
     * Creates a {@link ProductService} bean in the Spring context.
     * The {@link ProductServiceImpl} is instantiated with its dependencies
     * {@link JpaProductRepository} and {@link ModelMapper}.
     *
     * This method is responsible for providing the service layer of the product management logic
     * and is used throughout the application where {@link ProductService} is required.
     *
     * @param jpaProductRepository the repository responsible for handling product persistence.
     * @param modelMapper the utility used for converting between domain and persistence models.
     * @return the {@link ProductServiceImpl} implementation of {@link ProductService}.
     */
    @Bean
    ProductService productService(JpaProductRepository jpaProductRepository, ModelMapper modelMapper) {
        return new ProductServiceImpl(jpaProductRepository, modelMapper);
    }
}