package com.larbotech.infra.rest.configuration;


import com.larbotech.domain.api.SendMoneyUseCase;
import com.larbotech.domain.api.service.MoneyTransferProperties;
import com.larbotech.domain.api.service.NoOpAccountLockService;
import com.larbotech.domain.api.service.SendMoneyService;
import com.larbotech.domain.model.Money;
import com.larbotech.infra.persistence.jpa.h2.adapter.AccountPersistenceImplAdapter;
import com.larbotech.infra.persistence.jpa.h2.adapter.ActivityPersistenceImplAdapter;
import com.larbotech.infra.persistence.jpa.h2.configuration.H2PersistenceConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@EnableConfigurationProperties(ApplicationConfigurationProperties.class)
@Import(H2PersistenceConfiguration.class)
// We need to provide the explicit packages path to scan for infrastructure layer autowiring
public class ApplicationConfiguration {

  /**
   * Adds a use-case-specific {@link MoneyTransferProperties} object to the application context. The properties
   * are read from the Spring-Boot-specific {@link ApplicationConfigurationProperties} object.
   */
  @Bean
  public MoneyTransferProperties moneyTransferProperties(ApplicationConfigurationProperties applicationConfigurationProperties){
    return new MoneyTransferProperties(Money.of(applicationConfigurationProperties.getTransferThreshold()));
  }

  @Bean
  public SendMoneyUseCase sendMoneyUseCase(AccountPersistenceImplAdapter accountRepositoryImplAdapter, ActivityPersistenceImplAdapter activityRepositoryImplAdapter, MoneyTransferProperties moneyTransferProperties){
    return new SendMoneyService(accountRepositoryImplAdapter, new NoOpAccountLockService(), activityRepositoryImplAdapter, moneyTransferProperties);
  }

}
