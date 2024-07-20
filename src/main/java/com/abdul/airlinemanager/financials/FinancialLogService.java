package com.abdul.airlinemanager.financials;

import com.abdul.airlinemanager.player.Player;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FinancialLogService {

    private final FinancialLogRepository financialLogRepository;

    public void logTransaction(
            Player player,
            double amount,
            TransactionType transactionType,
            TransactionCategory transactionCategory
    ) {
        FinancialLog financialLog = FinancialLog.builder()
                .player(player)
                .amount(amount)
                .transactionType(transactionType)
                .transactionCategory(transactionCategory)
                .build();

        financialLogRepository.save(financialLog);
    }

}
