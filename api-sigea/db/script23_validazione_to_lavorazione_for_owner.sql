-- Auto-generated SQL script #202105120944
UPDATE sigec.t_sigea_stati
	SET configurazione='{
  "isEditableByOwner": false,
  "isWorkerExclusive": false,
  "statiRaggiungibili": [
    {
      "addNote": true,
      "statoId": "MODIFICA",
      "permessi": {
        "autorizzato": true,
        "mustNotBeOwner": true
      },
      "emailOwner": "InModifica",
      "pubblicazione": false,
      "emailOperatori": "Revocato",
      "statiSovrascritti": [],
      "revocaPubblicazione": true
    },
    {
      "addNote": true,
      "statoId": "MODIFICA",
      "permessi": {
        "mustBeOwner": true
      },
      "emailOwner": "InModifica",
      "pubblicazione": false,
      "emailOperatori": "Revocato",
      "statiSovrascritti": [
        "LAVORAZIONE"
      ],
      "revocaPubblicazione": true
    },
    {
      "addNote": false,
      "statoId": "LAVORAZIONE",
      "permessi": {
        "autorizzato": true
      },
      "emailOwner": "",
      "pubblicazione": false,
      "emailOperatori": "Revocato",
      "statiSovrascritti": [],
      "revocaPubblicazione": true
    },
    {
      "addNote": true,
      "statoId": "ANNULLATO",
      "permessi": {
        "anyUser": true
      },
      "emailOwner": "",
      "pubblicazione": false,
      "emailOperatori": "Annullato",
      "statiSovrascritti": [],
      "revocaPubblicazione": true
    }
  ]
}'::jsonb::jsonb
	WHERE stato_id='VALIDATO';
