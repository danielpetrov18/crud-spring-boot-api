package com.example.housing.mapper.implementation;

import org.modelmapper.ModelMapper;

import com.example.housing.mapper.IMapper;
import com.example.housing.domain.entity.Estate;
import com.example.housing.domain.dto.EstateDTO;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class EstateMapper implements IMapper<Estate, EstateDTO> {

    private final ModelMapper mapper;

    @Autowired
    public EstateMapper(final ModelMapper newMapper) {
        this.mapper = newMapper;
    }

    @Override
    public EstateDTO mapTo(Estate estate) {
        return this.mapper.map(estate, EstateDTO.class);
    }

    @Override
    public Estate mapFrom(EstateDTO estateDTO) {
        return this.mapper.map(estateDTO, Estate.class);
    }

}