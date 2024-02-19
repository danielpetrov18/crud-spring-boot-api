package com.example.housing.service;

import java.util.Optional;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.InjectMocks;

import org.junit.jupiter.api.Test;

import org.assertj.core.api.Assertions;

import com.example.housing.domain.entity.Estate;
import com.example.housing.domain.entity.EstateType;
import com.example.housing.repository.IEstateRepository;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class EstateServiceTests {

    @Mock
    private IEstateRepository estateRepositoryMock;

    @InjectMocks
    private EstateService estateService;

    @Test
    public void testSavingEstateSuccessful() {
        final Estate expected = Estate
                .builder()
                .estateId(1L)
                .estateType(EstateType.PENTHOUSE)
                .price(200000D)
                .build();

        Mockito.when(this.estateRepositoryMock.save(expected)).thenReturn(expected);

        final Estate result = this.estateService.saveEstate(expected);

        Mockito.verify(estateRepositoryMock, Mockito.times(1)).save(expected);

        Assertions.assertThat(result.getEstateId()).isEqualTo(expected.getEstateId());
        Assertions.assertThat(result.getEstateType()).isEqualTo(expected.getEstateType());
        Assertions.assertThat(result.getPrice()).isEqualTo(expected.getPrice());
    }

    @Test
    public void testFetchingEstateByIdSuccessful() {
        final Estate expected = Estate
                .builder()
                .estateId(1L)
                .estateType(EstateType.PENTHOUSE)
                .price(200000D)
                .build();

        Mockito.when(this.estateRepositoryMock.findById(Mockito.any())).thenReturn(Optional.ofNullable(expected));

        final Estate result = this.estateService.findEstateById(expected.getEstateId());

        Mockito.verify(estateRepositoryMock, Mockito.times(1)).findById(expected.getEstateId());

        Assertions.assertThat(result.getEstateId()).isEqualTo(expected.getEstateId());
        Assertions.assertThat(result.getEstateType()).isEqualTo(expected.getEstateType());
        Assertions.assertThat(result.getPrice()).isEqualTo(expected.getPrice());
    }

}