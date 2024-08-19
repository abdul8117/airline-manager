package com.abdul.airlinemanager.fleet;

import com.abdul.airlinemanager.player.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AircraftFleetRepository extends JpaRepository<AircraftFleet,
        Long> {
    AircraftFleet findByAircraftFleetId(Long aircraftFleetId);

    List<PlayerFleetDto> findAllByPlayer(Player player);
}
