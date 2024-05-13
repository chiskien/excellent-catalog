package com.excellent.catalogservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing;

import java.util.Optional;

@Configuration
@EnableJdbcAuditing
public class DataConfig {

    @Bean
    AuditorAware<String> auditorAware() {
        return () -> Optional.of("CK");
    }
}
