package com.architecture.hexagonal.infrastructure.config;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ModelMapperConfigTest {

    @Autowired
    private ModelMapper modelMapper;

    @Test
    void testModelMapperBeanExists() {
        assertNotNull(modelMapper);
    }
}