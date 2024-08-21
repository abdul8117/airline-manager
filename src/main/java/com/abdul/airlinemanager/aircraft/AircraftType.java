package com.abdul.airlinemanager.aircraft;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AircraftType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long aircraftTypeId;

    @Enumerated(EnumType.STRING)
    private AircraftManufacturers manufacturer;

    @Enumerated(EnumType.STRING)
    private AircraftModels model;

    private Integer capacity;

    private Integer range; // in kilometres

    private Double price;

}
