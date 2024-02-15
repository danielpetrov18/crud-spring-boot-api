package com.example.housing.mapper.implementation;

import org.modelmapper.ModelMapper;

import com.example.housing.mapper.IMapper;
import com.example.housing.domain.entity.Owner;
import com.example.housing.domain.dto.OwnerDTO;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class OwnerMapper implements IMapper<Owner, OwnerDTO> {

    private final ModelMapper mapper;

    @Autowired
    public OwnerMapper(final ModelMapper newMapper) {
        this.mapper = newMapper;
    }

    @Override
    public OwnerDTO mapTo(Owner owner) {
        return this.mapper.map(owner, OwnerDTO.class);
    }

    @Override
    public Owner mapFrom(OwnerDTO ownerDTO) {
        return this.mapper.map(ownerDTO, Owner.class);
    }

}