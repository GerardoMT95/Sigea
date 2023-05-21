
INSERT INTO sigec.t_sigea_stati (stato_id,descrizione,stato_inizio,stato_fine,accesso_condizionato,nome,configurazione)
	VALUES ('RIVALIDATO','Rivalidato',false,false,true,'VALIDA','{"isEditableByOwner": false, "isWorkerExclusive": false, "statiRaggiungibili": [{"addNote": true, "statoId": "MODIFICA", "permessi": {"autorizzato": true, "mustNotBeOwner": true}, "emailOwner": "InModifica", "pubblicazione": false, "emailOperatori": "Revocato", "statiSovrascritti": [], "revocaPubblicazione": true}, {"addNote": true, "statoId": "MODIFICA", "permessi": {"mustBeOwner": true}, "emailOwner": "InModifica", "pubblicazione": false, "emailOperatori": "Revocato", "statiSovrascritti": ["LAVORAZIONE"], "revocaPubblicazione": true}, {"addNote": false, "statoId": "LAVORAZIONE", "permessi": {"autorizzato": true}, "emailOwner": "", "pubblicazione": false, "emailOperatori": "Revocato", "statiSovrascritti": [], "revocaPubblicazione": true}, {"addNote": true, "statoId": "ANNULLATO", "permessi": {"anyUser": true}, "emailOwner": "", "pubblicazione": false, "emailOperatori": "Annullato", "statiSovrascritti": [], "revocaPubblicazione": true}]}'::jsonb);



UPDATE sigec.t_sigea_ruoli
	SET permessi='{
	"creaEvento": true,
	"filtroGestisci": true,
	"filtroEventoSeeAll": true,
	"cambioStatoMODIFICA": true,
	"cambioStatoRESPINTO": true,
	"cambioStatoVALIDATO": true,
	"cambioStatoRIVALIDATO": true,
	"cambioStatoLAVORAZIONE": true,
	"filtroSegnalazioneSeeAll": true,
	"writableNucleoLAVORAZIONE": true
}'::jsonb
	WHERE ruolo_id='VALIDATORE';



