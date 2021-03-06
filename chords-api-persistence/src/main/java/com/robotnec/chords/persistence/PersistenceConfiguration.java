package com.robotnec.chords.persistence;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.sql.DataSource;

@EnableJpaRepositories("com.robotnec.chords.persistence.repository")
@Configuration
public class PersistenceConfiguration {

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.url}")
    private String dataSourceUrl;

    @Value("${spring.datasource.driverClassName}")
    private String driverClassName;

    @Value("${flyway.db.clean:false}")
    private boolean flywayClean;

    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder
                .create()
                .password(password)
                .username(username)
                .url(dataSourceUrl)
                .driverClassName(driverClassName)
                .type(MysqlDataSource.class)
                .build();
    }

    @Bean
    public FlywayMigrationStrategy flywayMigrationStrategy() {
        return flyway -> {
            if (flywayClean) {
                flyway.clean();
            }
            flyway.migrate();
        };
    }
}

