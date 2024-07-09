package com.example.timetrackingsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "plane")
public class Plane extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String planeName;

    @Column(name = "year_of_manufacture", nullable = false)
    private Integer yearOfManufacture;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "airline_id", referencedColumnName = "id")
    private Airline airline;
}
