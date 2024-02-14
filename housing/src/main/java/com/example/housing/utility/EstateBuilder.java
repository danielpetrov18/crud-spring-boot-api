package com.example.housing.utility;

import com.example.housing.entity.Estate;
import com.example.housing.entity.EstateType;

public class EstateBuilder {

    private final static Double priceA = 200000d;
    private final static Double priceB = 100000d;
    private final static Double priceC = 550000d;

    public static Estate getEstateA() {
        return Estate
                .builder()
                .estateType(EstateType.PENTHOUSE)
                .price(priceA)
                .build();
    }

    public static Estate getEstateB() {
        return Estate
                .builder()
                .estateType(EstateType.FLAT)
                .price(priceB)
                .build();
    }

    public static Estate getEstateC() {
        return Estate
                .builder()
                .estateType(EstateType.MANSION)
                .price(priceC)
                .build();
    }

}