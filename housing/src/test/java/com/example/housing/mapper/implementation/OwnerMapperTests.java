package com.example.housing.mapper.implementation;

import java.util.Set;

import java.time.LocalDate;

import org.modelmapper.ModelMapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import org.assertj.core.api.Assertions;

import com.example.housing.domain.dto.OwnerDTO;
import com.example.housing.domain.entity.Owner;
import com.example.housing.utility.OwnerBuilder;

import com.example.housing.domain.entity.Estate;
import com.example.housing.utility.EstateBuilder;

public class OwnerMapperTests {

    private OwnerMapper ownerMapper;

    @BeforeEach
    public void setUp() {
        this.ownerMapper = new OwnerMapper(new ModelMapper());
    }

    @Test
    public void testOwnerSuccessfullyMappedToOwnerDTO() {
        final Set<Estate> estates = Set.of(
                EstateBuilder.getEstateA(),
                EstateBuilder.getEstateB(),
                EstateBuilder.getEstateC()
        );

        final Owner owner = OwnerBuilder.getOwnerC();
        owner.addEstates(estates);

        final OwnerDTO ownerDTO = this.ownerMapper.mapTo(owner);

        Assertions.assertThat(ownerDTO.getOwnerId()).isEqualTo(owner.getOwnerId());
        Assertions.assertThat(ownerDTO.getFirstname()).isEqualTo(owner.getFirstname());
        Assertions.assertThat(ownerDTO.getLastname()).isEqualTo(owner.getLastname());
        Assertions.assertThat(ownerDTO.getBirthDate().toString()).isEqualTo(owner.getBirthDate().toString());
        Assertions.assertThat(ownerDTO.getOwnedEstates()).containsAll(estates);
    }

    @Test
    public void testOwnerDTOSuccessfullyMappedToOwner() {
        final Set<Estate> estates = Set.of(
                EstateBuilder.getEstateA(),
                EstateBuilder.getEstateB(),
                EstateBuilder.getEstateC()
        );

        final Long id = 1L;
        final String firstname = "Nickel";
        final String lastname = "Back";
        final LocalDate birthdate = LocalDate.of(1990, 01, 01);

        final OwnerDTO ownerDTO = OwnerDTO.builder()
                .ownerId(id)
                .firstname(firstname)
                .lastname(lastname)
                .birthDate(birthdate)
                .ownedEstates(estates)
                .build();

        final Owner owner = this.ownerMapper.mapFrom(ownerDTO);

        Assertions.assertThat(owner.getOwnerId()).isEqualTo(ownerDTO.getOwnerId());
        Assertions.assertThat(owner.getFirstname()).isEqualTo(ownerDTO.getFirstname());
        Assertions.assertThat(owner.getLastname()).isEqualTo(ownerDTO.getLastname());
        Assertions.assertThat(owner.getBirthDate().toString()).isEqualTo(ownerDTO.getBirthDate().toString());
        Assertions.assertThat(owner.getOwnedEstates()).containsAll(estates);
    }

}