package com.example.housing.controller;

import java.util.Collection;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.housing.domain.dto.OwnerDTO;
import com.example.housing.domain.entity.Owner;
import com.example.housing.service.OwnerService;
import com.example.housing.mapper.implementation.OwnerMapper;

@Slf4j
@RestController
@RequiredArgsConstructor
public class OwnerController {

    private final OwnerMapper ownerMapper;
    private final OwnerService ownerService;

    @PostMapping(path = "/owners")
    public ResponseEntity<?> createOwner(@RequestBody final OwnerDTO ownerRequest) {
        final Owner toBeInserted = this.ownerMapper.mapFrom(ownerRequest);
        final Owner insertedOwner = this.ownerService.saveOwner(toBeInserted);
        final OwnerDTO insertedOwnerDTO = this.ownerMapper.mapTo(insertedOwner);

        return ResponseEntity.status(HttpStatus.CREATED).body(insertedOwnerDTO);
    }

    @GetMapping(path = "/owners/{id}")
    public ResponseEntity<?> fetchOwnerById(@PathVariable("id") final Long ownerId) {
        final Owner foundOwner = this.ownerService.findOwnerById(ownerId);
        final OwnerDTO foundOwnerDTO = this.ownerMapper.mapTo(foundOwner);

        return ResponseEntity.ok(foundOwnerDTO);
    }

    @GetMapping(path = "/owners")
    public ResponseEntity<?> fetchOwners() {
        final Collection<Owner> fetchedOwners = this.ownerService.findAllOwners();

        final Collection<OwnerDTO> fetchedOwnersDTO = fetchedOwners
                .stream()
                .map(this.ownerMapper::mapTo)
                .collect(Collectors.toUnmodifiableSet());

        return ResponseEntity.ok(fetchedOwnersDTO);
    }

    @PutMapping(path = "/owners/{id}")
    public ResponseEntity<?> updateOwner(@PathVariable("id") final Long id, @RequestBody final OwnerDTO dto) {
        final Owner ownerObject = this.ownerMapper.mapFrom(dto);
        final Owner updatedOwner = this.ownerService.updateOwnerById(id, ownerObject);
        final OwnerDTO updatedOwnerDTO = this.ownerMapper.mapTo(updatedOwner);

        return ResponseEntity.ok(updatedOwnerDTO);
    }

    @DeleteMapping(path = "/owners/{id}")
    public ResponseEntity<?> deleteOwner(@PathVariable("id") final Long id) {
        final Owner deletedOwner = this.ownerService.deleteOwnerById(id);
        final OwnerDTO deletedOwnerDTO = this.ownerMapper.mapTo(deletedOwner);

        return ResponseEntity.ok(deletedOwnerDTO);
    }

}