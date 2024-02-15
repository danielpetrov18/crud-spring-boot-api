package com.example.housing.service;

import java.util.Optional;
import java.util.Collection;

import lombok.extern.slf4j.Slf4j;

import com.example.housing.domain.entity.Estate;
import com.example.housing.repository.IEstateRepository;
import com.example.housing.exception.EstateNotFoundByIdException;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@Service
public class EstateService {

    private final IEstateRepository estateRepository;

    @Autowired
    public EstateService(final IEstateRepository newEstateRepository) {
        this.estateRepository = newEstateRepository;
    }

    public Estate saveEstate(final Estate estate) {
        final Estate savedEstate = this.estateRepository.save(estate);
        log.debug("Inserted an estate: " + savedEstate);
        return savedEstate;
    }

    public Estate readEstate(final Long estateId) {
        final Estate estate = this.getEstateWithId(estateId);
        log.debug("Fetched estate: {} with id: {}", estate, estateId);
        return estate;
    }

    public Collection<Estate> readEstates() {
        final Collection<Estate> allEstates = this.estateRepository.findAll();
        log.debug("Fetched all estates");
        return allEstates;
    }

    public Estate updateEstate(final Long estateId, final Estate newEstate) {
        final Estate estate = this.getEstateWithId(estateId);

        estate.setEstateType(newEstate.getEstateType());
        estate.setPrice(newEstate.getPrice());

        final Estate updatedEstate = this.estateRepository.save(estate);

        log.debug("Updated estate with id: {}", estateId);

        return updatedEstate;
    }

    public Estate deleteEstate(final Long estateId) {
        final Estate deletedEstate = this.getEstateWithId(estateId);
        this.estateRepository.deleteById(estateId);
        log.debug("Deleted estate with id: {}", estateId);
        return deletedEstate;
    }

    private Estate getEstateWithId(final Long estateId) {
        final Optional<Estate> possibleEstate = this.estateRepository.findById(estateId);

        if(possibleEstate.isEmpty()) {
            log.debug("Estate with id: {} cannot be read from the database!", estateId);
            throw new EstateNotFoundByIdException("Estate with id: " + estateId + " does not exist!");
        }

        return possibleEstate.get();
    }

}