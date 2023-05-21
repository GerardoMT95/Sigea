-- Auto-generated SQL script #202207270918
UPDATE sigec.t_sigea_stati
	set configurazione='{"isEditableByOwner": false, "isWorkerExclusive": false, "statiRaggiungibili": [{"addNote": false, "statoId": "MODIFICA", "permessi": {"mustBeOwner": true}, "emailOwner": "", "pubblicazione": false, "emailOperatori": "", "statiSovrascritti": [], "revocaPubblicazione": false}]}'::jsonb
	WHERE stato_id='RIVALUTAZIONE';
UPDATE sigec.t_sigea_stati
	SET configurazione='{"isEditableByOwner": true, "isWorkerExclusive": false, "statiRaggiungibili": [{"addNote": false, "statoId": "VALUTAZIONE", "permessi": {"mustBeOwner": true}, "emailOwner": "", "pubblicazione": false, "emailOperatori": "", "statiSovrascritti": [], "revocaPubblicazione": false}, {"addNote": false, "statoId": "VALIDATO", "permessi": {"autorizzato": true, "mustBeOwner": true}, "emailOwner": "", "pubblicazione": true, "emailOperatori": "", "statiSovrascritti": ["VALUTAZIONE"], "revocaPubblicazione": false}]}'::jsonb
	WHERE stato_id='ANNULLATO';
UPDATE sigec.t_sigea_stati
	SET configurazione='{"isEditableByOwner": false, "isWorkerExclusive": false, "statiRaggiungibili": [{"addNote": true, "statoId": "MODIFICA", "permessi": {"autorizzato": true, "mustBeWorker": true}, "emailOwner": "InModifica", "pubblicazione": false, "emailOperatori": "", "statiSovrascritti": [], "revocaPubblicazione": false}, {"addNote": true, "statoId": "RESPINTO", "permessi": {"autorizzato": true, "mustBeWorker": true, "mustNotBeOwner": true}, "emailOwner": "Respinto", "pubblicazione": false, "emailOperatori": "", "statiSovrascritti": [], "revocaPubblicazione": false}, {"addNote": false, "statoId": "VALIDATO", "permessi": {"autorizzato": true, "mustBeWorker": true}, "emailOwner": "", "pubblicazione": true, "emailOperatori": "Rivalidato", "statiSovrascritti": ["RIVALIDATO"], "revocaPubblicazione": false}, {"addNote": false, "statoId": "RIVALIDATO", "permessi": {"autorizzato": true, "mustBeWorker": true}, "emailOwner": "", "pubblicazione": true, "emailOperatori": "Rivalidato", "statiSovrascritti": [], "revocaPubblicazione": false}]}'::jsonb
	WHERE stato_id='RIVALIDATO';
UPDATE sigec.t_sigea_stati
	SET configurazione='{"isEditableByOwner": true, "isWorkerExclusive": false, "statiRaggiungibili": [{"addNote": false, "statoId": "RIVALUTAZIONE", "permessi": {"mustBeOwner": true}, "emailOwner": "", "pubblicazione": false, "emailOperatori": "", "statiSovrascritti": [], "revocaPubblicazione": false}, {"addNote": false, "statoId": "VALIDATO", "permessi": {"autorizzato": true, "mustBeOwner": true}, "emailOwner": "", "pubblicazione": true, "emailOperatori": "Rivalidato", "statiSovrascritti": ["RIVALUTAZIONE", "RIVALIDATO"], "revocaPubblicazione": false}, {"addNote": false, "statoId": "RIVALIDATO", "permessi": {"autorizzato": true, "mustBeOwner": true}, "emailOwner": "", "pubblicazione": true, "emailOperatori": "Rivalidato", "statiSovrascritti": ["RIVALUTAZIONE"], "revocaPubblicazione": false}]}'::jsonb
	WHERE stato_id='BOZZA';
UPDATE sigec.t_sigea_stati
	SET configurazione='{"isEditableByOwner": false, "isWorkerExclusive": false, "statiRaggiungibili": [{"addNote": false, "statoId": "RIVALUTAZIONE", "permessi": {"autorizzato": true, "mustNotBeOwner": true}, "emailOwner": "", "pubblicazione": false, "emailOperatori": "", "statiSovrascritti": [], "revocaPubblicazione": false}, {"addNote": true, "statoId": "MODIFICA", "permessi": {"autorizzato": true, "mustNotBeOwner": true}, "emailOwner": "InModifica", "pubblicazione": false, "emailOperatori": "", "statiSovrascritti": [], "revocaPubblicazione": false}]}'::jsonb
	WHERE stato_id='RESPINTO';
