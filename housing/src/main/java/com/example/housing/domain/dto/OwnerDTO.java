package com.example.housing.domain.dto;

import lombok.*;

import java.util.Set;
import java.util.HashSet;

import java.time.LocalDate;

import com.example.housing.domain.entity.Estate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OwnerDTO {

    private Long ownerId;
    private String firstname;
    private String lastname;
    private LocalDate birthDate;
    @Builder.Default
    private Set<Estate> ownedEstates = new HashSet<>();

}