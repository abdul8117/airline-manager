package com.abdul.airlinemanager.player;

import org.springframework.stereotype.Service;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

//    public boolean existsByUsername(String username) {
//        return playerRepository.existsByUsername(username);
//    }

    public boolean existsByEmail(String email) {
        return playerRepository.existsByEmail(email);
    }
}
