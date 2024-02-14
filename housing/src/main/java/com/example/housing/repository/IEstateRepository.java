package com.example.housing.repository;

import java.util.List;

import com.example.housing.entity.Estate;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface IEstateRepository extends JpaRepository<Estate, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM estates WHERE owner_id = :ownerId")
    List<Estate> findEstatesOwnedByOwnerId(@Param("ownerId") final Long ownerId);

}
