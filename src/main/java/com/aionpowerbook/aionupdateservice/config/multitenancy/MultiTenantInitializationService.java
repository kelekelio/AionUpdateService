package com.aionpowerbook.aionupdateservice.config.multitenancy;

import com.aionpowerbook.aionupdateservice.config.db.DatabaseConfiguration;
import com.aionpowerbook.aionupdateservice.config.db.DatabaseSettings;
import liquibase.integration.spring.MultiTenantSpringLiquibase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.util.Map;

@Slf4j
@Lazy(value = false)
@Configuration
@RequiredArgsConstructor
public class MultiTenantInitializationService {

    private final DatabaseConfiguration databaseConfiguration;

    @Bean
    public MultiTenantSpringLiquibase liquibaseMt() throws Exception {

        MultiTenantSpringLiquibase liquibase = new MultiTenantSpringLiquibase();

        for (Map.Entry<String, DatabaseSettings> tenant : databaseConfiguration.getSettings().entrySet()) {
            liquibase.setDataSource(tenant.getValue().createDataSource());
            liquibase.setChangeLog(databaseConfiguration.getLiquibase().getChangeLog());
            liquibase.setShouldRun(databaseConfiguration.getLiquibase().isEnabled());
            liquibase.afterPropertiesSet();
        }
        return liquibase;
    }
}
