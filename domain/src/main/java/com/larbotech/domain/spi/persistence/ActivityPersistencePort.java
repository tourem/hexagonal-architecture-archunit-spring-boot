package com.larbotech.domain.spi.persistence;

import com.larbotech.domain.model.Account;

public interface ActivityPersistencePort {

	void updateActivities(Account account);

}
