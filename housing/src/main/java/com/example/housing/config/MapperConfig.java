package com.example.housing.config;

import org.modelmapper.ModelMapper;

import org.modelmapper.convention.MatchingStrategies;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        final ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return mapper;
    }

}
