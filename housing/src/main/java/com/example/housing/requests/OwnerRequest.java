package com.example.housing.requests;

import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OwnerRequest {

    private String firstname;
    private String lastname;
    private LocalDate birthDate;

}