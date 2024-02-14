package com.example.housing.repository;

import com.example.housing.entity.Estate;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface IEstateRepository extends JpaRepository<Estate, Long> {
}
