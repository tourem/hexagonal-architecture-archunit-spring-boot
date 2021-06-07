package com.larbotech.infra.persistence.jpa.h2.adapter;

import javax.persistence.EntityNotFoundException;

import java.time.LocalDateTime;
import java.util.List;

import com.larbotech.infra.persistence.jpa.h2.mapper.AccountMapper;
import com.larbotech.infra.persistence.jpa.h2.repository.ActivityRepository;
import com.larbotech.infra.persistence.jpa.h2.annotation.SpiImplementPersistenceAdapter;
import com.larbotech.domain.model.Account;
import com.larbotech.domain.spi.persistence.AccountPersistencePort;
import com.larbotech.infra.persistence.jpa.h2.repository.SpringDataAccountRepository;
import com.larbotech.infra.persistence.jpa.h2.entity.AccountJpaEntity;
import com.larbotech.infra.persistence.jpa.h2.entity.ActivityJpaEntity;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@SpiImplementPersistenceAdapter
public class AccountPersistenceImplAdapter implements
		AccountPersistencePort {

	private final SpringDataAccountRepository accountRepository;
	private final ActivityRepository activityRepository;
	private final AccountMapper accountMapper;

	@Override
	public Account loadAccount(
					Account.AccountId accountId,
					LocalDateTime baselineDate) {

		AccountJpaEntity account =
				accountRepository.findById(accountId.getId())
						.orElseThrow(EntityNotFoundException::new);

		List<ActivityJpaEntity> activities =
				activityRepository.findByOwnerSince(
						accountId.getId(),
						baselineDate);

		Long withdrawalBalance = orZero(activityRepository
				.getWithdrawalBalanceUntil(
						accountId.getId(),
						baselineDate));

		Long depositBalance = orZero(activityRepository
				.getDepositBalanceUntil(
						accountId.getId(),
						baselineDate));

		return accountMapper.mapToDomainEntity(
				account,
				activities,
				withdrawalBalance,
				depositBalance);

	}

	private Long orZero(Long value){
		return value == null ? 0L : value;
	}

}
