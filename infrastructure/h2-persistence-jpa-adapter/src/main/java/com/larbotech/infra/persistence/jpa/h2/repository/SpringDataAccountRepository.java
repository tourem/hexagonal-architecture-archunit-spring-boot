package com.larbotech.infra.persistence.jpa.h2.repository;

import com.larbotech.infra.persistence.jpa.h2.entity.AccountJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataAccountRepository extends JpaRepository<AccountJpaEntity, UUID> {
}
