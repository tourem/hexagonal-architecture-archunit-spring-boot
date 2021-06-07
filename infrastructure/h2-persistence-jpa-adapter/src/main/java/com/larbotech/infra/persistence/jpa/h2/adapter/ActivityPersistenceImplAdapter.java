package com.larbotech.infra.persistence.jpa.h2.adapter;

import com.larbotech.domain.model.Account;
import com.larbotech.domain.model.Activity;
import com.larbotech.domain.spi.persistence.ActivityPersistencePort;
import com.larbotech.infra.persistence.jpa.h2.annotation.SpiImplementPersistenceAdapter;
import com.larbotech.infra.persistence.jpa.h2.mapper.AccountMapper;
import com.larbotech.infra.persistence.jpa.h2.repository.ActivityRepository;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@SpiImplementPersistenceAdapter
public class ActivityPersistenceImplAdapter implements
		ActivityPersistencePort {

	private final ActivityRepository activityRepository;
	private final AccountMapper accountMapper;

	@Override
	public void updateActivities(Account account) {
		for (Activity activity : account.getActivityWindow().getActivities()) {
			if (activity.getId() == null) {
				activityRepository.save(accountMapper.mapToJpaEntity(activity));
			}
		}
	}

}
