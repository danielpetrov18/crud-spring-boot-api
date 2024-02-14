package com.example.housing.entity;

import lombok.*;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Data
@Builder
@ToString
@AllArgsConstructor
@Entity(name="Estate")
@Table(name="estates")
public class Estate {

    @Id
    @Column(name="house_id", updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long houseId;

    @Enumerated(EnumType.STRING)
    @Column(name="estate_type", nullable = false)
    private final EstateType estateType;

    @Column(name="price", nullable = false)
    private final Double price;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "owner_id")
    private final Owner owner;

}