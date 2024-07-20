package com.abdul.airlinemanager.route;

import com.abdul.airlinemanager.airport.Airport;
import com.abdul.airlinemanager.player.Player;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long route_id;

    @ManyToOne
    @JoinColumn(name = "origin_airport_id", nullable = false)
    private Airport originAirport;

    @ManyToOne
    @JoinColumn(name = "destination_airport_id", nullable = false)
    private Airport destinationAirport;

    private Integer distance;

    private Integer totalFlightTime; // time in the air + turnaround time

    @ManyToOne
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;

}
