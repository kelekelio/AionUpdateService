package com.aionpowerbook.aionupdateservice.config.multitenancy;

import com.aionpowerbook.aionupdateservice.config.db.DatabaseConfiguration;
import liquibase.integration.spring.MultiTenantSpringLiquibase;
import liquibase.integration.spring.SpringLiquibase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;

import javax.sql.DataSource;
import java.util.List;

@Slf4j
@Lazy(value = false)
@Configuration
@RequiredArgsConstructor
public class MultiTenantInitializationService {

    private final DatabaseConfiguration databaseConfiguration;

//    @Bean
//    @ConfigurationProperties("db")
//    public LiquibaseProperties masterLiquibaseProperties() {
//        return new LiquibaseProperties();
//    }

//    private final SpringLiquibase springLiquibase;
//
//    private final DataSource dataSource;
//
//
//
//    @Bean
//    public MultiTenantSpringLiquibase liquibaseMt(DataSource dataSource) throws SQLException {
//        MultiTenantSpringLiquibase multiTenantSpringLiquibase = new MultiTenantSpringLiquibase();
//        multiTenantSpringLiquibase.setDataSource(dataSource);
//        Statement stmt = dataSource.getConnection().createStatement();
//        multiTenantSpringLiquibase.setChangeLog("classpath:db/changelog/aion_0_changelog.xml");
//        multiTenantSpringLiquibase.setShouldRun(true);
//        return multiTenantSpringLiquibase;
//    }

    @Bean
    public SpringLiquibase liquibase(DataSource dataSource) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog(databaseConfiguration.getLiquibase().getChangeLog());
        liquibase.setShouldRun(databaseConfiguration.getLiquibase().isEnabled());
        liquibase.setDropFirst(true);
        liquibase.setShouldRun(false);
        return liquibase;
    }

    @Bean
    @DependsOn("liquibase") // ensure execution after SpringLiquibase Bean
    public MultiTenantSpringLiquibase liquibaseMt(DataSource dataSource) {

        MultiTenantSpringLiquibase liquibase = new MultiTenantSpringLiquibase();
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog("classpath:db/changelog/aion_0_changelog.xml");
        liquibase.setShouldRun(true);
        liquibase.setSchemas(List.of("001", "002"));
        return liquibase;
    }
}
