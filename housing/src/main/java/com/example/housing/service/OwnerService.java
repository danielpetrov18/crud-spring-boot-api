package com.example.housing.service;

import java.util.Optional;
import java.util.Collection;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.housing.domain.entity.Owner;
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

    public Owner saveOwner(final Owner owner) {
        final Owner savedOwner = this.ownerRepo.save(owner);
        log.debug("Saved an owner: {}", savedOwner);
        return savedOwner;
    }

    public Owner findOwnerById(final Long ownerId) {
        final Optional<Owner> possibleOwner = this.ownerRepo.findById(ownerId);
        log.debug("ID: {}, Found owner: {}", ownerId, possibleOwner);
        return possibleOwner.get();
    }

    public Collection<Owner> findAllOwners() {
        final Collection<Owner> allOwners = this.ownerRepo.findAll();
        log.debug("Found all owners");
        return allOwners;
    }

    public Owner updateOwnerById(final Long ownerId, final Owner newOwner) {
        final Owner owner = this.getOwnerWithId(ownerId);

        owner.setFirstname(newOwner.getFirstname());
        owner.setLastname(newOwner.getLastname());
        owner.setBirthDate(newOwner.getBirthDate());

        final Owner updatedOwner = this.ownerRepo.save(owner);
        log.debug("Old owner: {}, Updated owner: {}", owner, updatedOwner);

        return updatedOwner;
    }

    public Owner deleteOwnerById(final Long ownerId) {
        final Owner deletedOwner = this.getOwnerWithId(ownerId);
        this.ownerRepo.deleteById(ownerId);
        log.debug("Deleted owner: {}", ownerId);
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