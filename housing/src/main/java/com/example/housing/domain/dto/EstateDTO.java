package com.example.housing.domain.dto;

import lombok.*;

import com.example.housing.domain.entity.EstateType;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EstateDTO {

    private Long estateId;
    private EstateType estateType;
    private Double price;

}