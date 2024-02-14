package com.example.housing.entity;

import lombok.Getter;

@Getter
public enum EstateType {

    PENTHOUSE("Penthouse"),
    FLAT("Flat"),
    TOWNHOUSE("Townhouse"),
    MANSION("Mansion"),
    COTTAGE("Cottage");

    private final String type;

    private EstateType(final String newType) {
        this.type = newType;
    }

}