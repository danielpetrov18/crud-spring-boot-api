package com.example.housing.domain.entity;

import lombok.Getter;

@Getter
public enum EstateType {

    PENTHOUSE("Penthouse"),
    FLAT("Flat"),
    TOWNHOUSE("Townhouse"),
    MANSION("Mansion"),
    COTTAGE("Cottage");

    private final String type;

    EstateType(final String newType) {
        this.type = newType;
    }

}