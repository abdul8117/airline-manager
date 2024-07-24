package com.abdul.airlinemanager.fleet;

import com.abdul.airlinemanager.aircraft.AircraftType;
import com.abdul.airlinemanager.player.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AircraftFleetRepository extends JpaRepository<AircraftFleet, Long> {

    @Query("SELECT new com.abdul.airlinemanager.fleet.PlayerFleetDto(fleet" +
            ".aircraftType, COUNT(fleet.aircraftType)) FROM AircraftFleet " +
            "fleet WHERE fleet.player.playerId = ?1 GROUP BY fleet" +
            ".aircraftType")
    List<PlayerFleetDto> findCountsByPlayer(Long playerId);

}
