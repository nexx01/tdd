package com.example.tdd.queryAndSpell;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.spel.spi.EvaluationContextExtension;

@Configuration
//@EnableJpaRepositories
public class SecurityConfiguration {

    @Bean
    EvaluationContextExtension securityExtension() {
        return new LocaleContextHolderExtension();
    }
}