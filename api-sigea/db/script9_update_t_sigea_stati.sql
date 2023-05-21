-- Auto-generated SQL script #202103181508
UPDATE sigec.t_sigea_stati
	SET configurazione='{"isEditableByOwner": false, "isWorkerExclusive": false, "statiRaggiungibili": [{"addNote": true, "statoId": "MODIFICA", "permessi": {"autorizzato": true, "mustNotBeOwner": true}, "emailOwner": "InModifica", "pubblicazione": false, "emailOperatori": "Revocato", "statiSovrascritti": [], "revocaPubblicazione": true}, {"addNote": true, "statoId": "MODIFICA", "permessi": {"mustBeOwner": true}, "emailOwner": "", "pubblicazione": false, "emailOperatori": "Revocato", "statiSovrascritti": ["LAVORAZIONE"], "revocaPubblicazione": true}, {"addNote": false, "statoId": "LAVORAZIONE", "permessi": {"autorizzato": true}, "emailOwner": "", "pubblicazione": false, "emailOperatori": "Revocato", "statiSovrascritti": [], "revocaPubblicazione": true}, {"addNote": true, "statoId": "ANNULLATO", "permessi": {"mustBeOwner": false}, "emailOwner": "", "pubblicazione": false, "emailOperatori": "Annullato", "statiSovrascritti": [], "revocaPubblicazione": true}]}'::jsonb::jsonb
	WHERE stato_id='VALIDATO';

-- Auto-generated SQL script #202103181511
UPDATE sigec.t_sigea_ruoli
	SET permessi='{"creaEvento": true, "filtroGestisci": true, "filtroEventoSeeAll": true, "cambioStatoMODIFICA": true, "cambioStatoRESPINTO": true, "cambioStatoVALIDATO": true, "cambioStatoANNULLATO": true, "cambioStatoLAVORAZIONE": true, "filtroSegnalazioneSeeAll": true, "writableNucleoLAVORAZIONE": true}'::jsonb::jsonb
	WHERE ruolo_id='VALIDATORE';

UPDATE sigec.t_sigea_stati
	SET configurazione='{"isEditableByOwner": false, "isWorkerExclusive": false, "statiRaggiungibili": [{"addNote": true, "statoId": "LAVORAZIONE", "permessi": {"autorizzato": true}, "emailOwner": "", "pubblicazione": false, "emailOperatori":"", "statiSovrascritti": [], "revocaPubblicazione": false}]}'::jsonb::jsonb
	WHERE stato_id='RIVALUTAZIONE';
