package com.example.housing.service;

import java.util.Optional;
import java.util.Collection;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.housing.domain.entity.Estate;
import com.example.housing.repository.IEstateRepository;
import com.example.housing.exception.EstateNotFoundByIdException;

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
        log.debug("Saved an estate: {}", savedEstate);
        return savedEstate;
    }

    public Estate findEstateById(final Long estateId) {
        final Optional<Estate> possibleEstate = this.estateRepository.findById(estateId);
        log.debug("ID: {}, Found estate: {}", estateId, possibleEstate);
        return possibleEstate.get();
    }

    public Collection<Estate> findAllEstates() {
        final Collection<Estate> allEstates = this.estateRepository.findAll();
        log.debug("Found all estates");
        return allEstates;
    }

    public Estate updateEstateById(final Long estateId, final Estate newEstate) {
        final Estate oldEstate = this.getEstateWithId(estateId);

        oldEstate.setEstateType(newEstate.getEstateType());
        oldEstate.setPrice(newEstate.getPrice());
        final Estate updatedEstate = this.estateRepository.save(oldEstate);

        log.debug("Old estate: {}, Updated estate: {}", oldEstate, newEstate);

        return updatedEstate;
    }

    public Estate deleteEstateById(final Long estateId) {
        final Estate deletedEstate = this.getEstateWithId(estateId);
        this.estateRepository.deleteById(estateId);
        log.debug("Deleted estate: {}", deletedEstate);
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