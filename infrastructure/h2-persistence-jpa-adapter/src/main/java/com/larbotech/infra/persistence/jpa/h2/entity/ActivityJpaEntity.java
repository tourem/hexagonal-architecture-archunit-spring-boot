package com.larbotech.infra.persistence.jpa.h2.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "activity")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityJpaEntity {

	@Id
	@GeneratedValue
	private UUID id;

	@Column
	private LocalDateTime timestamp;

	@Column
	private UUID ownerAccountId;

	@Column
	private UUID sourceAccountId;

	@Column
	private UUID targetAccountId;

	@Column
	private Long amount;

}
