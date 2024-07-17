package com.abdul.airlinemanager.player;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, Long> {

    Optional<Player> findByEmail(String email);

    boolean existsByEmail(String email);

    @Query("SELECT player.password FROM Player player WHERE player.email = ?1")
    String findPasswordByEmail(String email);

}
