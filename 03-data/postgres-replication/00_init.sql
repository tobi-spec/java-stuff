CREATE USER replicator WITH REPLICATION ENCRYPTED PASSWORD 'replicator_password';
SELECT pg_create_physical_replication_slot('replication_slot');

CREATE TABLE product (
                         id SERIAL PRIMARY KEY,
                         name VARCHAR(50) NOT NULL
);

GRANT USAGE ON SCHEMA public TO replicator;
GRANT SELECT ON TABLE product TO replicator;
GRANT SELECT ON ALL TABLES IN SCHEMA public TO replicator;
ALTER DEFAULT PRIVILEGES FOR ROLE postgres IN SCHEMA public
GRANT SELECT ON TABLES TO replicator;
