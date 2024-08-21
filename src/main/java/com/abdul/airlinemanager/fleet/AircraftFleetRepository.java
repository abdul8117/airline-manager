package com.abdul.airlinemanager.fleet;

import com.abdul.airlinemanager.player.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AircraftFleetRepository extends JpaRepository<AircraftFleet,
        Long> {
    AircraftFleet findByAircraftFleetId(Long aircraftFleetId);

    List<AircraftFleetDto> findAllByPlayer(Player player);
}
