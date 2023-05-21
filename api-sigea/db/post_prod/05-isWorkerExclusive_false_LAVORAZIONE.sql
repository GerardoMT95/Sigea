UPDATE SIGEC.T_SIGEA_STATI 
set configurazione=
 '{
    "isEditableByOwner": false,
    "isWorkerExclusive": false,
    "statiRaggiungibili": [
        {
            "addNote": true,
            "statoId": "MODIFICA",
            "permessi": {
                "autorizzato": true,
                "mustBeWorker": true
            },
            "emailOwner": "InModifica",
            "pubblicazione": false,
            "emailOperatori": "",
            "statiSovrascritti": [],
            "revocaPubblicazione": false
        },
        {
            "addNote": true,
            "statoId": "RESPINTO",
            "permessi": {
                "autorizzato": true,
                "mustBeWorker": true,
                "mustNotBeOwner": true
            },
            "emailOwner": "Respinto",
            "pubblicazione": false,
            "emailOperatori": "",
            "statiSovrascritti": [],
            "revocaPubblicazione": false
        },
        {
            "addNote": false,
            "statoId": "VALIDATO",
            "permessi": {
                "autorizzato": true,
                "mustBeWorker": true
            },
            "emailOwner": "",
            "pubblicazione": true,
            "emailOperatori": "Rivalidato",
            "statiSovrascritti": [],
            "revocaPubblicazione": false
        }
    ]
}'
where stato_id='LAVORAZIONE'