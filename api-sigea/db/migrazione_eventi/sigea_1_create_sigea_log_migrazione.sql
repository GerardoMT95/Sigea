--DROP TABLE SIGEC.T_SIGEA_LOG_MIGRATION;
CREATE TABLE SIGEC.T_SIGEA_LOG_MIGRATION (
	EVENTO_ID_VIP int8 PRIMARY KEY,
	EVENTO_ID_SIGEA int8,
	RISULTATO TEXT,
	RISULTATO_REL TEXT,
	DATA_INSERIMENTO  TIMESTAMP ( 6 ), 
	UTILITY TEXT,
	EVENTO_VIP TEXT,
	EVENTO_SIGEA TEXT,
	UTENTE_VIP TEXT,
	TICKET_VIP TEXT,
	TICKET_SIGEA TEXT,
	ACCESSIBILITA_VIP TEXT,
	ACCESSIBILITA_SIGEA TEXT,
	DATI_GENERALI_VIP TEXT,
	DATI_GENERALI_SIGEA TEXT,
	LOCATION_VIP TEXT,
	LOCATION_SIGEA TEXT,
	PERIODO_VIP TEXT,
	PERIODO_SIGEA TEXT,
	ALLEGATI_VIP TEXT,
	IMMAGINI_SIGEA TEXT,
	DOCUMENTI_SIGEA TEXT,
	LINK_SIGEA TEXT,
	ALLEGATI_VIP2SIGEA TEXT,
	PRODOTTI_VIP TEXT,
	MICROESPERIENZE_VIP TEXT,
	METADATA_SIGEA text,
	RELAZIONI_VIP text,
	METADATA_POST_REL_SIGEA text
);