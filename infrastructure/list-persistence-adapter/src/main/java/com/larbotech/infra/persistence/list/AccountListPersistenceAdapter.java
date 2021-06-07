package com.larbotech.infra.persistence.list;

import com.larbotech.domain.model.Account;
import com.larbotech.domain.model.Activity;
import com.larbotech.domain.spi.persistence.AccountPersistencePort;
import com.larbotech.domain.spi.persistence.ActivityPersistencePort;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
class AccountListPersistenceAdapter implements
		AccountPersistencePort,
		ActivityPersistencePort {

	private List<Account> accountList = new ArrayList<>();
	private List<Activity> activityList = new ArrayList<>();

	@Override
	public Account loadAccount(
					Account.AccountId accountId,
					LocalDateTime baselineDate) {

		//TODO
		return null;
	}

	private Long orZero(Long value){
		return value == null ? 0L : value;
	}


	@Override
	public void updateActivities(Account account) {
	//TODO
	}

}
