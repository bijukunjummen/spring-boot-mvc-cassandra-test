DROP KEYSPACE IF EXISTS sample;

/* Create new keyspace */
CREATE KEYSPACE IF NOT EXISTS sample WITH replication = {'class':'SimpleStrategy', 'replication_factor':1};
