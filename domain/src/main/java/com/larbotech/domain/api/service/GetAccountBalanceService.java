package com.larbotech.domain.api.service;

import java.time.LocalDateTime;

import com.larbotech.domain.api.GetAccountBalanceQuery;
import com.larbotech.domain.spi.persistence.AccountPersistencePort;
import com.larbotech.domain.model.Account;
import com.larbotech.domain.model.Money;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class GetAccountBalanceService implements GetAccountBalanceQuery {

    private final AccountPersistencePort accountPersistencePort;

    @Override
    public Money getAccountBalance(Account.AccountId accountId) {
        return accountPersistencePort.loadAccount(accountId, LocalDateTime.now())
                .calculateBalance();
    }
}
