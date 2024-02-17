package com.example.housing.controller;

import java.util.Collection;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

import com.example.housing.exception.OwnerNotFoundByIdException;

import com.example.housing.domain.dto.OwnerDTO;
import com.example.housing.domain.entity.Owner;
import com.example.housing.service.OwnerService;
import com.example.housing.mapper.implementation.OwnerMapper;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@RestController
public class OwnerController {

    private final OwnerMapper ownerMapper;
    private final OwnerService ownerService;

    @Autowired
    public OwnerController(final OwnerService newOwnerService, final OwnerMapper newOwnerMapper) {
        this.ownerMapper = newOwnerMapper;
        this.ownerService = newOwnerService;
    }

    @PostMapping(path = "/owners")
    public ResponseEntity<?> createOwner(@RequestBody final OwnerDTO ownerRequest) {
        try {
            final Owner toBeInserted = this.ownerMapper.mapFrom(ownerRequest);
            final Owner insertedOwner = this.ownerService.createOwner(toBeInserted);
            final OwnerDTO insertedOwnerDTO = this.ownerMapper.mapTo(insertedOwner);
            log.debug("Inserted a new owner: {}", insertedOwnerDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(insertedOwnerDTO);
        }
        catch(Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(path = "/owners/{id}")
    public ResponseEntity<?> fetchOwnerById(@PathVariable("id") final Long ownerId) {
        return this.ownerService.readOwner(ownerId)
                .map(owner -> {
                    final OwnerDTO fetchedOwnerDTO = this.ownerMapper.mapTo(owner);
                    log.debug("Fetched: {}", fetchedOwnerDTO);
                    return ResponseEntity.ok(fetchedOwnerDTO);
                })
                .orElseGet(() -> {
                    final String errMsg = "Owner with id: " + ownerId + " not found!";
                    log.error(errMsg);
                    throw new OwnerNotFoundByIdException("OwnerNotFoundByIdException", errMsg);
                });
    }


    @GetMapping(path = "/owners")
    public ResponseEntity<?> fetchOwners() {
        try {
            final Collection<Owner> fetchedOwners = this.ownerService.readAllOwners();

            final Collection<OwnerDTO> fetchedOwnersDTO = fetchedOwners
                    .stream()
                    .map(this.ownerMapper::mapTo)
                    .collect(Collectors.toUnmodifiableSet());

            log.debug("Fetched all owners");

            return ResponseEntity.ok(fetchedOwnersDTO);
        }
        catch(Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping(path = "/owners/{id}")
    public ResponseEntity<?> updateOwner(@PathVariable("id") final Long id, @RequestBody final OwnerDTO dto) {
        try {
            final Owner ownerObject = this.ownerMapper.mapFrom(dto);
            final Owner updatedOwner = this.ownerService.updateOwner(id, ownerObject);
            final OwnerDTO updatedOwnerDTO = this.ownerMapper.mapTo(updatedOwner);
            log.debug("Updated owner: {}", updatedOwnerDTO);
            return ResponseEntity.ok(updatedOwnerDTO);
        }
        catch(Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(path = "/owners/{id}")
    public ResponseEntity<?> deleteOwner(@PathVariable("id") final Long id) {
        try {
            final Owner deletedOwner = this.ownerService.deleteOwner(id);
            final OwnerDTO deletedOwnerDTO = this.ownerMapper.mapTo(deletedOwner);
            log.debug("Deleted owner: {}", deletedOwner);
            return ResponseEntity.ok(deletedOwnerDTO);
        }
        catch(Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

}