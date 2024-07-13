package com.abdul.airlinemanager.aircraft;

import jakarta.persistence.*;

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

    private Integer range; // in kilometres

    public AircraftType() {
    }

    public AircraftType(AircraftManufacturers manufacturer, AircraftModels model, Integer capacity, Integer range) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.capacity = capacity;
        this.range = range;
    }

    public Long getAircraft_type_id() {
        return aircraft_type_id;
    }

    public void setAircraft_type_id(Long aircraft_type_id) {
        this.aircraft_type_id = aircraft_type_id;
    }

    public AircraftManufacturers getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(AircraftManufacturers manufacturer) {
        this.manufacturer = manufacturer;
    }

    public AircraftModels getModel() {
        return model;
    }

    public void setModel(AircraftModels model) {
        this.model = model;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getRange() {
        return range;
    }

    public void setRange(Integer range) {
        this.range = range;
    }
}
