package com.example.housing.entity;

import lombok.*;

import java.util.Set;
import java.util.HashSet;
import java.time.LocalDate;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Data
@Builder
@ToString
@AllArgsConstructor
@Entity(name="Owner")
@Table(name="owners")
public class Owner {

    @Id
    @Column(name="owner_id", updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long ownerId;

    @Column(name="first_name", nullable = false)
    private final String firstname;

    @Column(name="last_name", nullable = false)
    private final String lastname;

    @Column(name = "birth_date", nullable = false)
    private final LocalDate birthDate;

    @Builder.Default
    @ToString.Exclude
    @JsonManagedReference
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private final Set<Estate> ownedEstates = new HashSet<>();

}