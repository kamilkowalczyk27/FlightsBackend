package com.flights.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "aircraft")
public class Aircraft {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "model")
    private String model;

    @Column(name = "height")
    private double height;

    @Column(name = "length")
    private double length;

    @Column(name = "max_speed")
    private BigDecimal maxSpeed;

    @Column(name = "fuel_capacity")
    private BigDecimal fuelCapacity;

    @Column(name = "max_range")
    private BigDecimal maxRange;

    @Column(name = "fuel_burn_per_hour")
    private BigDecimal fuelBurnPerHour;

    @Column(name = "cruising_speed")
    private BigDecimal cruisingSpeed;
}
