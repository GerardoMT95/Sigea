CREATE TABLE sigec.t_sigea_log_pubblicazioni (
	id_log_pubblicazioni int8 NULL,
	stato varchar NULL,
	pubblicazione_id int8 NULL,
	data_modifica date NULL,
	CONSTRAINT t_sigea_log_pubblicazioni_pk PRIMARY KEY (id_log_pubblicazioni),
	CONSTRAINT t_sigea_log_pubblicazioni_fk FOREIGN KEY (pubblicazione_id) REFERENCES sigec.t_sigea_pubblicazioni(pubblicazione_id)
);

ALTER TABLE sigec.t_sigea_log_pubblicazioni RENAME COLUMN stato TO codice_stato;


ALTER TABLE sigec.t_sigea_log_pubblicazioni ALTER COLUMN data_modifica TYPE timestamp(0) USING data_modifica::timestamp;


CREATE SEQUENCE sigec.sigea_logpubblicazioni_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
	
	
	CREATE TABLE sigec.t_sigea_stati_pubblicazioni (
	codice_stato varchar NULL,
	descrizione_stato varchar NULL
);

ALTER TABLE sigec.t_sigea_stati_pubblicazioni ADD CONSTRAINT t_sigea_stati_pubblicazioni_pk PRIMARY KEY (codice_stato);


ALTER TABLE sigec.t_sigea_log_pubblicazioni ADD CONSTRAINT t_sigea_log_pubblicazioni_fk1 FOREIGN KEY (codice_stato) REFERENCES sigec.t_sigea_stati_pubblicazioni(codice_stato);
