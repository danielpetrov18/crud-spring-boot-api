package com.example.housing.service;

import java.util.Optional;

import lombok.extern.slf4j.Slf4j;

import com.example.housing.entity.Owner;
import com.example.housing.requests.OwnerRequest;

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

    public void createOwner(final Owner owner) {
        log.debug("Inserting a new owner: {}", owner);
        this.ownerRepo.save(owner);
    }

    public Owner readOwner(final Long ownerId) {
        log.debug("Fetching owner with id: " + ownerId);

        final Optional<Owner> possibleOwner = this.ownerRepo.findById(ownerId);

        if(possibleOwner.isEmpty()) {
            log.debug("Owner with id: {} cannot be read from the database!", ownerId);
            throw new OwnerNotFoundByIdException("Owner with id: " + ownerId + " does not exist!");
        }

        return possibleOwner.get();
    }

    public Owner updateOwner(final Long ownerId, final OwnerRequest newOwner) {
        log.debug("Updating owner with id: {} with {}", ownerId, newOwner);

        final Optional<Owner> possibleOwner = this.ownerRepo.findById(ownerId);

        if(possibleOwner.isEmpty()) {
            log.debug("Owner with id: {} not found!", ownerId);
            throw new OwnerNotFoundByIdException("Owner with id: " + ownerId + " not found!");
        }

        final Owner updatedOwner = possibleOwner.get();

        updatedOwner.setFirstname(newOwner.getFirstname());
        updatedOwner.setLastname(newOwner.getLastname());
        updatedOwner.setBirthDate(newOwner.getBirthDate());

        this.ownerRepo.save(updatedOwner);

        return updatedOwner;
    }

    public void deleteOwner(final Long ownerId) {
        final Optional<Owner> possibleOwner = this.ownerRepo.findById(ownerId);

        if(possibleOwner.isEmpty()) {
            log.debug("Trying to delete owner with id: {} but not found!", ownerId);
            throw new OwnerNotFoundByIdException("Owner with id: " + ownerId + " not found!");
        }

        log.debug("Deleting owner with id: {}", ownerId);
        this.ownerRepo.deleteById(ownerId);
    }

}