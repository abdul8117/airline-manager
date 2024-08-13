package com.abdul.airlinemanager.fleet;

import com.abdul.airlinemanager.aircraft.AircraftType;
import com.abdul.airlinemanager.player.Player;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AircraftFleet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long aircraftFleetId;

    @ManyToOne
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;

    @ManyToOne
    @JoinColumn(name = "aircraft_type_id", nullable = false)
    private AircraftType aircraftType;

}
