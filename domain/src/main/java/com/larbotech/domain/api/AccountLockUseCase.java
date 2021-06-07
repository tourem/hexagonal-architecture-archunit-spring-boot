package com.larbotech.domain.api;

import com.larbotech.domain.model.Account;

public interface AccountLockUseCase {

	void lockAccount(Account.AccountId accountId);

	void releaseAccount(Account.AccountId accountId);

}
