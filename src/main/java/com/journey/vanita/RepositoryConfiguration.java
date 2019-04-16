package com.journey.vanita;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = "com.journey.vanita.repositories")
@EntityScan(basePackages = "com.journey.vanita")
@EnableTransactionManagement
public class RepositoryConfiguration {

}
