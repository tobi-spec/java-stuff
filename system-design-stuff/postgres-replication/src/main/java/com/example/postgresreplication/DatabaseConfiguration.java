package com.example.postgresreplication;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DatabaseConfiguration {


    @Bean
    @ConfigurationProperties(prefix = "app.datasource.writer")
    public DataSource firstDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties(prefix = "app.datasource.reader")
    public DataSource secondDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public DataSource dataSource(@Qualifier("firstDataSource") DataSource writerDataSource,
                                 @Qualifier("secondDataSource") DataSource readerDataSource) {
        TransactionRoutingDataSource routingDataSource = new TransactionRoutingDataSource();

        Map<Object, Object> targets = new HashMap<>();
        targets.put(DataSourceType.WRITER, writerDataSource);
        targets.put(DataSourceType.READER, readerDataSource);

        routingDataSource.setDefaultTargetDataSource(writerDataSource);
        routingDataSource.setTargetDataSources(targets);
        routingDataSource.afterPropertiesSet();

        return routingDataSource;
    }

}
