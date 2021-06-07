package com.larbotech.domain.api.service;

import com.larbotech.domain.api.AccountLockUseCase;
import com.larbotech.domain.model.Account;

public class NoOpAccountLockService implements AccountLockUseCase {

	@Override
	public void lockAccount(Account.AccountId accountId) {
		// do nothing
	}

	@Override
	public void releaseAccount(Account.AccountId accountId) {
		// do nothing
	}

}
