package com.larbotech.infra.persistence.jpa.h2.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
// We need to provide the explicit packages path to scan for infrastructure layer autowiring
@ComponentScan("com.larbotech.infra.persistence.jpa.h2.adapter")
@EntityScan("com.larbotech.infra.persistence.jpa.h2.entity")
@EnableJpaRepositories("com.larbotech.infra.persistence.jpa.h2.repository")
public class H2PersistenceConfiguration {
}
