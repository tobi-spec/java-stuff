package com.example.postgresreplication;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import static com.example.postgresreplication.DataSourceType.READER;
import static com.example.postgresreplication.DataSourceType.WRITER;

public class TransactionRoutingDataSource extends AbstractRoutingDataSource {

    @Override
    public Object determineCurrentLookupKey() {
        return TransactionSynchronizationManager.isCurrentTransactionReadOnly() ? READER : WRITER;
    }
}
