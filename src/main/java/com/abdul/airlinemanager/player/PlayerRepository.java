package com.abdul.airlinemanager.player;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, Long> {

    Optional<Player> findByEmail(String email);

    boolean existsByEmail(String email);

    @Query("SELECT player.password FROM Player player WHERE player.email = ?1")
    String findPasswordByEmail(String email);

    @Modifying
    @Query("UPDATE Player player SET player.balance = player.balance - ?2 " +
            "WHERE player.playerId = ?1 AND player.balance >= ?2")
    void decreaseBalance(Long playerId, Double amount);

    @Modifying
    @Query("UPDATE Player player SET player.balance = player.balance + ?2 " +
            "WHERE player.playerId = ?1")
    void increaseBalance(Long playerId, Double amount);

}
