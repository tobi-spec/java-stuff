CREATE USER replicator WITH REPLICATION ENCRYPTED PASSWORD 'replicator_password';
SELECT pg_create_physical_replication_slot('replication_slot');

CREATE TABLE product (
                         id SERIAL PRIMARY KEY,
                         name VARCHAR(50) NOT NULL
);