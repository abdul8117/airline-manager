package com.abdul.airlinemanager.fleet;

import com.abdul.airlinemanager.aircraft.AircraftType;
import com.abdul.airlinemanager.aircraft.AircraftTypeRepository;
import com.abdul.airlinemanager.financials.FinancialLogService;
import com.abdul.airlinemanager.financials.TransactionCategory;
import com.abdul.airlinemanager.financials.TransactionType;
import com.abdul.airlinemanager.player.Player;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AircraftFleetService {

    private final AircraftFleetRepository aircraftFleetRepository;
    private final AircraftTypeRepository aircraftTypeRepository;
    private final FinancialLogService financialLogService;

    public List<AircraftFleet> getAllAircraftFleet() {
        return aircraftFleetRepository.findAll();
    }

    /**
     * Buy an aircraft type, add it to the player's fleet, update the
     * player's balance, and log the transaction.
     * @param aircraftTypeId id representing the specific aircraft type
     * @param player player object representing the player who is purchasing
     *               the aircraft
     */
    public void buyAircraftType(Long aircraftTypeId, Player player) {

        // get info about the aircraft
        AircraftType aircraftType = aircraftTypeRepository.findByAircraftTypeId(aircraftTypeId);

        // create a new aircraft fleet object
        AircraftFleet aircraftFleet = AircraftFleet.builder()
                .player(player)
                .aircraftType(aircraftType)
                .build();

        // save the aircraft fleet object
        aircraftFleetRepository.save(aircraftFleet);

        // update the player's balance
        player.setBalance(player.getBalance() - aircraftType.getPrice());

        // log the transaction in the FinancialLog entity
        financialLogService.logTransaction(player, aircraftType.getPrice(),
                TransactionType.EXPENSE, TransactionCategory.AIRCRAFT_PURCHASE);
    }
}
