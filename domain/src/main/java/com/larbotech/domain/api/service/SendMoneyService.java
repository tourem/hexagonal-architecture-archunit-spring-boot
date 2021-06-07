package com.larbotech.domain.api.service;

import com.larbotech.domain.api.AccountLockUseCase;
import com.larbotech.domain.api.SendMoneyUseCase;
import com.larbotech.domain.spi.persistence.AccountPersistencePort;
import com.larbotech.domain.spi.persistence.ActivityPersistencePort;
import com.larbotech.domain.api.SendMoneyCommand;
import com.larbotech.domain.model.Account;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Transactional
public class SendMoneyService implements SendMoneyUseCase {

	private final AccountPersistencePort accountPersistencePort;
	private final AccountLockUseCase accountLockUseCase;
	private final ActivityPersistencePort activityPersistencePort;
	private final MoneyTransferProperties moneyTransferProperties;

	@Override
	public boolean sendMoney(SendMoneyCommand command) {

		checkThreshold(command);

		LocalDateTime baselineDate = LocalDateTime.now().minusDays(10);

		Account sourceAccount = accountPersistencePort.loadAccount(
				command.getSourceAccountId(),
				baselineDate);

		Account targetAccount = accountPersistencePort.loadAccount(
				command.getTargetAccountId(),
				baselineDate);

		Account.AccountId sourceAccountId = sourceAccount.getId()
				.orElseThrow(() -> new IllegalStateException("expected source account ID not to be empty"));
		Account.AccountId targetAccountId = targetAccount.getId()
				.orElseThrow(() -> new IllegalStateException("expected target account ID not to be empty"));

		accountLockUseCase.lockAccount(sourceAccountId);
		if (!sourceAccount.withdraw(command.getMoney(), targetAccountId)) {
			accountLockUseCase.releaseAccount(sourceAccountId);
			return false;
		}

		accountLockUseCase.lockAccount(targetAccountId);
		if (!targetAccount.deposit(command.getMoney(), sourceAccountId)) {
			accountLockUseCase.releaseAccount(sourceAccountId);
			accountLockUseCase.releaseAccount(targetAccountId);
			return false;
		}

		activityPersistencePort.updateActivities(sourceAccount);
		activityPersistencePort.updateActivities(targetAccount);

		accountLockUseCase.releaseAccount(sourceAccountId);
		accountLockUseCase.releaseAccount(targetAccountId);
		return true;
	}

	private void checkThreshold(SendMoneyCommand command) {
		if(command.getMoney().isGreaterThan(moneyTransferProperties.getMaximumTransferThreshold())){
			throw new ThresholdExceededException(moneyTransferProperties.getMaximumTransferThreshold(), command.getMoney());
		}
	}

}




