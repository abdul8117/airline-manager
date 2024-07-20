package com.abdul.airlinemanager.financials;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FinancialLogRepository extends JpaRepository<FinancialLog,
        Long> {
}
