package com.example.housing.repository;

import java.util.Set;
import java.util.List;
import java.util.Optional;

import com.example.housing.entity.Estate;
import com.example.housing.entity.Owner;
import org.junit.jupiter.api.Test;

import com.example.housing.utility.OwnerBuilder;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import com.example.housing.utility.EstateBuilder;
import com.example.housing.repository.IOwnerRepository;
import com.example.housing.repository.IEstateRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class IEstateRepositoryTests {

    @Autowired
    private IOwnerRepository ownerRepo;

    @Autowired
    private IEstateRepository estateRepo;

    @Test
    public void testEstateSuccessfullySavedInDatabase() {
        final Estate estate = EstateBuilder.getEstateA();
        this.estateRepo.save(estate);

        final Optional<Estate> possibleEstate = this.estateRepo.findById(estate.getEstateId());

        assertThat(possibleEstate.isPresent(), is(true));
        assertThat(possibleEstate.get(), equalTo(estate));
    }

    @Test
    public void testAddingOwnerToEstateSuccessfulInDatabase() {
        final Owner owner = OwnerBuilder.getOwnerC();
        this.ownerRepo.save(owner);

        final Estate estate = EstateBuilder.getEstateB();
        estate.setOwner(owner);
        this.estateRepo.save(estate);

        final Owner estateOwner = this.estateRepo
                                            .findById(estate.getEstateId())
                                            .get()
                                            .getOwner();

        assertThat(estateOwner, equalTo(owner));
    }

    @Test
    public void testRemovingOwnerToEstateSuccessfulInDatabase() {
        final Owner owner = OwnerBuilder.getOwnerC();
        this.ownerRepo.save(owner);

        final Estate estate = EstateBuilder.getEstateB();
        estate.setOwner(owner);
        this.estateRepo.save(estate);

        this.estateRepo.findById(estate.getEstateId()).ifPresent(foundEstate -> {
            foundEstate.setOwner(null);
            this.estateRepo.save(foundEstate);
            assertThat(foundEstate.getOwner(), nullValue());
        });
    }

    @Test
    public void testMultipleEstatesOwnedByOnePersonInDatabase() {
        final Owner owner = OwnerBuilder.getOwnerA();

        final Set<Estate> estates = Set.of(
               EstateBuilder.getEstateA(),
               EstateBuilder.getEstateB(),
               EstateBuilder.getEstateC()
        );

        estates.forEach(estate -> estate.setOwner(owner));

        this.ownerRepo.save(owner);
        this.estateRepo.saveAll(estates);

        final List<Estate> ownerEstates = this.estateRepo.findEstatesOwnedByOwnerId(owner.getOwnerId());
        assertThat(ownerEstates.stream().allMatch(
                estate -> estate.getOwner().equals(owner)
        ), is(true));
    }

}