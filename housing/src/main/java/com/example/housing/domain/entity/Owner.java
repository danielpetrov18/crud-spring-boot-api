package com.example.housing.domain.entity;

import lombok.*;

import java.util.Set;
import java.util.HashSet;
import java.time.LocalDate;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Owner")
@Table(name = "owners")
public class Owner {

    @Id
    @Column(name = "owner_id", updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ownerId;

    @Column(name = "first_name", nullable = false, updatable = false)
    private String firstname;

    @Column(name = "last_name", nullable = false, updatable = false)
    private String lastname;

    @Column(name = "birth_date", nullable = false, updatable = false)
    private LocalDate birthDate;

    @Builder.Default
    @ToString.Exclude
    @JsonManagedReference
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private final Set<Estate> ownedEstates = new HashSet<>();

}