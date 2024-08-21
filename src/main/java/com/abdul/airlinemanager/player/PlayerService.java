package com.abdul.airlinemanager.player;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PlayerService {

    private final PlayerRepository playerRepository;

    @Transactional
    public void decreaseBalance(Player player, double amount) {
        playerRepository.decreaseBalance(player.getPlayerId(), amount);
    }

    @Transactional
    public void increaseBalance(Player player, double amount) {
        playerRepository.increaseBalance(player.getPlayerId(), amount);
    }
}
