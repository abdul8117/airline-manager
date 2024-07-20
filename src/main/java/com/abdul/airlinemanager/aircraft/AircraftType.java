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
    private Long aircraft_type_id;

    @Enumerated(EnumType.STRING)
    private AircraftManufacturers manufacturer;

    @Enumerated(EnumType.STRING)
    private AircraftModels model;

    private Integer capacity;

    private Integer range;

    private Long price;

}
