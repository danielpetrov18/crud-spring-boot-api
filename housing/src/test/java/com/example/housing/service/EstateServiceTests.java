package com.example.housing.service;

import com.example.housing.domain.entity.Estate;
import com.example.housing.repository.IEstateRepository;
import com.example.housing.utility.EstateBuilder;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
public class EstateServiceTests {

    private IEstateRepository estateRepository;

    @Autowired
    public EstateServiceTests(final IEstateRepository newEstateRepository) {
        this.estateRepository = newEstateRepository;
    }

    // C
    @Test
    public void testEstateSuccessfullyAddedToDatabase() {
        final Estate estate = EstateBuilder.getEstateC();

        this.estateRepository.sa(estate);

        final Optional<Estate> possibleEstate = this.estateService.readEstate(estate.getEstateId());

        Assertions.assertThat(possibleEstate.isPresent());
        Assertions.assertThat(possibleEstate.get().getEstateType()).isEqualTo(estate.getEstateType());
        Assertions.assertThat(possibleEstate.get().getPrice()).isEqualTo(estate.getPrice());
    }

    // R


    // U


    // D

}
