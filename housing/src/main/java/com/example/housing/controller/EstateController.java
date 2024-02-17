package com.example.housing.controller;

import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.stream.Collectors;

import com.example.housing.domain.dto.EstateDTO;
import com.example.housing.domain.entity.Estate;
import com.example.housing.service.EstateService;
import com.example.housing.mapper.implementation.EstateMapper;
import com.example.housing.exception.EstateNotFoundByIdException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@RestController
public class EstateController {

    private final EstateMapper estateMapper;
    private final EstateService estateService;

    @Autowired
    public EstateController(final EstateMapper newEstateMapper, final EstateService newEstateService) {
        this.estateMapper = newEstateMapper;
        this.estateService = newEstateService;
    }

    @PostMapping(path = "/estates")
    public ResponseEntity<?> createEstate(@RequestBody final EstateDTO estateDTO) {
        try {
            final Estate toBeInserted = this.estateMapper.mapFrom(estateDTO);
            final Estate insertedEstate = this.estateService.saveEstate(toBeInserted);
            final EstateDTO insertedEstateDTO = this.estateMapper.mapTo(insertedEstate);
            log.debug("Inserted a new estate: {}", insertedEstateDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(insertedEstateDTO);
        }
        catch(Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(path = "/estates/{id}")
    public ResponseEntity<?> readEstate(@PathVariable("id") final Long estateId) {
        return this.estateService.readEstate(estateId)
                .map(owner -> {
                    final EstateDTO fetchedEstateDTO = this.estateMapper.mapTo(owner);
                    log.debug("Fetched estate: {}", fetchedEstateDTO);
                    return ResponseEntity.ok(fetchedEstateDTO);
                })
                .orElseGet(() -> {
                    final String errMsg = "Estate with id: " + estateId + " not found!";
                    log.error(errMsg);
                    throw new EstateNotFoundByIdException("EstateNotFoundByIdException", errMsg);
                });
    }

    @GetMapping(path = "/estates")
    public ResponseEntity<?> readEstates() {
        try {
            final Collection<Estate> fetchedEstates = this.estateService.readEstates();

            final Collection<EstateDTO> fetchedEstatesDTO = fetchedEstates
                    .stream()
                    .map(this.estateMapper::mapTo)
                    .collect(Collectors.toUnmodifiableSet());

            log.debug("Fetched all estates");

            return ResponseEntity.ok(fetchedEstatesDTO);
        }
        catch(Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping(path = "/estates/{id}")
    public ResponseEntity<?> updateOwner(@PathVariable("id") final Long id, @RequestBody final EstateDTO dto) {
        try {
            final Estate estateObject = this.estateMapper.mapFrom(dto);
            final Estate updatedOwner = this.estateService.updateEstate(id, estateObject);
            final EstateDTO updatedEstateDTO = this.estateMapper.mapTo(updatedOwner);
            log.debug("Updated estate: {}", updatedEstateDTO);
            return ResponseEntity.ok(updatedEstateDTO);
        }
        catch(Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(path = "/estates/{id}")
    public ResponseEntity<?> deleteOwner(@PathVariable("id") final Long id) {
        try {
            final Estate deletedEstate = this.estateService.deleteEstate(id);
            final EstateDTO deletedEstateDTO = this.estateMapper.mapTo(deletedEstate);
            log.debug("Deleted owner: {}", deletedEstateDTO);
            return ResponseEntity.ok(deletedEstateDTO);
        }
        catch(Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

}