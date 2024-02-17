package com.example.housing.config;

import org.modelmapper.ModelMapper;

import com.example.housing.domain.dto.OwnerDTO;
import com.example.housing.domain.entity.Owner;

import org.modelmapper.convention.MatchingStrategies;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();

        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);

        mapper.createTypeMap(Owner.class, OwnerDTO.class)
                .addMapping(Owner::getOwnedEstates, OwnerDTO::setOwnedEstates);

        mapper.createTypeMap(OwnerDTO.class, Owner.class)
                .addMapping(OwnerDTO::getOwnedEstates, Owner::setOwnedEstates);

        return mapper;
    }

}
