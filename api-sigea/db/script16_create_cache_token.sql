CREATE TABLE sigec.t_sigea_cache_token (
	access_token varchar NULL,
	expires_in int NULL,
	client_id varchar NULL,
	creation_date timestamp NULL
);

ALTER TABLE sigec.t_sigea_cache_token ALTER COLUMN access_token SET NOT NULL;
ALTER TABLE sigec.t_sigea_cache_token ALTER COLUMN expires_in SET NOT NULL;
ALTER TABLE sigec.t_sigea_cache_token ALTER COLUMN client_id SET NOT NULL;
ALTER TABLE sigec.t_sigea_cache_token ALTER COLUMN creation_date SET NOT NULL;