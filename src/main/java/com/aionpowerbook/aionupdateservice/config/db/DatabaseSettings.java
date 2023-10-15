package com.aionpowerbook.aionupdateservice.config.db;

import lombok.Getter;
import lombok.Setter;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Getter
@Setter
public class DatabaseSettings {
    private String url;
    private String username;
    private String driver;
    private String password;
    private String liquibase;
    private String scheme;

    public DataSource createDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setSchema(scheme);
        return dataSource;
    }
}
