package com.larbotech.domain.spi.persistence;

import java.time.LocalDateTime;

import com.larbotech.domain.model.Account;

public interface AccountPersistencePort {

	Account loadAccount(Account.AccountId accountId, LocalDateTime baselineDate);
}
