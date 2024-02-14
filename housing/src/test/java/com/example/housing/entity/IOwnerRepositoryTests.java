package com.example.housing.entity;

import java.util.Set;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import com.example.housing.utility.OwnerBuilder;
import com.example.housing.repository.IOwnerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class IOwnerRepositoryTests {

    @Autowired
    private IOwnerRepository ownerRepo;

    @Test
    public void testOwnerSuccessfullySavedInDatabase() {
        final Owner owner = OwnerBuilder.getOwnerA();

        this.ownerRepo.save(owner);

        final Optional<Owner> possibleOwner = this.ownerRepo.findById(owner.getOwnerId());

        assertThat(possibleOwner.isPresent(), is(true));
        assertThat(possibleOwner.get(), equalTo(owner));
    }

    @Test
    public void testMultipleOwnersSuccessfullySavedInDatabase() {
        final Set<Owner> owners = Set.of(
                OwnerBuilder.getOwnerA(),
                OwnerBuilder.getOwnerB(),
                OwnerBuilder.getOwnerC());

        this.ownerRepo.saveAll(owners);

        final List<Owner> possibleOwners = this.ownerRepo.findAll();

        boolean allOwnersSavedValid = possibleOwners.containsAll(owners);
        assertThat(allOwnersSavedValid, is(true));
    }

    @Test
    public void testOwnerSuccessfullyDeletedFromDatabase() {
        final Owner owner = OwnerBuilder.getOwnerA();
        this.ownerRepo.save(owner);

        this.ownerRepo.deleteById(owner.getOwnerId());

        final Optional<Owner> possibleOwner = this.ownerRepo.findById(owner.getOwnerId());

        assertThat(possibleOwner.isEmpty(), is(true));
    }

    @Test
    public void testOwnerSuccessfullyUpdatedInDatabase() {
        final Owner originalOwner = OwnerBuilder.getOwnerA();
        this.ownerRepo.save(originalOwner);

        final Owner newOwner = OwnerBuilder.getOwnerB();

        originalOwner.setFirstname(newOwner.getFirstname());
        originalOwner.setLastname(newOwner.getLastname());
        originalOwner.setBirthDate(newOwner.getBirthDate());

        this.ownerRepo.save(originalOwner);

        final Optional<Owner> possibleUpdatedOwner = this.ownerRepo.findById(originalOwner.getOwnerId());

        assertThat(possibleUpdatedOwner.isPresent(), is(true));
        assertThat(possibleUpdatedOwner.get().getFirstname(), equalTo(originalOwner.getFirstname()));
        assertThat(possibleUpdatedOwner.get().getLastname(), equalTo(originalOwner.getLastname()));
        assertThat(possibleUpdatedOwner.get().getBirthDate(), equalTo(originalOwner.getBirthDate()));
    }

}