UPDATE sigec.t_sigea_stati
	SET configurazione='{"isEditableByOwner": false, "isWorkerExclusive": false, "statiRaggiungibili": [{"addNote": true, "statoId": "MODIFICA", "permessi": {"autorizzato": true, "mustNotBeOwner": true}, "emailOwner": "InModifica", "pubblicazione": false, "emailOperatori": "Revocato", "statiSovrascritti": [], "revocaPubblicazione": true}, {"addNote": true, "statoId": "MODIFICA", "permessi": {"mustBeOwner": true}, "emailOwner": "InModifica", "pubblicazione": false, "emailOperatori": "Revocato", "statiSovrascritti": ["LAVORAZIONE"], "revocaPubblicazione": true}, {"addNote": true, "statoId": "ANNULLATO", "permessi": {"anyUser": true}, "emailOwner": "", "pubblicazione": false, "emailOperatori": "Annullato", "statiSovrascritti": [], "revocaPubblicazione": true}, {"addNote": false, "statoId": "LAVORAZIONE", "permessi": {"autorizzato": true}, "emailOwner": "", "pubblicazione": false, "emailOperatori": "", "statiSovrascritti": [], "revocaPubblicazione": false}]}'::jsonb
	WHERE stato_id='VALUTAZIONE';
UPDATE sigec.t_sigea_stati
	SET configurazione='{"isEditableByOwner": false, "isWorkerExclusive": false, "statiRaggiungibili": [{"addNote": false, "statoId": "LAVORAZIONE", "permessi": {"autorizzato": true}, "emailOwner": "", "pubblicazione": false, "emailOperatori": "", "statiSovrascritti": [], "revocaPubblicazione": false}]}'::jsonb
	WHERE stato_id='MODIFICA';
UPDATE sigec.t_sigea_stati
	SET configurazione='{"isEditableByOwner": false, "isWorkerExclusive": false, "statiRaggiungibili": [{"addNote": true, "statoId": "MODIFICA", "permessi": {"autorizzato": true, "mustNotBeOwner": true}, "emailOwner": "InModifica", "pubblicazione": false, "emailOperatori": "Revocato", "statiSovrascritti": [], "revocaPubblicazione": true}, {"addNote": true, "statoId": "MODIFICA", "permessi": {"mustBeOwner": true}, "emailOwner": "InModifica", "pubblicazione": false, "emailOperatori": "Revocato", "statiSovrascritti": ["LAVORAZIONE"], "revocaPubblicazione": true}, {"addNote": true, "statoId": "ANNULLATO", "permessi": {"anyUser": true}, "emailOwner": "", "pubblicazione": false, "emailOperatori": "Annullato", "statiSovrascritti": [], "revocaPubblicazione": true}, {"addNote": false, "statoId": "LAVORAZIONE", "permessi": {"autorizzato": true}, "emailOwner": "", "pubblicazione": false, "emailOperatori": "", "statiSovrascritti": [], "revocaPubblicazione": false}]}'::jsonb
	WHERE stato_id='LAVORAZIONE';
UPDATE sigec.t_sigea_stati
	SET configurazione='{"isEditableByOwner": false, "isWorkerExclusive": false, "statiRaggiungibili": [{"addNote": false, "statoId": "LAVORAZIONE", "permessi": {"autorizzato": true}, "emailOwner": "", "pubblicazione": false, "emailOperatori": "", "statiSovrascritti": [], "revocaPubblicazione": false}, {"addNote": false, "statoId": "MODIFICA", "permessi": {"autorizzato": true, "mustBeOwner": true}, "emailOwner": "", "pubblicazione": false, "emailOperatori": "", "statiSovrascritti": ["LAVORAZIONE"], "revocaPubblicazione": false}]}'::jsonb
	WHERE stato_id='VALIDATO';




UPDATE sigec.t_sigea_ruoli
	SET permessi='{"creaEvento": true, "filtroGestisci": true, "filtroEventoSeeAll": true, "cambioStatoMODIFICA": true, "cambioStatoRESPINTO": true, "cambioStatoVALIDATO": true, "cambioStatoRIVALIDATO": true, "cambioStatoLAVORAZIONE": true,"cambioStatoRILAVORAZIONE": true, "filtroSegnalazioneSeeAll": true, "writableNucleoLAVORAZIONE": true}'::jsonb
	WHERE ruolo_id='VALIDATORE';