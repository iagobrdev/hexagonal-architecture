package com.architecture.hexagonal.infrastructure.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for setting up the {@link ModelMapper} bean.
 * This class ensures that a singleton instance of the {@link ModelMapper} is available
 * throughout the application via dependency injection.
 */
@Configuration
public class ModelMapperConfig {

    /**
     * Creates a {@link ModelMapper} bean that can be used for object mapping between domain
     * and entity objects.
     *
     * @return a new instance of {@link ModelMapper}.
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}