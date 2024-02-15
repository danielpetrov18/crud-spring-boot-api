package com.example.housing.domain.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OwnerDTO {

    private Long ownerId;
    private String firstname;
    private String lastname;
    private LocalDate birthDate;

}