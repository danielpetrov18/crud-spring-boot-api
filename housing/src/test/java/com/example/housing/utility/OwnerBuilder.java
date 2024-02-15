package com.example.housing.utility;

import java.time.LocalDate;

import com.example.housing.domain.entity.Owner;

public final class OwnerBuilder {

    private OwnerBuilder() {}

    private final static String OWNER_A_FIRSTNAME = "John";
    private final static String OWNER_B_FIRSTNAME = "Casey";
    private final static String OWNER_C_FIRSTNAME = "Kevin";

    private final static String OWNER_A_LASTNAME = "Petrovic";
    private final static String OWNER_B_LASTNAME = "Petrov";
    private final static String OWNER_C_LASTNAME = "Calvert";

    private final static LocalDate OWNER_A_BIRTHDATE = LocalDate.of(1999, 1, 1);
    private final static LocalDate OWNER_B_BIRTHDATE = LocalDate.of(1980, 12, 20);
    private final static LocalDate OWNER_C_BIRTHDATE = LocalDate.of(2004, 4, 20);

    public static Owner getOwnerA() {
        return Owner
                .builder()
                .firstname(OWNER_A_FIRSTNAME)
                .lastname(OWNER_A_LASTNAME)
                .birthDate(OWNER_A_BIRTHDATE)
                .build();
    }

    public static Owner getOwnerB() {
        return Owner
                .builder()
                .firstname(OWNER_B_FIRSTNAME)
                .lastname(OWNER_B_LASTNAME)
                .birthDate(OWNER_B_BIRTHDATE)
                .build();
    }

    public static Owner getOwnerC() {
        return Owner
                .builder()
                .firstname(OWNER_C_FIRSTNAME)
                .lastname(OWNER_C_LASTNAME)
                .birthDate(OWNER_C_BIRTHDATE)
                .build();
    }

}