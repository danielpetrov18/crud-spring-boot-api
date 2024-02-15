package com.example.housing.mapper.implementation;

import org.modelmapper.ModelMapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import com.example.housing.domain.dto.EstateDTO;
import com.example.housing.domain.entity.Estate;
import com.example.housing.utility.EstateBuilder;
import com.example.housing.domain.entity.EstateType;

public class EstateMapperTests {

    private EstateMapper estateMapper;

    @BeforeEach
    public void setUp() {
        this.estateMapper = new EstateMapper(new ModelMapper());
    }

    @Test
    public void testEstateSuccessfullyMappedToEstateDTO() {
        final Estate estate = EstateBuilder.getEstateC();

        final EstateDTO estateDTO = this.estateMapper.mapTo(estate);

        assertThat(estate.getEstateId(), is(equalTo(estateDTO.getEstateId())));
        assertThat(estate.getEstateType(), is(equalTo(estateDTO.getEstateType())));
        assertThat(estate.getPrice(), is(equalTo(estateDTO.getPrice())));
    }

    @Test
    public void testEstateDTOSuccessfullyMappedToEstate() {
        final EstateDTO estateDTO = EstateDTO
                .builder()
                .estateId(1L)
                .estateType(EstateType.MANSION)
                .price(200000D)
                .build();

        final Estate estate = this.estateMapper.mapFrom(estateDTO);

        assertThat(estateDTO.getEstateId(), is(equalTo(estate.getEstateId())));
        assertThat(estateDTO.getEstateType(), is(equalTo(estate.getEstateType())));
        assertThat(estateDTO.getPrice(), is(equalTo(estate.getPrice())));
    }

}
