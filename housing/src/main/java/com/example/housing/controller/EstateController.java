package com.example.housing.controller;

import java.util.Collection;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.housing.domain.dto.EstateDTO;
import com.example.housing.domain.entity.Estate;
import com.example.housing.service.EstateService;
import com.example.housing.mapper.implementation.EstateMapper;

@Slf4j
@RestController
@RequiredArgsConstructor
public class EstateController {

    private final EstateMapper estateMapper;
    private final EstateService estateService;

    @PostMapping(path = "/estates")
    public ResponseEntity<?> createEstate(@RequestBody final EstateDTO estateDTO) {
        final Estate toBeInserted = this.estateMapper.mapFrom(estateDTO);
        final Estate insertedEstate = this.estateService.saveEstate(toBeInserted);
        final EstateDTO insertedEstateDTO = this.estateMapper.mapTo(insertedEstate);

        return ResponseEntity.status(HttpStatus.CREATED).body(insertedEstateDTO);
    }

    @GetMapping(path = "/estates/{id}")
    public ResponseEntity<?> readEstate(@PathVariable("id") final Long estateId) {
        final Estate foundEstate = this.estateService.findEstateById(estateId);
        final EstateDTO foundEstateDTO = this.estateMapper.mapTo(foundEstate);

        return ResponseEntity.ok(foundEstateDTO);
    }

    @GetMapping(path = "/estates")
    public ResponseEntity<?> readEstates() {
        final Collection<Estate> fetchedEstates = this.estateService.findAllEstates();

        final Collection<EstateDTO> fetchedEstatesDTO = fetchedEstates
                .stream()
                .map(this.estateMapper::mapTo)
                .collect(Collectors.toUnmodifiableSet());

        return ResponseEntity.ok(fetchedEstatesDTO);
    }

    @PutMapping(path = "/estates/{id}")
    public ResponseEntity<?> updateOwner(@PathVariable("id") final Long id, @RequestBody final EstateDTO dto) {
        final Estate estateObject = this.estateMapper.mapFrom(dto);
        final Estate updatedOwner = this.estateService.updateEstateById(id, estateObject);
        final EstateDTO updatedEstateDTO = this.estateMapper.mapTo(updatedOwner);

        return ResponseEntity.ok(updatedEstateDTO);
    }

    @DeleteMapping(path = "/estates/{id}")
    public ResponseEntity<?> deleteOwner(@PathVariable("id") final Long id) {
        final Estate deletedEstate = this.estateService.deleteEstateById(id);
        final EstateDTO deletedEstateDTO = this.estateMapper.mapTo(deletedEstate);

        return ResponseEntity.ok(deletedEstateDTO);
    }

}