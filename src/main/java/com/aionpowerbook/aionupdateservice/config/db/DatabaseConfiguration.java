package com.aionpowerbook.aionupdateservice.config.db;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "db")
public class DatabaseConfiguration {

    private Map<String, DatabaseSettings> settings = new HashMap<>();
    private LiquibaseProperties liquibase;

    public Map<Object, Object> createTargetDataSources() {
        Map<Object, Object> result = new HashMap<>();
        settings.forEach((key, value) ->  result.put(key, value.createDataSource()));
        return result;
    }

    @Bean
    public DataSource dataSource() {
        ServerRoutingDataSource dataSource = new ServerRoutingDataSource();
        dataSource.setTargetDataSources(createTargetDataSources());
        return dataSource;
    }
}
