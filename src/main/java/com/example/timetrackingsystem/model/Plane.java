package com.example.timetrackingsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.core.annotation.Order;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "plane")
public class Plane {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer planeId;

    @Column(name = "name", nullable = false)
    private String planeName;

    @Column(name = "year_of_manufacture", nullable = false)
    private Integer yearOfManufacture;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "airline_id", referencedColumnName = "id")
    private Airline airline;

    public Plane(String name, Integer year, Airline airline) {
        planeName = name;
        yearOfManufacture = year;
        this.airline = airline;
    }
}
