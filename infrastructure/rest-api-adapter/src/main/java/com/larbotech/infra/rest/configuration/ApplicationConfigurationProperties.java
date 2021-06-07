package com.larbotech.infra.rest.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "bank")
public class ApplicationConfigurationProperties {

  private long transferThreshold = Long.MAX_VALUE;

}
