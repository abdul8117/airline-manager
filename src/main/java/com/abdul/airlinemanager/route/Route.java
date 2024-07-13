package com.abdul.airlinemanager.route;

import com.abdul.airlinemanager.airport.Airport;
import com.abdul.airlinemanager.player.Player;
import jakarta.persistence.*;

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

    private Integer flightTime;  // in minutes

    private Integer turnaroundTime;  // in minutes

    @ManyToOne
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;

    public Route() {
    }

    public Route(Airport originAirport, Airport destinationAirport, Integer distance, Integer flightTime, Integer turnaroundTime, Player player) {
        this.originAirport = originAirport;
        this.destinationAirport = destinationAirport;
        this.distance = distance;
        this.flightTime = flightTime;
        this.turnaroundTime = turnaroundTime;
        this.player = player;
    }

    public Long getRoute_id() {
        return route_id;
    }

    public void setRoute_id(Long route_id) {
        this.route_id = route_id;
    }

    public Airport getOriginAirport() {
        return originAirport;
    }

    public void setOriginAirport(Airport originAirport) {
        this.originAirport = originAirport;
    }

    public Airport getDestinationAirport() {
        return destinationAirport;
    }

    public void setDestinationAirport(Airport destinationAirport) {
        this.destinationAirport = destinationAirport;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Integer getFlightTime() {
        return flightTime;
    }

    public void setFlightTime(Integer flightTime) {
        this.flightTime = flightTime;
    }

    public Integer getTurnaroundTime() {
        return turnaroundTime;
    }

    public void setTurnaroundTime(Integer turnaroundTime) {
        this.turnaroundTime = turnaroundTime;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
