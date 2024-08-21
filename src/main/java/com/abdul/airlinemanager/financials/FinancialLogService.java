package com.abdul.airlinemanager.financials;

import com.abdul.airlinemanager.player.Player;
import com.abdul.airlinemanager.time.WeekService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FinancialLogService {

    private final FinancialLogRepository financialLogRepository;
    private final WeekService weekService;

    /**
     * Logs a transaction into the database.
     */
    public void logTransaction(
            Player player,
            Double amount,
            TransactionType transactionType,
            TransactionCategory transactionCategory
    ) {
        FinancialLog financialLog = FinancialLog.builder()
                .player(player)
                .amount(amount)
                .transactionType(transactionType)
                .transactionCategory(transactionCategory)
                .week(weekService.getCurrentWeek())
                .build();

        financialLogRepository.save(financialLog);
    }
}
