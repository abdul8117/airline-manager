package com.abdul.airlinemanager.fleet;

import com.abdul.airlinemanager.aircraft.AircraftType;
import com.abdul.airlinemanager.player.Player;
import jakarta.persistence.*;

@Entity
public class AircraftFleet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long aircraft_fleet_id;

    @ManyToOne
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;

    @ManyToOne
    @JoinColumn(name = "aircraft_type_id", nullable = false)
    private AircraftType aircraftType;

    public AircraftFleet() {
    }

    public AircraftFleet(Player player, AircraftType aircraftType) {
        this.player = player;
        this.aircraftType = aircraftType;
    }

    public Long getAircraft_fleet_id() {
        return aircraft_fleet_id;
    }

    public void setAircraft_fleet_id(Long aircraft_fleet_id) {
        this.aircraft_fleet_id = aircraft_fleet_id;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public AircraftType getAircraftType() {
        return aircraftType;
    }

    public void setAircraftType(AircraftType aircraftType) {
        this.aircraftType = aircraftType;
    }
}
