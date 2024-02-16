package com.example.housing.domain.dto;

import lombok.*;

import java.util.Set;

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
    private Set<Estate> estates;

}