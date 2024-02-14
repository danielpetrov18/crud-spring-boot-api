package com.example.housing.repository;

import com.example.housing.entity.Owner;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface IOwnerRepository extends JpaRepository<Owner, Long> {



}