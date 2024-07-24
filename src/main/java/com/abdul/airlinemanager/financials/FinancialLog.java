package com.abdul.airlinemanager.financials;

import com.abdul.airlinemanager.player.Player;
import com.abdul.airlinemanager.time.Week;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class FinancialLog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long logId;

    @ManyToOne
    @JoinColumn(name = "week_id", nullable = false)
    public Week week;

    @ManyToOne
    @JoinColumn(name = "player_id", nullable = false)
    public Player player;

    @Enumerated(EnumType.STRING)
    public TransactionType transactionType; // income or expense

    @Enumerated(EnumType.STRING)
    public TransactionCategory transactionCategory; // aircraft, route,
    // flight expense, etc

    public Double amount;

}
