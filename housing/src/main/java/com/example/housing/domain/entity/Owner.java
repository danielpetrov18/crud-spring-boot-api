package com.example.housing.domain.entity;

import lombok.*;

import java.util.Set;
import java.util.HashSet;
import java.time.LocalDate;
import java.util.Collection;

import jakarta.persistence.*;

@Data
@Builder
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
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private Set<Estate> ownedEstates = new HashSet<>();
    public void addEstates(final Collection<Estate> estates) {
        this.ownedEstates.addAll(estates);
    }

}