package com.larbotech.infra.persistence.jpa.h2.mapper;

import java.util.ArrayList;
import java.util.List;

import com.larbotech.domain.model.Account;
import com.larbotech.domain.model.Activity;
import com.larbotech.domain.model.ActivityWindow;
import com.larbotech.domain.model.Money;
import com.larbotech.infra.persistence.jpa.h2.entity.AccountJpaEntity;
import com.larbotech.infra.persistence.jpa.h2.entity.ActivityJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

	public Account mapToDomainEntity(
			AccountJpaEntity account,
			List<ActivityJpaEntity> activities,
			Long withdrawalBalance,
			Long depositBalance) {

		Money baselineBalance = Money.subtract(
				Money.of(depositBalance),
				Money.of(withdrawalBalance));

		return Account.withId(
				 Account.AccountId.of(account.getId()),
				baselineBalance,
				mapToActivityWindow(activities));

	}

	public ActivityWindow mapToActivityWindow(List<ActivityJpaEntity> activities) {
		List<Activity> mappedActivities = new ArrayList<>();

		for (ActivityJpaEntity activity : activities) {
			mappedActivities.add(new Activity(
					Activity.ActivityId.of(activity.getId()),
					Account.AccountId.of(activity.getOwnerAccountId()),
					Account.AccountId.of(activity.getSourceAccountId()),
					Account.AccountId.of(activity.getTargetAccountId()),
					activity.getTimestamp(),
					Money.of(activity.getAmount())));
		}

		return new ActivityWindow(mappedActivities);
	}

	public ActivityJpaEntity mapToJpaEntity(Activity activity) {
		return new ActivityJpaEntity(
				activity.getId() == null ? null : activity.getId().getId(),
				activity.getTimestamp(),
				activity.getOwnerAccountId().getId(),
				activity.getSourceAccountId().getId(),
				activity.getTargetAccountId().getId(),
				activity.getMoney().getAmount().longValue());
	}

}
