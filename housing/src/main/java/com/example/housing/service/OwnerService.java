package com.example.housing.service;

import java.util.Optional;

import lombok.extern.slf4j.Slf4j;

import com.example.housing.domain.entity.Owner;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.housing.repository.IOwnerRepository;

import com.example.housing.exception.OwnerNotFoundByIdException;

@Slf4j
@Service
public class OwnerService {

    private final IOwnerRepository ownerRepo;

    @Autowired
    public OwnerService(final IOwnerRepository newOwnerRepo) {
        this.ownerRepo = newOwnerRepo;
    }

    public Owner createOwner(final Owner owner) {
        final Owner savedOwner = this.ownerRepo.save(owner);
        log.debug("Inserted a new owner: {}", owner);
        return savedOwner;
    }

    public Owner readOwner(final Long ownerId) {
        final Owner owner = this.getOwnerWithId(ownerId);
        log.debug("Fetched owner with id: " + ownerId);
        return owner;
    }

    public Iterable<Owner> readOwners() {
        final Iterable<Owner> allOwners = this.ownerRepo.findAll();
        log.debug("Fetched all owners");
        return allOwners;
    }

    public Owner updateOwner(final Long ownerId, final Owner newOwner) {
        final Owner owner = this.getOwnerWithId(ownerId);

        owner.setFirstname(newOwner.getFirstname());
        owner.setLastname(newOwner.getLastname());
        owner.setBirthDate(newOwner.getBirthDate());

        final Owner updatedOwner = this.ownerRepo.save(owner);

        log.debug("Updated owner with id: {} with {}", ownerId, updatedOwner);

        return updatedOwner;
    }

    public Owner deleteOwner(final Long ownerId) {
        final Owner deletedOwner = this.getOwnerWithId(ownerId);
        this.ownerRepo.deleteById(ownerId);
        log.debug("Deleted owner with id: {}", ownerId);
        return deletedOwner;
    }

    private Owner getOwnerWithId(final Long ownerId) {
        final Optional<Owner> possibleOwner = this.ownerRepo.findById(ownerId);

        if(possibleOwner.isEmpty()) {
            log.debug("Owner with id: {} cannot be read from the database!", ownerId);
            throw new OwnerNotFoundByIdException("Owner with id: " + ownerId + " does not exist!");
        }

        return possibleOwner.get();
    }

}