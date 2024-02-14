package com.example.housing.entity;

import lombok.*;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="Estate")
@Table(name="estates")
public class Estate {

    @Id
    @Column(name="estate_id", updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long estateId;

    @Enumerated(EnumType.STRING)
    @Column(name="estate_type", nullable = false, updatable = false)
    private EstateType estateType;

    @Column(name="price", nullable = false)
    private Double price;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "owner_id")
    private Owner owner;

}