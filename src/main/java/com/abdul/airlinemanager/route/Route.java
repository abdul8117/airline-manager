package com.abdul.airlinemanager.route;

import com.abdul.airlinemanager.airport.Airport;
import com.abdul.airlinemanager.player.Player;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
    private Long routeId;

    @ManyToOne
    @JoinColumn(name = "hub_airport_id", nullable = false)
    private Airport hubAirport;

    @ManyToOne
    @JoinColumn(name = "destination_airport_id", nullable = false)
    private Airport destinationAirport;

    @Column(nullable = false)
    private Integer distance; // one way distance in km

    @Column(nullable = false)
    private Integer totalFlightTime; // time in the air + turnaround time
    // (minutes)

    @Column(nullable = false)
    private Integer paxDemand; // passenger demand

    @ManyToOne
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;

}
